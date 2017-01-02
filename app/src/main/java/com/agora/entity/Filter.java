package com.agora.entity;

import java.io.Serializable;

/**
 * Created by Ivan on 19/11/15.
 */
public abstract class  Filter implements Serializable {

    private int weight;
    private String name;
    private String value;
    private int icon;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
