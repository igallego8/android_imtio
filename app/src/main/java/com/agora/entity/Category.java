package com.agora.entity;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Ivan on 6/10/15.
 */
public class Category implements Serializable {

    private Long categoryKey;
    private String description;
    private String imageName;

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    private Bitmap image;

    public Category (Long categoryKey, String description, String imageName){
        this.setCategoryKey(categoryKey);
        this.setDescription(description);
        this.imageName=imageName;
    }

    public Category (Long categoryKey){
        this.setCategoryKey(categoryKey);
    }


    public Long getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(Long categoryKey) {
        this.categoryKey = categoryKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String toString (){
        StringBuffer buffer=new StringBuffer();
        buffer.append("category:{");

        if (categoryKey!=null) {
            buffer.append("categoryKey:").append(categoryKey).append(",");
        }
        if (description!=null) {
            buffer.append("description:").append(description).append(",");
        }
        if (imageName!=null && !imageName.trim().equals("")) {
            buffer.append("imageName:").append(imageName);
        }
        buffer.append("}");
        return buffer.toString();

    }


    public JSONObject populateJson(){
        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj.put("categoryKey",categoryKey);
            jsonObj.put("description",description);
            jsonObj.put("imageName", imageName);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObj;
    }
}
