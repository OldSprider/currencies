package com.scand.currencies.validation;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

@SuppressWarnings("unused")
public class AmountValidator implements ConstraintValidator<CustomAmountValidation, String> {

    private static final Logger logger = Logger.getLogger(AmountValidator.class.getName());

    @SuppressWarnings("EmptyMethod")
    @Override
    public void initialize(CustomAmountValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(String amount, ConstraintValidatorContext constraintValidatorContext) {
        try {
            BigDecimal amountStatus = new BigDecimal(amount);
        } catch (RuntimeException e) {
            logger.log(Level.ERROR, "exception in amount validation, wrong amount");
            return false;
        }
        return true;
    }
}
