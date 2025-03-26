package si.paurus.task.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.paurus.task.service.FoDataService;

import java.nio.file.Path;

@RestController
@RequestMapping("/api/fo-data")
public class FoDataController {

    private final FoDataService service;

    public FoDataController(FoDataService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertData() {
//        System.out.println("Current directory: " + System.getProperty("user.dir"));
        service.insertDataFast(Path.of("src\\main\\resources\\fo_random.txt")); //Hardcoded path, intended for testing purposes
        return ResponseEntity.ok("Insertion started.");
    }

    @GetMapping("/min-max")
    public ResponseEntity<Object[]> getMinMax() {
        return ResponseEntity.ok(service.getMinMaxDateInsert());
    }
}

