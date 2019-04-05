package com.pan.auctionsystem;

import com.pan.auctionsystem.domin.AuctionItemDao;
import com.pan.auctionsystem.domin.AuctionUserDao;
import com.pan.auctionsystem.domin.ItemOrderDao;
import com.pan.auctionsystem.model.AuctionItem;
import com.pan.auctionsystem.model.AuctionUser;
import com.pan.auctionsystem.util.translate.TimeTranslation;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActionsystemApplicationTests {

    @Autowired
    private TimeTranslation translation;

    @Test
    public void contextLoads() {
        Date date = translation.StringDate2Date("2019-12-10");

        System.out.println(date.getTime());
    }

}
