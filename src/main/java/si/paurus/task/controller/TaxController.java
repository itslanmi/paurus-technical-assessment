package si.paurus.task.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import si.paurus.task.dto.TaxRequest;
import si.paurus.task.dto.TaxResponse;
import si.paurus.task.service.TaxService;

@RestController
public class TaxController {

    private final TaxService taxService;

    public TaxController(TaxService taxService) {
        this.taxService = taxService;
    }

    @PostMapping("/api/tax")
    public TaxResponse calculateTax(@RequestBody TaxRequest request,
                                    @RequestParam boolean isGeneralTaxation,
                                    @RequestParam boolean isRateBased,
                                    @RequestParam double taxRate,
                                    @RequestParam double taxAmount) {

        return taxService.calculateTax(request.getPlayedAmount(), request.getOdd(), taxRate, taxAmount, isGeneralTaxation, isRateBased);
    }
}