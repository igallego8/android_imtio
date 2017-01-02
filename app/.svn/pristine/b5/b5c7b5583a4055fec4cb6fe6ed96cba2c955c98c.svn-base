package com.agora.db;

import android.provider.BaseColumns;

/**
 * Created by Ivan on 7/11/15.
 */
public class MessageTable implements DatabaseTable {


    public static final String TABLE_NAME = "message";
    public static final String COLUMN_NAME_ENTRY_ID = "messageKey";
    public static final String COLUMN_NAME_USER_KEY = "userKey";
    public static final String COLUMN_NAME_STATUS = "status";
    public static final String COLUMN_NAME_INCOMING = "incoming";
    public static final String COLUMN_NAME_DATE = "settlementDate";

    public MessageTable() {}

    @Override
    public String getCreateQuery() {
        return "CREATE TABLE "+TABLE_NAME
                +"("+COLUMN_NAME_ENTRY_ID+" LONG PRIMARY KEY, "
                + COLUMN_NAME_USER_KEY + " LONG, "
                +COLUMN_NAME_STATUS+ " TEXT, "
                +COLUMN_NAME_INCOMING+" BOOLEAN, "
                +COLUMN_NAME_DATE+" DATETIME DEFAULT CURRENT_TIMESTAMP";
    }

    @Override
    public String getDropQuery() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

}
