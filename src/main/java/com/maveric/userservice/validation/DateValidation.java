package com.maveric.userservice.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { DateValidatorImplementation.class })
public @interface DateValidation {
    String message() default "dateOfBirth should not be future date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
