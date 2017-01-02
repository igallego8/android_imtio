package com.agora.util.listadapter;

/**
 * Created by Ivan on 11/11/15.
 */
public  class Item{

    private final String title;
    private final String description;
    private final Integer icon;

    public Item(String title, String description,Integer icon) {
        this.title = title;
        this.description=description;
        this.icon = icon;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getIcon() {
        return icon;
    }
}