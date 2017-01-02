package com.agora.builder;

import android.os.Build;

/**
 * Created by Ivan on 18/09/15.
 */
public class BuilderFactory {

    public static Builder getBuilder(String className)  {
        Builder b= null;
        try {
            b = (Builder)Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return b;
    }
}
