package si.paurus.task.model;

    import jakarta.persistence.*;
    import java.time.LocalDateTime;

    @Entity
    @Table(name = "fo_data")
    public class FoData {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private int matchId;

        @Column(nullable = false)
        private int marketId;

        @Column(nullable = false)
        private int outcomeId;

        private String specifiers;

        @Column(nullable = false)
        private LocalDateTime dateInsert = LocalDateTime.now();

        public FoData() {
        }

        public FoData(Long id, int matchId, int marketId, int outcomeId, String specifiers, LocalDateTime dateInsert) {
            this.id = id;
            this.matchId = matchId;
            this.marketId = marketId;
            this.outcomeId = outcomeId;
            this.specifiers = specifiers;
            this.dateInsert = dateInsert;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public int getMatchId() {
            return matchId;
        }

        public void setMatchId(int matchId) {
            this.matchId = matchId;
        }

        public int getMarketId() {
            return marketId;
        }

        public void setMarketId(int marketId) {
            this.marketId = marketId;
        }

        public int getOutcomeId() {
            return outcomeId;
        }

        public void setOutcomeId(int outcomeId) {
            this.outcomeId = outcomeId;
        }

        public String getSpecifiers() {
            return specifiers;
        }

        public void setSpecifiers(String specifiers) {
            this.specifiers = specifiers;
        }

        public LocalDateTime getDateInsert() {
            return dateInsert;
        }

        public void setDateInsert(LocalDateTime dateInsert) {
            this.dateInsert = dateInsert;
        }
    }