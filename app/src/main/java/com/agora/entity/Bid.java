package com.agora.entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Ivan on 13/09/15.
 */
public class Bid implements Serializable {

    private Date expirationDate;
    private Date settlementDate;
    private Long bidKey;
    private Need need;
    private User user;
    private Long dealKey;
    private Date serviceDate;
    private Date availableDate;
    private int deliveryDaysAfterDeal;
    private boolean shipToClientDestination;
    private boolean deliveryOnSaleSite;
    private boolean creditCardPayment;
    private  boolean cashPayment;
    private Company  company;
    private String currency;
    private BigDecimal price;
    private String observation;
    private String image1;
    private String image2;
    private String image3;

    public Long getDealKey() {
        return dealKey;
    }

    public void setDealKey(Long dealKey) {
        this.dealKey = dealKey;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Long getBidKey() {
        return bidKey;
    }

    public void setBidKey(Long bidKey) {
        this.bidKey = bidKey;
    }

    public Need getNeed() {
        return need;
    }

    public void setNeed(Need need) {
        this.need = need;
    }

    public Bid(){

    }

    public Bid (Long bidKey,Need need, Date settlementDate,Date expirationDate){
        this.bidKey=bidKey;
        this.setSettlementDate(settlementDate);
        this.expirationDate=expirationDate;
        this.need=need;
    }


    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }


    public Date getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(Date availableDate) {
        this.availableDate = availableDate;
    }

    public boolean isCreditCardPayment() {
        return creditCardPayment;
    }

    public void setCreditCardPayment(boolean creditCardPayment) {
        this.creditCardPayment = creditCardPayment;
    }





    public String toString(){
        StringBuffer buffer=new StringBuffer();
        buffer.append("bid:{");
        if (bidKey!=null) {
            buffer.append("bidKey:").append(bidKey).append(",");
        }
        if (observation!=null) {
            buffer.append("observation:").append(observation).append(",");
        }
        if (image1!=null) {
            buffer.append("image1:").append(image1).append(",");
        }
        if (image2!=null) {
            buffer.append("image2:").append(image1).append(",");
        }
        if (image2!=null) {
            buffer.append("image3:").append(image1).append(",");
        }
        if (expirationDate!=null) {
            buffer.append("expirationDate:").append(expirationDate.getTime()).append(",");
        }
        if (settlementDate !=null) {
            buffer.append("settlementDate:").append(settlementDate.getTime()).append(",");
        }
        if (serviceDate!=null) {
            buffer.append("serviceDate:").append(serviceDate.getTime()).append(",");
        }
        if (availableDate!=null) {
            buffer.append("availableDate:").append(availableDate.getTime()).append(",");
        }
        if (currency !=null) {
            buffer.append("currency:").append(currency).append(",");
        }

        if (price !=null) {
            buffer.append("price:").append(String.valueOf(price)).append(",");
        }

        if (dealKey !=null) {
            buffer.append("dealKey:").append(dealKey).append(",");
        }

        buffer.append("shipToClientDestination:").append(shipToClientDestination).append(",");
        buffer.append("deliveryOnSaleSite:").append(deliveryOnSaleSite).append(",");
        buffer.append("creditCardPayment:").append(creditCardPayment).append(",");
        buffer.append("cashPayment:").append(cashPayment).append(",");
        buffer.append("deliveryDaysAfterDeal:").append(deliveryDaysAfterDeal).append(",");

        if (user !=null) {
            buffer.append("userKey:").append(user.getUserKey()).append(",");
        }

        if (company!=null) {
            buffer.append(company.toString()).append(",");
        }
        if (need!=null) {
            buffer.append(need.toString());
        }
        buffer.append("}");
        return buffer.toString();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
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

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public int getDeliveryDaysAfterDeal() {
        return deliveryDaysAfterDeal;
    }

    public void setDeliveryDaysAfterDeal(int deliveryDaysAfterDeal) {
        this.deliveryDaysAfterDeal = deliveryDaysAfterDeal;
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

    public boolean isCashPayment() {
        return cashPayment;
    }

    public void setCashPayment(boolean cashPayment) {
        this.cashPayment = cashPayment;
    }


    public JSONObject populateJson(){
        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj.put("bidKey",bidKey);
            jsonObj.put("observation",observation);

            jsonObj.put("image1",image1 );
            jsonObj.put("image2",image2 );
            jsonObj.put("image3",image3 );

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObj;
    }
}
