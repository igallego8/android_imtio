package com.agora.entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Ivan on 17/09/15.
 */
public class Auth implements Serializable {
    private String id;
    private String password;
    private String idGCM;


    public Auth(){

    }

    public Auth(String id, String password,String idGCM){
        this.id=id;
        this.password=password;
        this.idGCM=idGCM;

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdGCM() {
        return idGCM;
    }

    public void setIdGCM(String idGCM) {
        this.idGCM = idGCM;
    }

    public String toString(){
        StringBuffer buffer=new StringBuffer();
        buffer.append("'auth':{");
        if (id!=null) {
            buffer.append("'id':").append(id).append(",");
        }
        if (password!=null) {
            buffer.append("'password':").append(password).append(",");
        }
        if (idGCM !=null) {
            buffer.append("'idGCM':").append(idGCM);
        }
        buffer.append("}");
        return buffer.toString();
    }


    public JSONObject populateJson(){
        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj.put("id",id);
            jsonObj.put("password",password);
            jsonObj.put("idGCM", idGCM);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObj;
    }
}
