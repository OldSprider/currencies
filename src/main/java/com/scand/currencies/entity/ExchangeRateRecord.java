package com.scand.currencies.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "spots")
public class ExchangeRateRecord implements ExchangeRate {

    @Id
    @Column(name = "Currency")
    private final String currency;

    @Column(name = "Spot")
    private final BigDecimal spot;

    public ExchangeRateRecord(String currency, BigDecimal spot) {
        this.currency = currency;
        this.spot = spot;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getSpot() {
        return spot;
    }

}
