package com.agora.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.agora.app.AppContext;
import com.agora.db.DatabaseTable;
import com.agora.db.FactoryDBHelper;
import com.agora.entity.Chat;
import com.agora.entity.ChatUser;
import com.agora.entity.Conversation;
import com.agora.util.SQLHelper;
import com.agora.util.UtilProcess;

import org.slf4j.helpers.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 7/11/15.
 */
public class ChatDAO implements DatabaseTable {


    private FactoryDBHelper helper=AppContext.dbHelper;
    public static final String TABLE_NAME = "chat";
    public static final String COLUMN_NAME_ENTRY_ID = "chatKey";
    public static final String COLUMN_NAME_LAST_MESSAGE = "lastTextMessage";
    public static final String COLUMN_NAME_QTY_NEW_MESSAGES = "qtyNewMessages";
    public static final String COLUMN_NAME_USER_KEY = "userKey";
    public static final String COLUMN_NAME_NEED_KEY = "needKey";
    public static final String COLUMN_NAME_DATE = "settlementDate";

    public static final String SELECT_CHAT_IN_KEY_QUERY = "SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_NAME_ENTRY_ID;
    public static final String SELECT_CHAT_BY_KEY_QUERY = "SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_NAME_ENTRY_ID+ " = ?";
    public static final String SELECT_ALL_QUERY = "SELECT * FROM "+TABLE_NAME;

    public static final String SELECT_BY_USER_KEY_QUERY = "SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_NAME_USER_KEY +" = ?";

    public static final String SELECT_BY_USER_KEY_NEED_KEY_QUERY = "SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_NAME_USER_KEY +" = ? AND "+COLUMN_NAME_NEED_KEY+" = ?";


    public static final String SELECT_BY_NEED_KEY_QUERY = "SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_NAME_NEED_KEY +" = ?";


    public static ChatDAO instance;

    private ChatDAO() {

    }

    public static ChatDAO getInstance(){
        if (instance==null){
            instance= new ChatDAO();
        }
        return instance;
    }

    @Override
    public String getCreateQuery() {
        return "CREATE TABLE "+TABLE_NAME
                +"("+COLUMN_NAME_ENTRY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1, "
                + COLUMN_NAME_LAST_MESSAGE + " TEXT, "
                +COLUMN_NAME_QTY_NEW_MESSAGES+" INTEGER, "
                +COLUMN_NAME_USER_KEY+" LONG, "
                +COLUMN_NAME_NEED_KEY+" LONG, "
                +COLUMN_NAME_DATE+" DATETIME DEFAULT CURRENT_TIMESTAMP)";
    }

    @Override
    public String getDropQuery() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public boolean update(Chat chat){
        SQLiteDatabase db = AppContext.dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_QTY_NEW_MESSAGES, chat.getQtyNewMessages());
        contentValues.put(COLUMN_NAME_LAST_MESSAGE,chat.getLastTextMessage());
        db.update(TABLE_NAME, contentValues, COLUMN_NAME_ENTRY_ID+" = ?", new String[]{chat.getChatKey().toString()});

