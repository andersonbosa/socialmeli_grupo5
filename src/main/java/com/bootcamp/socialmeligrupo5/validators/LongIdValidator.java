package com.bootcamp.socialmeligrupo5.validators;

import com.bootcamp.socialmeligrupo5.annotations.Id;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LongIdValidator implements ConstraintValidator<Id, Long> {
		@Override
		public void initialize(Id constraintAnnotation) {
		}

		@Override
		public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
				return value != null && value > 0;
		}
}
