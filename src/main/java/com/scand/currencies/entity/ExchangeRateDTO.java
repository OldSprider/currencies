package com.scand.currencies.entity;

import java.math.BigDecimal;

@SuppressWarnings("unused")
public class ExchangeRateDTO {

    private final String fromCurrency;
    private final String toCurrency;
    private final BigDecimal amount;
    private BigDecimal convertedAmount;

    public ExchangeRateDTO(String fromCurrency, String toCurrency, BigDecimal amount, BigDecimal convertedAmount) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amount = amount;
        this.convertedAmount = convertedAmount;
    }

    @Override
    public String toString() {
        return "Convert " + amount + " " + fromCurrency + " to " + toCurrency + " = " + convertedAmount;
    }

    public void setConvertedAmount(BigDecimal convertedAmount) {
        this.convertedAmount = convertedAmount;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }

}
