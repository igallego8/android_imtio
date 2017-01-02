package com.agora.app;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 16/02/16.
 */
public class ListGlobal extends Application {

    List list =  new ArrayList<>();

    public List getList(){
        return list;
    }

    public void setList(List list){
        this.list=list;
    }
}
