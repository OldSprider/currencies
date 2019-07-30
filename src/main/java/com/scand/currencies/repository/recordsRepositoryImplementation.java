package com.scand.currencies.repository;

import com.scand.currencies.entity.ExchangeRateRecord;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipInputStream;

@SuppressWarnings("unused")
@org.springframework.stereotype.Repository("recordsRepository")
public class recordsRepositoryImplementation implements recordsRepository<ExchangeRateRecord> {
    private static final Logger logger = Logger.getLogger(recordsRepositoryImplementation.class.getName());

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Override
    public void load(List<ExchangeRateRecord> exchangeRateRecordList) {
        for (ExchangeRateRecord record : exchangeRateRecordList) {
            if (searchRecordInDatabase(record)) updateRecord(record);
            else insertRecord(record);
        }
    }

    @Override
    public List readFromURL(String urlString) throws IOException {
        URL url = new URL(urlString);
        url.openConnection();
        InputStream inputStream = url.openStream();
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        zipInputStream.getNextEntry();
        LineIterator lineIterator = IOUtils.lineIterator(zipInputStream, "utf-8");
        List<String> currencyList = Arrays.asList(lineIterator.nextLine().split(","));
        List<String> spotList = Arrays.asList(lineIterator.nextLine().split(","));
        List<ExchangeRateRecord> exchangeRateList = new ArrayList<>();
        if (currencyList.size() == spotList.size()) {
            for (int i = 1; i < currencyList.size() - 1; i++) {
                exchangeRateList.add(new ExchangeRateRecord(currencyList.get(i), new BigDecimal(spotList.get(i).trim())));
            }
        } else {
            exchangeRateList.clear();
        }
        lineIterator.close();
        return exchangeRateList;
    }

    @Override
    public boolean searchRecordInDatabase(ExchangeRateRecord record) {
        String currencyExists;
        try {
            currencyExists = jdbcTemplate.queryForObject("SELECT Currency FROM spots WHERE Currency =?", new Object[]{record.getCurrency()}, String.class);
        } catch (Exception e) {
            logger.log(Level.ERROR, "exception while searching in database: ", e);
            return false;
        }
        return !Objects.requireNonNull(currencyExists).isEmpty();
    }

    @Override
    public void updateRecord(ExchangeRateRecord record) {
        jdbcTemplate.update("UPDATE spots SET Spot=(?) WHERE Currency=(?)", record.getSpot(), record.getCurrency());
    }

    @Override
    public void insertRecord(ExchangeRateRecord record) {
        jdbcTemplate.update("INSERT INTO spots (Currency, Spot) values(?, ?)", record.getCurrency(), record.getSpot());
    }

    @Override
    public BigDecimal searchSpotInDatabase(String currency) {
        return jdbcTemplate.queryForObject("SELECT Spot FROM spots WHERE Currency='" + (" " + currency) + "'", BigDecimal.class);
    }

}
