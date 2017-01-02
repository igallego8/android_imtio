package com.agora.bid;

import com.agora.app.AppConfig;
import com.agora.entity.Bid;
import com.agora.entity.Need;

import java.util.List;

/**
 * Created by Ivan on 11/02/16.
 */
public class BidNeedListHandler implements GenericListHandler<Bid> {

    private Long need;

    public BidNeedListHandler(Long need){
        this.need=need;
    }


    @Override
    public List<Bid> list() {
        List<Bid> result= AppConfig.dataProvider.fetchBidByNeedKey(need);
        return result;
    }
}
