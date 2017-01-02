package com.agora.entity;

import java.io.Serializable;

/**
 * Created by Ivan on 23/09/15.
 */
public enum NeedType  implements Serializable{
    PRODUCT('P'),
    SERVICE('S');

    private char type;

    NeedType(char type){
        this.type=type;
    }

    public char getType(){
        return type;
    }

    public String toString(){
        return Character.toString(type);
    }

}
