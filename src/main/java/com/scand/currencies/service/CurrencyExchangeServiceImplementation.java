package com.scand.currencies.service;

import com.scand.currencies.repository.recordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@SuppressWarnings("unused")
@Service("dataService")
public class CurrencyExchangeServiceImplementation implements CurrencyExchangeService {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    @Qualifier("recordsRepository")
    private recordsRepository recordsRepository;

    @SuppressWarnings("unchecked")
    @Override
    public BigDecimal convert(String fromCurrency, String toCurrency, BigDecimal startAmount) throws IOException {
        recordsRepository.load(recordsRepository.readFromURL("https://www.ecb.europa.eu/stats/eurofxref/eurofxref.zip"));
        BigDecimal fromSpot = recordsRepository.searchSpotInDatabase(fromCurrency);
        BigDecimal toSpot = recordsRepository.searchSpotInDatabase(toCurrency);
        return (startAmount.multiply(toSpot)).divide(fromSpot, RoundingMode.HALF_UP);
    }
}
