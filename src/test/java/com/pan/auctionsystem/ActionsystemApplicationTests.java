package com.pan.auctionsystem;

import com.pan.auctionsystem.domin.ItemOrderDao;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActionsystemApplicationTests {

    @Autowired
    @Qualifier("stringRedisTemplate")
    @Getter
    @Setter
    private RedisTemplate template;

    @Test
    public void contextLoads() {
//        Long ll = System.currentTimeMillis();
//        System.out.println(ll.toString());
//
//        Date date = new Date(ll);
//        System.out.println(date);
//        System.out.println(date.getTime());
//
//        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
//
//        String dateString = sdf.format(date);
//        System.out.println(dateString);
//
//        try {
//            Long lll = sdf.parse(dateString).getTime();
//            System.out.println(lll);
//        }catch (Exception e){
//            e.printStackTrace();
//        }


//        schedule.putActionScheduleInNextHour();
//        dao.addNewOrder(2, 1, Long.valueOf(1), 5);
//        System.out.println(templates.opsForValue().get("testvalue").toString());

    }

}
