package com.agora.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.agora.app.AppConfig;
import com.agora.app.AppContext;
import com.agora.db.DatabaseTable;
import com.agora.db.FactoryDBHelper;
import com.agora.entity.Message;
import com.agora.util.UtilProcess;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 7/11/15.
 */
public class MessageDAO implements DatabaseTable {


    private FactoryDBHelper helper= AppContext.dbHelper;
    public static final String TABLE_NAME = "message";
    public static final String COLUMN_NAME_ENTRY_ID = "messageKey";
    public static final String COLUMN_NAME_CHAT_KEY = "chatKey";
    public static final String COLUMN_NAME_TEXT = "messageText";
    public static final String COLUMN_NAME_STATUS = "status";
    public static final String COLUMN_NAME_INCOMING = "incoming";
    public static final String COLUMN_NAME_DATE = "settlementDate";
    public static final String SELECT_MESSAGE_BY_CHAT_KEY_QUERY = "SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_NAME_CHAT_KEY+" = ?";
    public static final String SELECT_MESSAGE_BY_KEY_QUERY = "SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_NAME_ENTRY_ID+" = ?";

    public static MessageDAO instance;

    public static MessageDAO getInstance(){
        if (instance==null){
            instance= new MessageDAO();
        }
        return instance;
    }
    private MessageDAO() {

    }

    @Override
    public String getCreateQuery() {
        return "CREATE TABLE "+TABLE_NAME
                +"("+COLUMN_NAME_ENTRY_ID+" LONG PRIMARY KEY, "
                + COLUMN_NAME_CHAT_KEY + " INTEGER, "
                +COLUMN_NAME_STATUS+ " TEXT, "
                +COLUMN_NAME_TEXT+" TEXT, "
                +COLUMN_NAME_INCOMING+" INTEGER, "
                +COLUMN_NAME_DATE+" DATETIME DEFAULT CURRENT_TIMESTAMP)";
    }

    @Override
    public String getDropQuery() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public boolean update(Message message){
        SQLiteDatabase db = AppContext.dbHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_NAME_STATUS, message.getStatus());
            db.update(TABLE_NAME, contentValues, COLUMN_NAME_ENTRY_ID+" = ?", new String[]{message.getMessageKey().toString()});

        return true;
    }

    public synchronized boolean insert(Message message) {
            SQLiteDatabase db = AppContext.dbHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_NAME_ENTRY_ID, message.getMessageKey());
            contentValues.put(COLUMN_NAME_CHAT_KEY, message.getChatKey());
            contentValues.put(COLUMN_NAME_TEXT, message.getText());
            contentValues.put(COLUMN_NAME_STATUS, message.getStatus());
            contentValues.put(COLUMN_NAME_INCOMING, message.getIncoming()?1:0);
            contentValues.put(COLUMN_NAME_DATE, UtilProcess.getDateTime(message.getCreationDate()));
            db.insert(TABLE_NAME, null, contentValues);

        return true;
    }

    public ArrayList<Message> fetchMessagesByChatKey(Integer chatKey){
        ArrayList<Message> result = new ArrayList<>();
        Cursor cursor;
        SQLiteDatabase db = AppContext.dbHelper.getWritableDatabase();

            cursor= db.rawQuery(SELECT_MESSAGE_BY_CHAT_KEY_QUERY, new String[]{chatKey.toString()});
            cursor.moveToFirst();
            Message message;
            for (int i = 0; i < cursor.getCount(); i++) {
                message = new Message();
                message.setMessageKey(cursor.getLong(0));
                message.setChatKey(cursor.getInt(1));
                message.setStatus(cursor.getString(2));
                message.setText(cursor.getString(3));
                message.setIncoming(cursor.getInt(4) > 0);
                message.setCreationDate(UtilProcess.getDateTime(cursor.getString(5)));
                result.add(message);
                cursor.moveToNext();
            }


        return result;
    }

    public synchronized Message fetchMessagesByKey(Long messageKey){
        Cursor cursor;
        SQLiteDatabase db = AppContext.dbHelper.getWritableDatabase();

            cursor= db.rawQuery(SELECT_MESSAGE_BY_KEY_QUERY, new String[]{messageKey.toString()});
            cursor.moveToFirst();
            Message message=null;
            if (cursor.getCount()>0) {
                message = new Message();
                message.setMessageKey(cursor.getLong(0));
                message.setChatKey(cursor.getInt(1));
                message.setStatus(cursor.getString(2));
                message.setText(cursor.getString(3));
                message.setIncoming(cursor.getInt(4) > 0);
                message.setCreationDate(UtilProcess.getDateTime(cursor.getString(5)));
            }
            return message;


    }
}
