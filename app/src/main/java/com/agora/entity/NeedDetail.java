package com.agora.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Ivan on 19/09/15.
 */
public class NeedDetail implements Serializable {

    private String observation;
    private String image1;
    private String image2;
    private String image3;
    private boolean shippingService;
    //private boolean creditCardPayment;
    private boolean paymentOnSite;


    private boolean shipToClientDestination;
    private int shipToClientDestinationWeight;
    private boolean deliveryOnSaleSite;
    private int deliveryOnSaleSiteWeight;
    private boolean bestOffer;
    private int bestOfferWeight;
    private boolean creditCardPayment;
    private int creditCardPaymentWeight;
    private  boolean cashPayment;
    private int cashPaymentWeight;
    private BigDecimal maxAmount;
    private int maxAmountWeight;

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public boolean isShippingService() {
        return shippingService;
    }

    public void setShippingService(boolean shippingService) {
        this.shippingService = shippingService;
    }

    public boolean isCreditCardPayment() {
        return creditCardPayment;
    }

    public void setCreditCardPayment(boolean creditCardPayment) {
        this.creditCardPayment = creditCardPayment;
    }

    public boolean isPaymentOnSite() {
        return paymentOnSite;
    }

    public void setPaymentOnSite(boolean paymentOnsite) {
        this.paymentOnSite = paymentOnSite;
    }



    public String toString(){
        StringBuffer buffer=new StringBuffer();
        buffer.append("needDetail:{");

        if (observation!=null) {
            buffer.append("observation:").append(observation).append(",");
        }
        buffer.append("shippingService:").append(shippingService).append(",");
        buffer.append("creditCardPayment:").append(isCreditCardPayment()).append(",");
        buffer.append("paymentOnSite:").append(paymentOnSite).append(",");
        if (image1!=null) {
            buffer.append("image1:").append(image1).append(",");
        }
        if (image2!=null) {
            buffer.append("image2:").append(image1).append(",");
        }
        if (image2!=null) {
            buffer.append("image3:").append(image1);
        }

        buffer.append("}");
        return buffer.toString();
    }

    public boolean isShipToClientDestination() {
        return shipToClientDestination;
    }

    public void setShipToClientDestination(boolean shipToClientDestination) {
        this.shipToClientDestination = shipToClientDestination;
    }

    public boolean isDeliveryOnSaleSite() {
        return deliveryOnSaleSite;
    }

    public void setDeliveryOnSaleSite(boolean deliveryOnSaleSite) {
        this.deliveryOnSaleSite = deliveryOnSaleSite;
    }

    public boolean isBestOffer() {
        return bestOffer;
    }

    public void setBestOffer(boolean bestOffer) {
        this.bestOffer = bestOffer;
    }

    public boolean isCashPayment() {
        return cashPayment;
    }

    public void setCashPayment(boolean cashPayment) {
        this.cashPayment = cashPayment;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public int getDeliveryOnSaleSiteWeight() {
        return deliveryOnSaleSiteWeight;
    }

    public void setDeliveryOnSaleSiteWeight(int deliveryOnSaleSiteWeight) {
        this.deliveryOnSaleSiteWeight = deliveryOnSaleSiteWeight;
    }

    public int getCreditCardPaymentWeight() {
        return creditCardPaymentWeight;
    }

    public void setCreditCardPaymentWeight(int creditCardPaymentWeight) {
        this.creditCardPaymentWeight = creditCardPaymentWeight;
    }

    public int getCashPaymentWeight() {
        return cashPaymentWeight;
    }

    public void setCashPaymentWeight(int cashPaymentWeight) {
        this.cashPaymentWeight = cashPaymentWeight;
    }

    public int getMaxAmountWeight() {
        return maxAmountWeight;
    }

    public void setMaxAmountWeight(int maxAmountWeight) {
        this.maxAmountWeight = maxAmountWeight;
    }

    public int getShipToClientDestinationWeight() {
        return shipToClientDestinationWeight;
    }

    public void setShipToClientDestinationWeight(int shipToClientDestinationWeight) {
        this.shipToClientDestinationWeight = shipToClientDestinationWeight;
    }

    public int getBestOfferWeight() {
        return bestOfferWeight;
    }

    public void setBestOfferWeight(int bestOfferWeight) {
        this.bestOfferWeight = bestOfferWeight;
    }
}
