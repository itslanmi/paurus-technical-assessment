package si.paurus.task.service;

import org.springframework.stereotype.Service;
import si.paurus.task.dto.TaxResponse;

@Service
public class TaxService {
    public TaxResponse calculateTax(double playedAmount, double odd, double taxRate, double taxAmount, boolean isGeneralTaxation, boolean isRateBased) {
        double possibleReturnAmountBefTax = playedAmount * odd;
        double finalAmount;
        double taxValue;

        if (isGeneralTaxation) {
            // General Taxation
            if (isRateBased) {
                taxValue = possibleReturnAmountBefTax * (taxRate / 100);
            } else {
                taxValue = taxAmount;
            }
        } else {
            // Winnings Taxation
            double winnings = possibleReturnAmountBefTax - playedAmount;
            if (isRateBased) {
                taxValue = winnings * (taxRate / 100);
            } else {
                taxValue = taxAmount;
            }
        }

        finalAmount = possibleReturnAmountBefTax - taxValue;
        return new TaxResponse(possibleReturnAmountBefTax,possibleReturnAmountBefTax, finalAmount, taxRate, taxValue);
    }
}
