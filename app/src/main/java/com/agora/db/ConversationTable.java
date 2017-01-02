package com.agora.db;

/**
 * Created by Ivan on 7/11/15.
 */
public class ConversationTable implements DatabaseTable {


    public static final String TABLE_NAME = "conversation";
    public static final String COLUMN_NAME_ENTRY_ID = "conversationKey";
    public static final String COLUMN_NAME_COMPANY_KEY = "companyKey";
    public static final String COLUMN_NAME_LAST_TEXT = "lastMessage";
    public static final String COLUMN_NAME_NUMBER = "newNumber";
    public static final String COLUMN_NAME_DATE = "settlementDate";

    public ConversationTable() {}

    @Override
    public String getCreateQuery() {
        return "CREATE TABLE "+TABLE_NAME
                +"("+COLUMN_NAME_ENTRY_ID+" LONG PRIMARY KEY, "
                + COLUMN_NAME_COMPANY_KEY + " LONG, "
                +COLUMN_NAME_LAST_TEXT+ " TEXT, "
                +COLUMN_NAME_NUMBER+" INTEGER, "
                +COLUMN_NAME_DATE+" DATETIME DEFAULT CURRENT_TIMESTAMP";
    }

    @Override
    public String getDropQuery() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

}
