package com.pan.auctionsystem.UserBase.service;

import com.pan.auctionsystem.domin.AuctionItemDao;
import com.pan.auctionsystem.model.AuctionItem;
import com.pan.auctionsystem.util.myInterface.domin.CRUDDao;
import com.pan.auctionsystem.util.myInterface.service.CRUDService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("auctionItemService")
public class AuctionItemService implements CRUDService<AuctionItem> {

    @Resource(name = "auctionItemDao")
    private AuctionItemDao dao;

    @Override
    public List<AuctionItem> selectAll() {
        return dao.selectAll();
    }

    @Override
    public List<AuctionItem> selectByCondition(AuctionItem model) {
        return null;
    }

    @Override
    public AuctionItem findOneById(int id) {
        return null;
    }

    @Override
    public int updateOneByModel(AuctionItem model) {
        return 0;
    }

    @Override
    public int deleteOneById(int id) {
        return 0;
    }

    @Override
    public int addOneByModel(AuctionItem model) {
        return 0;
    }
}
