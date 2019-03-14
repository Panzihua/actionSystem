package com.pan.auctionsystem;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActionsystemApplicationTests {

    @Test
    public void contextLoads() {
        Long ll = System.currentTimeMillis();
        System.out.println(ll.toString());

        Date date = new Date(ll);
        System.out.println(date);
        System.out.println(date.getTime());

        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

        String dateString = sdf.format(date);
        System.out.println(dateString);

        try {
            Long lll = sdf.parse(dateString).getTime();
            System.out.println(lll);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
