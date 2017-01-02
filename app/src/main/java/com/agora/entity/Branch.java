package com.agora.entity;

import android.location.Location;

import java.io.Serializable;

/**
 * Created by Ivan on 17/09/15.
 */
public class Branch implements Serializable {

    private Long brachKey;
    private Location location;

    public Long getBrachKey() {
        return brachKey;
    }

    public void setBrachKey(Long brachKey) {
        this.brachKey = brachKey;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
