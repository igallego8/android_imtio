package com.agora.util;

/**
 * Created by Ivan on 17/11/15.
 */
public class SQLHelper {


    public static String inExpression(Object ... params){
        if (params!=null & params.length!= 0){
            StringBuffer bf = new StringBuffer();
            bf.append(" IN (");
            for (int index=0 ; index<params.length;index++){
                if (index!=0){
                    bf.append(",");
                }
                bf.append("?");
            }
            bf.append(")");
            return bf.toString();
        }
        return null;
    }
}
