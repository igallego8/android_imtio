package com.agora.entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Ivan on 14/09/15.
 */
public class Need implements Serializable {

    private Long needKey;
    private String description;
    private Date settlementDate;
    private Date expirationDate;
    private ArrayList<Bid> bids=new ArrayList<>();
    private Integer messagesNumber=0;
    private Integer bidsNumber=0;
    //private User user;
    private String type;
    private String image1;
    private String image2;
    private String image3;
    private Category category;
    private Long dealKey;


    public Map<String, Boolean> getPreferences() {
        return preferences;
    }

    public void setPreferences(Map<String, Boolean> preferences) {
        this.preferences = preferences;
    }

    private Map<String,Boolean> preferences= new HashMap<>();

    public Long getDealKey() {
        return dealKey;
    }

    public void setDealKey(Long dealKey) {
        this.dealKey = dealKey;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }



    public Need (){

    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Long getNeedKey() {
        return needKey;
    }

    public void setNeedKey(Long needKey) {
        this.needKey = needKey;
    }

    public ArrayList<Bid> getBids() {
        return bids;
    }

    public void setBids(ArrayList<Bid> bids) {
        this.bids = bids;
    }

    public Integer getMessagesNumber() {
        return messagesNumber;
    }

    public void setMessagesNumber(Integer messagesNumber) {
        this.messagesNumber = messagesNumber;
    }

    public Integer getBidsNumber() {
        return bidsNumber;
    }

    public void setBidsNumber(Integer bidsNumber) {
        this.bidsNumber = bidsNumber;
    }




    public String toString(){
        StringBuffer buffer=new StringBuffer();
        buffer.append("need:{");
        if (needKey!=null) {
            buffer.append("needKey:").append(needKey).append(",");
        }
        buffer.append("description:").append(description).append(",");
        if (expirationDate!=null) {
            buffer.append("expirationDate:").append(expirationDate.getTime()).append(",");
        }
        if (settlementDate!=null) {
            buffer.append("settlementDate:").append(settlementDate.getTime()).append(",");
        }
        if(messagesNumber!=null) {
            buffer.append("messagesNumber:").append(messagesNumber).append(",");
        }
        if (bidsNumber!=null) {
            buffer.append("bidsNumber:").append(bidsNumber).append(",");
        }
        if (category!=null) {
            buffer.append(category.toString()).append(",");
        }

       // if (user!=null && user.getUserKey()!=null) {
         //   buffer.append("userKey:").append(user.getUserKey().toString()).append(",");
        //}

        if (dealKey!=null){
            buffer.append(dealKey).append(",");
        }

        if(image1!=null) {
            buffer.append("image1:").append(image1).append(",");
        }

        if(image2!=null) {
            buffer.append("image2:").append(image2).append(",");
        }
        if(image3!=null) {
            buffer.append("image3:").append(image3).append(",");
        }
        buffer.append("preferences:{");

        int size=preferences.keySet().size();
       for (String  key : preferences.keySet()){
           Boolean value = preferences.get(key);
           buffer.append(key).append(":").append(value);
           size--;
           if (size>0){
               buffer.append(",");
           }
       }
        buffer.append("}").append(",");

        if (type!=null){
            buffer.append("type:").append(type);
        }



        buffer.append("}");
        return buffer.toString();
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


    public JSONObject populateJson(){
        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj.put("needKey",needKey);
            jsonObj.put("description",description);
            //jsonObj.put("expirationDate", expirationDate);
            //jsonObj.put("settlementDate", settlementDate);
            jsonObj.put("messagesNumber",messagesNumber );
            jsonObj.put("bidsNumber", bidsNumber);
            jsonObj.put("category", category.populateJson());
           // jsonObj.put("user", user.populateJson());
           // jsonObj.put("dealKey",dealKey );
            jsonObj.put("image1",image1 );
            jsonObj.put("image2",image2 );
            jsonObj.put("image3",image3 );

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObj;
    }
}
