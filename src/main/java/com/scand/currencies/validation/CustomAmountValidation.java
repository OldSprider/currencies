package com.scand.currencies.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@SuppressWarnings("unused")
@Documented
@Constraint(validatedBy = AmountValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomAmountValidation {
    String message() default "Invalid currency";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
