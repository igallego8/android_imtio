package com.agora.db.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.agora.app.AppConfig;
import com.agora.app.AppContext;
import com.agora.db.DatabaseTable;
import com.agora.db.FactoryDBHelper;
import com.agora.entity.Conversation;
import com.agora.entity.Message;
import com.agora.util.UtilProcess;

/**
 * Created by Ivan on 7/11/15.
 */
public class ConversationDAO implements DatabaseTable {


    private FactoryDBHelper helper=AppContext.dbHelper;
    public static final String TABLE_NAME = "conversation";
    public static final String COLUMN_NAME_ENTRY_ID = "conversationKey";
    public static final String COLUMN_NAME_COMPANY_KEY = "companyKey";
    public static final String COLUMN_NAME_NEED_KEY = "needKey";
    public static final String COLUMN_NAME_LAST_TEXT = "lastMessage";
    public static final String COLUMN_NAME_NUMBER = "newNumber";
    public static final String COLUMN_NAME_DATE = "settlementDate";

    public ConversationDAO() {

    }

    @Override
    public String getCreateQuery() {
        return "CREATE TABLE "+TABLE_NAME
                +"("+COLUMN_NAME_ENTRY_ID+" LONG PRIMARY KEY, "
                + COLUMN_NAME_COMPANY_KEY + " LONG, "
                + COLUMN_NAME_NEED_KEY + " LONG, "
                +COLUMN_NAME_LAST_TEXT+ " TEXT, "
                +COLUMN_NAME_NUMBER+" INTEGER, "
                +COLUMN_NAME_DATE+" DATETIME DEFAULT CURRENT_TIMESTAMP)";
    }

    @Override
    public String getDropQuery() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public boolean update(){
        return true;
    }

    public boolean insert(Conversation conversation) {
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_NAME_COMPANY_KEY, conversation.getCompanyKey());
            contentValues.put(COLUMN_NAME_NEED_KEY, conversation.getNeedKey());
            contentValues.put(COLUMN_NAME_LAST_TEXT, conversation.getText());
            contentValues.put(COLUMN_NAME_NUMBER, conversation.getNewNumber());
            contentValues.put(COLUMN_NAME_DATE, UtilProcess.getDateTime(conversation.getCreationDate()));
            db.insert(TABLE_NAME, null, contentValues);
        }finally {
            if (db!=null){
                db.close();
            }
        }
        return true;
    }
}
