package com.agora.entity;

import java.io.Serializable;

/**
 * Created by Ivan on 17/09/15.
 */
public class ContacInfo implements Serializable {
    private Long contacInfoKey;
    private String email;
    private String address;
    private String mobile;

    public Long getContacInfoKey() {
        return contacInfoKey;
    }

    public void setContacInfoKey(Long contacInfoKey) {
        this.contacInfoKey = contacInfoKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
