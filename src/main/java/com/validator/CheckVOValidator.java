package com.validator;

import com.vo.CheckVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @description: 对RequestBody的验证
 * @create: 2019-08-13 15:55
 * @author: zxw
 **/
public class CheckVOValidator implements Validator {

    private final static Logger logger = LoggerFactory.getLogger(CheckVOValidator.class);

    @Override
    public boolean supports(Class<?> clazz) {
        return CheckVO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        logger.info("模拟验证");
        errors.rejectValue("name","name.test");
    }
}
