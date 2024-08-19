package com.authorization.cajuchallenge.model.validator;

import com.authorization.cajuchallenge.model.decorators.NotZero;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class NotZeroValidator implements ConstraintValidator<NotZero, BigDecimal> {

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.compareTo(BigDecimal.ZERO) != 0;
    }
}