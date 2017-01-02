package com.agora.entity;

import java.io.Serializable;

/**
 * Created by Ivan on 17/09/15.
 */
public class Topic implements Serializable {

    private Long topicKey;
    private String description;

    public Topic(Long topicKey, String description){
        this.topicKey=topicKey;
        this.description=description;
    }

    public Long getTopicKey() {
        return topicKey;
    }

    public void setTopicKey(Long topicKey) {
        this.topicKey = topicKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
