package com.itheima.springmvc.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * SpringMVCת����
 * Converter<S, T>  S��sourceԴ�������ͣ�T��targetĿ����������
 * @author 
 *
 */
public class DateConverter implements Converter<String, Date>  {

	@Override
    public Date convert(String source) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = simpleDateFormat.parse(source);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
