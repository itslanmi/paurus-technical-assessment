package si.paurus.task.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaxResponse {
    @JsonProperty("possibleReturnAmount")
    private double possibleReturnAmount;

    @JsonProperty("possibleReturnAmountBefTax")
    private double possibleReturnAmountBefTax;

    @JsonProperty("possibleReturnAmountAfterTax")
    private double possibleReturnAmountAfterTax;

    @JsonProperty("taxRate")
    private double taxRate;

    @JsonProperty("taxAmount")
    private double taxAmount;

    public TaxResponse(double possibleReturnAmount, double possibleReturnAmountBefTax, double possibleReturnAmountAfterTax, double taxRate, double taxAmount) {
        this.possibleReturnAmount = possibleReturnAmount;
        this.possibleReturnAmountBefTax = possibleReturnAmountBefTax;
        this.possibleReturnAmountAfterTax = possibleReturnAmountAfterTax;
        this.taxRate = taxRate;
        this.taxAmount = taxAmount;
    }

    public double getPossibleReturnAmount() {
        return possibleReturnAmount;
    }

    public double getPossibleReturnAmountBefTax() {
        return possibleReturnAmountBefTax;
    }

    public double getPossibleReturnAmountAfterTax() {
        return possibleReturnAmountAfterTax;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public double getTaxAmount() {
        return taxAmount;
    }
}