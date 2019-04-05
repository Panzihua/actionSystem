package com.pan.auctionsystem.util.translate;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class TimeTranslation {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Long StringDate2Long(String stringDate){
        return StringDate2Date(stringDate).getTime();
    }

    public int countAge(String birth){
        Calendar temp = Calendar.getInstance();
        int now = temp.get(Calendar.YEAR);

        temp.setTime(StringDate2Date(birth));
        int small = temp.get(Calendar.YEAR);

        return now - small;
    }

    public Date StringDate2Date(String str){
        Date date = null;
        try {
            date = dateFormat.parse(str);

            return date;
        }catch (ParseException e){
            e.printStackTrace();
        }

        return date;
    }

}
