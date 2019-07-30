package com.scand.currencies.service;

import java.io.IOException;
import java.math.BigDecimal;

@SuppressWarnings("ALL")
public interface CurrencyExchangeService {
    @SuppressWarnings("unused")
    BigDecimal convert(String fromCurrency, String toCurrency, BigDecimal amount) throws IOException;
}
