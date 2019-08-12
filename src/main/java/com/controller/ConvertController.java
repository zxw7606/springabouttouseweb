package com.controller;

import com.annotation.Complex;
import org.javamoney.moneta.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Currency;
import java.util.Date;
import java.util.Locale;

/**
 * @description:
 * @create: 2019-08-12 20:47
 * @author: zxw
 **/
@RestController
public class ConvertController {
    private final static Logger logger = LoggerFactory.getLogger(ConvertController.class);
    @Autowired
    private ConversionService conversionService;

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("seeObj")
    public Object seeObj(Complex complex) {
        Assert.notNull(conversionService, "conversionService is null");
        return complex;
    }


    @GetMapping("getSee")
    public Complex getSee() {
        Complex complex = new Complex();
        complex.setDate(new Date());
        complex.setMoney(Money.of(10000000, Currency.getInstance(Locale.getDefault()).getCurrencyCode()));
        return complex;
    }

}
