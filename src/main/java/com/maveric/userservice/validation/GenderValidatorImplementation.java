package com.maveric.userservice.validation;

import com.maveric.userservice.constant.Gender;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
public class GenderValidatorImplementation implements ConstraintValidator <GenderValidation,Gender>{
    @Override
    public boolean isValid(Gender gender, ConstraintValidatorContext constraintValidatorContext) {
        if(gender.equals(Gender.MALE) || gender.equals(Gender.FEMALE))
            return true;
        return false;
    }
}
