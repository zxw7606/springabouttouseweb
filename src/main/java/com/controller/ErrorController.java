package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 错误测试
 * @create: 2019-08-13 18:08
 * @author: zxw
 **/
@RestController
public class ErrorController {

    @RequestMapping("seeError")
    public String error(){
        throw new RuntimeException("test");
    }
}
