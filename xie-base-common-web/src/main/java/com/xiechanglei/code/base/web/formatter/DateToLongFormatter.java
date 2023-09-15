package com.xiechanglei.code.base.web.formatter;

import org.springframework.format.Formatter;

import java.util.Date;
import java.util.Locale;

public class DateToLongFormatter implements Formatter<Date> {

    @Override
    public Date parse(String text, Locale locale) {
        return null;
    }

    @Override
    public String print(Date object, Locale locale) {
        return String.valueOf(object.getTime());
    }
}
