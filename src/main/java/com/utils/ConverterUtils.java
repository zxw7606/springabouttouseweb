package com.utils;

import org.springframework.core.convert.ConversionService;

/**
 * @description:
 * @create: 2019-08-13 09:18
 * @author: zxw
 **/
public class ConverterUtils {

    public static String convertToString(ConversionService conversionService,Object s){
        return conversionService.convert(s,String.class);
    }
}
