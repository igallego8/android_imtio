package com.agora.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ivan on 15/09/15.
 */
public class Message implements Serializable {

    private char type;
    private String status;
    private String text;
    private Date creationDate;
    private Boolean incoming;
    private Integer chatKey;
    private Long messageKey;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getIncoming() {
        return incoming;
    }

    public void setIncoming(Boolean incoming) {
        this.incoming = incoming;
    }

    public Long getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(Long messageKey) {
        this.messageKey = messageKey;
    }

    public String getTextInfo(){
        StringBuffer bf =  new StringBuffer();
        bf.append(this.getIncoming().toString()+";"+text+";"+creationDate);
        return bf.toString();
    }

    public Integer getChatKey() {
        return chatKey;
    }

    public void setChatKey(Integer chatKey) {
        this.chatKey = chatKey;
    }
}
