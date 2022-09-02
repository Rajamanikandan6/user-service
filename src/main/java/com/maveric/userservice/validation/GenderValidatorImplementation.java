package com.maveric.userservice.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
public class GenderValidatorImplementation implements ConstraintValidator <GenderValidation,String>{
    @Override
    public boolean isValid(String gender, ConstraintValidatorContext constraintValidatorContext) {
        if(gender == "MALE" || gender == "FEMALE"){
            return true;
        }
        return false;
    }
}
