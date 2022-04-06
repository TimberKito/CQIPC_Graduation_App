package com.timberkito.server.converter;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 日期转换
 *
 * @author Timber
 * @date 2022/4/1
 */
@Component
public class DateConverter implements Converter<String, LocalDate> {

    /**
     * 日期格式转换
     *
     * @param s
     * @return java.time.LocalDate
     * @author Timber.Wang
     * @date 2022/4/1 18:47
     */
    @Override
    public LocalDate convert(String s) {
        if (s.isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
