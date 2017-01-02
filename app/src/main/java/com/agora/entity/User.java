package com.agora.entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ivan on 17/09/15.
 */
public class User implements Serializable {

    private Long userKey;
    private String name;
    private String lastName;
    private Auth auth;
    private String email;
    private Date birthDate;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String image;

    public Long getUserKey() {
        return userKey;
    }

    public void setUserKey(Long userKey) {
        this.userKey = userKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public  String toString(){
        StringBuffer buffer=new StringBuffer();
        buffer.append("user:{");
        if (userKey!=null) {
            buffer.append("userKey:").append(userKey).append(",");
        }
        if (name!=null) {
            buffer.append("name:").append(name).append(",");
        }
        if (lastName!=null) {
            buffer.append("lastName:").append(lastName).append(",");
        }
        if (email!=null) {
            buffer.append("email:").append(email).append(",");
        }
        if (birthDate!=null) {
            buffer.append("birthDate:").append(birthDate).append(",");
        }
        if (auth!=null) {
            buffer.append(auth);
        }
        buffer.append("}");
        return buffer.toString();
    }


    public JSONObject populateJson(){
        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj.put("userKey",userKey);
            jsonObj.put("name",name);
            //jsonObj.put("expirationDate", expirationDate);
            //jsonObj.put("settlementDate", settlementDate);
            jsonObj.put("lastName",lastName );
            jsonObj.put("email", email);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObj;
    }
}
