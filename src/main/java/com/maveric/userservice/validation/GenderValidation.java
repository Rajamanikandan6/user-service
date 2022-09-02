package com.maveric.userservice.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { GenderValidatorImplementation.class })
public @interface GenderValidation {
    String message() default "Gender should either MALE or FEMALE";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
