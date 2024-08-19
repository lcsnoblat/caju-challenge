package com.authorization.cajuchallenge.model.decorators;

import com.authorization.cajuchallenge.model.validator.NotZeroValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotZeroValidator.class)
public @interface NotZero {

    String message() default "Value must not be zero";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}