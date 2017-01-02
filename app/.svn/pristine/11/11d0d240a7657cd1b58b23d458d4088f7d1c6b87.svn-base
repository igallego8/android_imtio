package com.agora.data;

/**
 * Created by Ivan on 17/09/15.
 */
public class DataProviderFactory {


    public static  IDataProvider createDataProvider(String className){
        try {
           return (IDataProvider)Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
