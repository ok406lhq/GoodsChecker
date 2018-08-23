package com.wolf.zero.greenroad.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {

    public static String formatDateToMd(String str) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sf2 = new SimpleDateFormat("MM-dd");
        String formatStr = "";
        try {
            formatStr = sf2.format(sf1.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatStr;
    }
}
