package com.maveric.userservice.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class DateValidatorImplementation implements ConstraintValidator <DateValidation,String>{


    @Override
    public boolean isValid(String dateOfBirth, ConstraintValidatorContext context) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = new Date();
            date2 = dateFormat.parse(dateOfBirth);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        dateFormat.format(date1);
        dateFormat.format(date2);
        if(date1.compareTo(date2) > 0)
        {
           return true;
        }else{
            return false;
        }
    }
}
