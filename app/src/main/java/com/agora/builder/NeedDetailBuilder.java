package com.agora.builder;

import com.agora.entity.NeedDetail;
import com.agora.entity.Topic;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by Ivan on 18/09/15.
 */
public class NeedDetailBuilder implements Builder {
    @Override
    public Object build(JSONObject o) throws JSONException {
        String observation= o.optString("observation");
        String image1= o.optString("image1");
        String image2= o.optString("image2");
        String image3= o.optString("image3");
        boolean shippingService= o.optBoolean("shippingService");
        boolean paymentOnSiteService= o.optBoolean("paymentOnSite");
        boolean paymentCreditCard= o.optBoolean("creditCardPayment");
        NeedDetail detail = new NeedDetail();
        detail.setObservation(observation);
        detail.setImage1(image1);
        detail.setImage2(image2);
        detail.setImage3(image3);
        detail.setCreditCardPayment(paymentCreditCard);
        detail.setPaymentOnSite(paymentOnSiteService);
        detail.setShippingService(shippingService);
        return detail;
    }
}
