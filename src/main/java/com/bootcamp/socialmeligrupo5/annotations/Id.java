package com.bootcamp.socialmeligrupo5.annotations;

import com.bootcamp.socialmeligrupo5.validators.LongIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LongIdValidator.class)
public @interface Id {
	String message() default "O Id deve ser um valor numerico positivo";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
