package com.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description:
 * @create: 2019-08-13 13:18
 * @author: zxw
 **/
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= UserConstraintValidator.class)
public @interface UserConstrain {
    String message() default "{com.validation.UserConstrain.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
