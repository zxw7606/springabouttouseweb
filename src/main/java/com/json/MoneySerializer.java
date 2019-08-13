package com.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.number.money.MonetaryAmountFormatter;
import org.springframework.stereotype.Component;

import javax.money.MonetaryAmount;
import java.io.IOException;
import java.util.Locale;

/**
 * @description:
 * @create: 2019-08-13 11:16
 * @author: zxw
 **/
@Component
public class MoneySerializer extends JsonSerializer<MonetaryAmount> {

    @Autowired
    private MonetaryAmountFormatter monetaryAmountFormatter;

    @Override
    public void serialize(MonetaryAmount value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(monetaryAmountFormatter.print(value, Locale.getDefault()));
    }
}
