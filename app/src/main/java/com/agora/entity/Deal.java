package com.agora.entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Ivan on 18/09/15.
 */
public class Deal implements Serializable {

    private Long dealKey;
    private String status;
    private Bid bid;
    private Need need;
    private Long date;
    private Company company;
    private User user;

    public Need getNeed() {
        return need;
    }

    public void setNeed(Need need) {
        this.need = need;
    }

    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }

    public Long getDealKey() {
        return dealKey;
    }

    public void setDealKey(Long dealKey) {
        this.dealKey = dealKey;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("deal:{");
        if (dealKey != null) {
            buffer.append("dealKey:").append(dealKey).append(",");
        }
        if (status!=null) {
            buffer.append("status:").append(status).append(",");
        }

        if (date != null) {
            buffer.append(date.toString()).append(",");
        }

        if (company != null) {
            buffer.append(company.toString()).append(",");
        }
        if (user != null) {
            buffer.append(user.toString()).append(",");
        }
        if (need != null) {
            buffer.append("needKey:").append(need.getNeedKey()).append(",");
        }
        if (bid != null) {
            buffer.append("bidKey:").append(bid.getBidKey());
        }

        buffer.append("}");
        return buffer.toString();
    }


    public JSONObject populateJson(){
        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj.put("bidKey",dealKey);
            jsonObj.put("status",status);
            //jsonObj.put("expirationDate", expirationDate);
            //jsonObj.put("settlementDate", settlementDate);
            if (bid!=null)
            jsonObj.put("bid",bid.populateJson());
            if (need!=null)
            jsonObj.put("need", need.populateJson());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObj;
    }


}
