package com.agora.bid;

import com.agora.app.AppConfig;
import com.agora.entity.Bid;
import com.agora.entity.Deal;
import com.agora.entity.Need;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 11/02/16.
 */
public class BidDealListHandler implements GenericListHandler<Bid> {

    private Deal deal;

    public BidDealListHandler(Deal deal){
        this.deal=deal;
    }

    @Override
    public List<Bid> list() {
        Bid bid = AppConfig.dataProvider.findBidByBidKey(deal.getBid().getBidKey());
        List<Bid> result= new ArrayList<>();
        result.add(bid);
        return result;
    }
}
