package com.agora.need.preferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ivan on 29/02/16.
 */
public class PreferenceCardDTO {

    private int titleIcon;
    private String title;
    private int titleIconColumn;
    private String titleColumn;

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public int getTitleIcon() {
        return titleIcon;
    }

    public void setTitleIcon(int titleIcon) {
        this.titleIcon = titleIcon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTitleIconColumn() {
        return titleIconColumn;
    }

    public void setTitleIconColumn(int titleIconColumn) {
        this.titleIconColumn = titleIconColumn;
    }

    public String getTitleColumn() {
        return titleColumn;
    }

    public void setTitleColumn(String titleColumn) {
        this.titleColumn = titleColumn;
    }

    private Map<String,String> parameters= new HashMap<>();

}
