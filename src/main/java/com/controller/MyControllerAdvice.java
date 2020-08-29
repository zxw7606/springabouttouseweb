package com.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description: 全局的处理
 * @create: 2019-08-13 18:01
 * @author: zxw
 **/
//
@RestControllerAdvice
public class MyControllerAdvice {
    private final static Logger logger = LoggerFactory.getLogger(MyControllerAdvice.class);

    @ExceptionHandler(Exception.class)
    public Object error(Exception e){
        logger.error(e.getMessage());
        return "error";
    }


}
