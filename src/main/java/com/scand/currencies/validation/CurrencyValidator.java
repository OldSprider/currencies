package com.scand.currencies.validation;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Currency;

@SuppressWarnings({"WeakerAccess", "unused"})
public class CurrencyValidator implements
        ConstraintValidator<CustomCurrencyValidation, String> {

    private static final Logger logger = Logger.getLogger(CurrencyValidator.class.getName());

    @SuppressWarnings("EmptyMethod")
    @Override
    public void initialize(CustomCurrencyValidation currency) {
    }

    @Override
    public boolean isValid(String currency, ConstraintValidatorContext constraintValidatorContext) {
        boolean currencyStatus;
        try {
            currencyStatus = Currency.getAvailableCurrencies().contains(Currency.getInstance(currency));
        } catch (RuntimeException e) {
            logger.log(Level.ERROR, "exception in currency validation, wrong currency");
            currencyStatus = false;
        }
        return currency != null && currencyStatus;
    }
}
