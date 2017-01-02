package com.agora.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ivan on 12/11/15.
 */
public class Chat implements Serializable {

    private Integer chatKey;
    private Date settlementDate;
    private Integer qtyNewMessages;
    private String lastTextMessage;
    private Long userKey;
    private Long needKey;


    public Integer getChatKey() {
        return chatKey;
    }

    public void setChatKey(Integer chatKey) {
        this.chatKey = chatKey;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }


    public Integer getQtyNewMessages() {
        return qtyNewMessages;
    }

    public void setQtyNewMessages(Integer qtyNewMessages) {
        this.qtyNewMessages = qtyNewMessages;
    }

    public String getLastTextMessage() {
        return lastTextMessage;
    }

    public void setLastTextMessage(String lastTextMessage) {
        this.lastTextMessage = lastTextMessage;
    }

    public Long getUserKey() {
        return userKey;
    }

    public void setUserKey(Long userKey) {
        this.userKey = userKey;
    }

    public Long getNeedKey() {
        return needKey;
    }

    public void setNeedKey(Long needKey) {
        this.needKey = needKey;
    }
}
