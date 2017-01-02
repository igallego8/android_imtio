package com.agora.builder;

import com.agora.entity.BidDetail;
import com.agora.entity.NeedDetail;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ivan on 18/09/15.
 */
public class BidDetailBuilder implements Builder {
    @Override
    public Object build(JSONObject o) throws JSONException {
        String observation= o.optString("observation");
        String image1= o.optString("image1");
        String image2= o.optString("image2");
        String image3= o.optString("image3");
        BidDetail detail = new BidDetail();
        detail.setObservation(observation);
        detail.setImage1(image1);
        detail.setImage2(image2);
        detail.setImage3(image3);
        return detail;
    }
}