        return true;
    }

    public Long insert(Chat chat) {
        SQLiteDatabase db = AppContext.dbHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_NAME_LAST_MESSAGE, chat.getLastTextMessage());
            contentValues.put(COLUMN_NAME_QTY_NEW_MESSAGES, chat.getQtyNewMessages());
            contentValues.put(COLUMN_NAME_USER_KEY, chat.getUserKey());
            contentValues.put(COLUMN_NAME_NEED_KEY, chat.getNeedKey());
            contentValues.put(COLUMN_NAME_DATE, UtilProcess.getDateTime(chat.getSettlementDate()));
            Long key= db.insert(TABLE_NAME, null, contentValues);
            chat.setChatKey(key.intValue());
            return key;


    }

    public synchronized List<Chat> fetchChatByUserKey(Long userKey){
        List<Chat> result = new ArrayList<>();
        Cursor cursor;
        SQLiteDatabase db = AppContext.dbHelper.getWritableDatabase();
        cursor= db.rawQuery(SELECT_BY_USER_KEY_QUERY, new String[]{userKey.toString()});
        cursor.moveToFirst();
        Chat chat;
        for (int i = 0; i < cursor.getCount(); i++) {
            chat = new Chat();
            chat.setChatKey(cursor.getInt(0));
            chat.setLastTextMessage(cursor.getString(1));
            chat.setQtyNewMessages(cursor.getInt(2));
            chat.setUserKey(cursor.getLong(3));
            chat.setNeedKey(cursor.getLong(4));
            chat.setSettlementDate(UtilProcess.getDateTime(cursor.getString(5)));
            result.add(chat);
            cursor.moveToNext();
        }
        return result;
    }

    public List<Chat> fetchChatByNeedKey(Long userKey){
        List<Chat> result = new ArrayList<>();
        Cursor cursor;
        SQLiteDatabase db = AppContext.dbHelper.getWritableDatabase();
        cursor= db.rawQuery(SELECT_BY_NEED_KEY_QUERY, new String[]{userKey.toString()});
        cursor.moveToFirst();
        Chat chat;
        for (int i = 0; i < cursor.getCount(); i++) {
            chat = new Chat();
            chat.setChatKey(cursor.getInt(0));
            chat.setLastTextMessage(cursor.getString(1));
            chat.setQtyNewMessages(cursor.getInt(2));
            chat.setUserKey(cursor.getLong(3));
            chat.setNeedKey(cursor.getLong(4));
            chat.setSettlementDate(UtilProcess.getDateTime(cursor.getString(5)));
            result.add(chat);
            cursor.moveToNext();
        }
        return result;
    }


    public ArrayList<Chat> fetchChatsByInChatKey(List<Integer> listKey){
        ArrayList<Chat> result = new ArrayList<>();
        Cursor cursor;
        SQLiteDatabase db = AppContext.dbHelper.getWritableDatabase();
        String[] kies= UtilProcess.convertToArrayString(listKey);
        cursor= db.rawQuery(SELECT_CHAT_IN_KEY_QUERY+ SQLHelper.inExpression(kies), kies);
        cursor.moveToFirst();
        Chat chat;
        for (int i = 0; i < cursor.getCount(); i++) {
            chat = new Chat();
            chat.setChatKey(cursor.getInt(0));
            chat.setLastTextMessage(cursor.getString(1));
            chat.setQtyNewMessages(cursor.getInt(2));
            chat.setUserKey(cursor.getLong(3));
            chat.setNeedKey(cursor.getLong(4));
            chat.setSettlementDate(UtilProcess.getDateTime(cursor.getString(5)));
            result.add(chat);
            cursor.moveToNext();
        }
        return result;
    }

    public Chat fetchChatByChatKey(Integer chatKey){
        Cursor cursor;
        SQLiteDatabase db = AppContext.dbHelper.getWritableDatabase();
        cursor= db.rawQuery(SELECT_CHAT_BY_KEY_QUERY, new String[]{chatKey.toString()});
        cursor.moveToFirst();
        Chat chat=null;
        if (cursor.getCount()==1) {
            chat = new Chat();
            chat.setChatKey(cursor.getInt(0));
            chat.setLastTextMessage(cursor.getString(1));
            chat.setQtyNewMessages(cursor.getInt(2));
            chat.setUserKey(cursor.getLong(3));
            chat.setNeedKey(cursor.getLong(4));
            chat.setSettlementDate(UtilProcess.getDateTime(cursor.getString(5)));
        }
        return chat;
    }

    public List<Chat> fetchAll(){
        List<Chat> result = new ArrayList<>();
        Cursor cursor;
        SQLiteDatabase db = AppContext.dbHelper.getWritableDatabase();

            cursor= db.rawQuery(SELECT_ALL_QUERY,null);
            cursor.moveToFirst();
            Chat chat;
            for (int i = 0; i < cursor.getCount(); i++) {
                chat = new Chat();
                chat.setChatKey(cursor.getInt(0));
                chat.setLastTextMessage(cursor.getString(1));
                chat.setQtyNewMessages(cursor.getInt(2));
                chat.setUserKey(cursor.getLong(3));
                chat.setNeedKey(cursor.getLong(4));
                chat.setSettlementDate(UtilProcess.getDateTime(cursor.getString(5)));
                result.add(chat);
                cursor.moveToNext();
            }

        return result;
    }

    public synchronized List<Chat> fetchChatByUserKeyNeedKey(Long userKey, Long needKey){
        List<Chat> result = new ArrayList<>();
        Cursor cursor;
        SQLiteDatabase db = AppContext.dbHelper.getWritableDatabase();
        cursor= db.rawQuery(SELECT_BY_USER_KEY_NEED_KEY_QUERY, new String[]{userKey.toString(),needKey.toString()});
        cursor.moveToFirst();
        Chat chat;
        for (int i = 0; i < cursor.getCount(); i++) {
            chat = new Chat();
            chat.setChatKey(cursor.getInt(0));
            chat.setLastTextMessage(cursor.getString(1));
            chat.setQtyNewMessages(cursor.getInt(2));
            chat.setUserKey(cursor.getLong(3));
            chat.setNeedKey(cursor.getLong(4));
            chat.setSettlementDate(UtilProcess.getDateTime(cursor.getString(5)));
            result.add(chat);
            cursor.moveToNext();
        }
        return result;
    }

}
