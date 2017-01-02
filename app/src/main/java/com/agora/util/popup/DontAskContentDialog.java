package com.agora.util.popup;

import java.io.Serializable;

/**
 * Created by Ivan on 28/12/15.
 */
public class DontAskContentDialog implements Serializable {

    private String url;
    private int resource;
    private String text;

    public DontAskContentDialog(int resource,String url,String text){
        this.setUrl(url);
        this.setResource(resource);
        this.text=text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
