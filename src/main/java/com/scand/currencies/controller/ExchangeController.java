package com.scand.currencies.controller;

import com.scand.currencies.entity.ExchangeRateDTO;
import com.scand.currencies.service.CurrencyExchangeService;
import com.scand.currencies.validation.CustomAmountValidation;
import com.scand.currencies.validation.CustomCurrencyValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;

@RestController
@Validated
class ExchangeController {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    @Qualifier("dataService")
    private CurrencyExchangeService currencyExchangeService;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public Object converting(@RequestParam("fromCurrency") @CustomCurrencyValidation(message = "wrong fromCurrency") String fromCurrency,
                             @RequestParam("toCurrency") @CustomCurrencyValidation(message = "wrong toCurrency") String toCurrency,
                             @RequestParam("amount") @CustomAmountValidation(message = "invalid amount") String amount) throws IOException {
        BigDecimal convertedAmount = currencyExchangeService.convert(fromCurrency, toCurrency, new BigDecimal(amount));
        return new ExchangeRateDTO(fromCurrency, toCurrency, new BigDecimal(amount), convertedAmount);
    }
}
