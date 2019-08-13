package com.validator;

import com.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description:
 * @create: 2019-08-13 13:14
 * @author: zxw
 **/
public class UserConstraintValidator implements ConstraintValidator<UserConstrain,User> {

    private final static Logger logger = LoggerFactory.getLogger(UserConstraintValidator.class);

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Override
    public void initialize(UserConstrain constraintAnnotation) {
        if (webApplicationContext!=null){
            logger.info("webApplicationContext injected by spring");
        }else {
            logger.info("webApplicationContext is null");
        }

    }

    @Override
    public boolean isValid(User value, ConstraintValidatorContext context) {

        if (value==null||!"name".equals(value.getName())){
            return true;
        }
        return false;
    }
}
