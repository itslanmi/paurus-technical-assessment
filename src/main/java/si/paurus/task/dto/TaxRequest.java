package si.paurus.task.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaxRequest {
    @JsonProperty("traderId")
    private int traderId;

    @JsonProperty("playedAmount")
    private double playedAmount;

    @JsonProperty("odd")
    private double odd;

    public int getTraderId() {
        return traderId;
    }

    public void setTraderId(int traderId) {
        this.traderId = traderId;
    }

    public double getPlayedAmount() {
        return playedAmount;
    }

    public void setPlayedAmount(double playedAmount) {
        this.playedAmount = playedAmount;
    }

    public double getOdd() {
        return odd;
    }

    public void setOdd(double odd) {
        this.odd = odd;
    }
}