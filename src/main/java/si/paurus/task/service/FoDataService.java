package si.paurus.task.service;

import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import si.paurus.task.repository.FoDataRepository;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.nio.file.Path;
import java.sql.Connection;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FoDataService {

    private final DataSource dataSource;
    private final FoDataRepository repository;
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    public FoDataService(DataSource dataSource, FoDataRepository repository) {
        this.dataSource = dataSource;
        this.repository = repository;
    }

    private String[] cleanRow(String line) {
        String[] fields = line.split("\\|");

        if (fields.length < 3) {
            throw new IllegalArgumentException("Invalid row format: " + line);
        }

        String matchId = fields[0].replaceAll("\\D+", ""); // Keep only numbers
        String marketId = fields[1].replaceAll("\\D+", "");
        String outcomeId = fields[2].replaceAll("\\D+", "");

        String specifiers = fields.length > 3
                ? String.join(",", Arrays.copyOfRange(fields, 3, fields.length)).replaceAll("^'|'$", "") // Remove quotes
                : "";

        return new String[]{matchId, marketId, outcomeId, specifiers};
    }



    @Transactional
    public void insertDataFast(Path filePath) {
        executorService.submit(() -> {
            try (Connection connection = dataSource.getConnection();
                 BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {

                BaseConnection pgConnection = connection.unwrap(BaseConnection.class);

                String copySQL = "COPY fo_data (match_id, market_id, outcome_id, specifiers) FROM STDIN WITH (FORMAT csv, DELIMITER '|')";
//              Uncomment the following line to ignore duplicates, this was my initial approach but didn't work as expected
//              String copySQL = "COPY fo_data (match_id, market_id, outcome_id, specifiers) FROM STDIN WITH (FORMAT csv, DELIMITER '|') ON CONFLICT (match_id, market_id, outcome_id) DO NOTHING";
                CopyManager copyManager = new CopyManager(pgConnection);

                StringBuilder cleanedData = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] cleanedFields = cleanRow(line);
                    cleanedData.append(String.join("|", cleanedFields)).append("\n");
                }

                copyManager.copyIn(copySQL, new BufferedReader(new StringReader(cleanedData.toString())));

                System.out.println("Data inserted successfully from file: " + filePath);
            } catch (Exception e) {
                System.err.println("Error inserting data: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

    public Object[] getMinMaxDateInsert() {
        return repository.findMinMaxDateInsert();
    }
}