package com.scand.currencies.repository;

import com.scand.currencies.entity.ExchangeRate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings("ALL")
public interface recordsRepository<ExchangeRateRecord extends ExchangeRate> {
    void load(List<ExchangeRateRecord> records);

    List readFromURL(String urlString) throws IOException;

    boolean searchRecordInDatabase(ExchangeRateRecord record);

    void updateRecord(ExchangeRateRecord record);

    void insertRecord(ExchangeRateRecord record);

    BigDecimal searchSpotInDatabase(String currency);
}
