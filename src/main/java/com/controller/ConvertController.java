package com.controller;

import com.utils.ConverterUtils;
import com.vo.Complex;
import com.vo.User;
import org.javamoney.moneta.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

/**
 * @description:
 * @create: 2019-08-12 20:47
 * @author: zxw
 **/
@Controller
public class ConvertController {
    private final static Logger logger = LoggerFactory.getLogger(ConvertController.class);
    @Autowired
    private ConversionService conversionService;

    @GetMapping("hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }

    /**
     * 类型转换的测试
     *
     * @param modelAndView
     * @param complex
     * @param request      http://localhost:9999/seeObj?date=1996-11-11 11:11:11&money=CNY 11111&user={"id":"123","name":"4456"}
     */
    @PostMapping("seeObj")
    public Object seeObj(ModelAndView modelAndView, @ModelAttribute Complex complex, HttpServletRequest request) {
        String dateS = ConverterUtils.convertToString(conversionService, complex.getDate());
        String moneyS = ConverterUtils.convertToString(conversionService, complex.getMoney());
//        String userS = ConverterUtils.convertToString(conversionService, complex.getUser());
        logger.info("date = {}, money={} ", dateS, moneyS);
        modelAndView.setViewName("index");
        return modelAndView;
    }


    @GetMapping("getSee")
    @ResponseBody
    public Object getSee() {
        Complex complex = new Complex();
        complex.setUser(new User());
        Currency currency = Currency.getInstance(Locale.getDefault());
        complex.setMoney(Money.of(10000, currency.getCurrencyCode()));
        complex.setDate(new Date());
        return complex;
    }

}
