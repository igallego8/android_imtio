package com.agora.entity;

import com.agora.entity.Client;
import com.agora.entity.Company;
import com.agora.entity.Need;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ivan on 17/09/15.
 */
public class Conversation  implements Serializable{

    private String text;
    private int newNumber;
    private Long CompanyKey;
    private Long needKey;
    private Date creationDate;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getCompanyKey() {
        return CompanyKey;
    }

    public void setCompanyKey(Long companyKey) {
        CompanyKey = companyKey;
    }

    public Long getNeedKey() {
        return needKey;
    }

    public void setNeedKey(Long needKey) {
        this.needKey = needKey;
    }

    public int getNewNumber() {
        return newNumber;
    }

    public void setNewNumber(int newNumber) {
        this.newNumber = newNumber;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
