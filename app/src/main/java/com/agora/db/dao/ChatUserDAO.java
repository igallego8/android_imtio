package com.agora.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.agora.app.AppContext;
import com.agora.db.DatabaseTable;
import com.agora.db.FactoryDBHelper;
import com.agora.entity.Chat;
import com.agora.entity.ChatUser;
import com.agora.entity.Message;
import com.agora.util.SQLHelper;
import com.agora.util.UtilProcess;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 7/11/15.
 */
public class ChatUserDAO implements DatabaseTable {


    private FactoryDBHelper helper=AppContext.dbHelper;
    public static final String TABLE_NAME = "chatuser";
    public static final String COLUMN_NAME_USER_KEY = "userKey";
    public static final String COLUMN_NAME_CHAT_KEY = "chatKey";
    public static final String COLUMN_NAME_NEED_KEY = "needKey";

    public static final String SELECT_BY_USER_KEY_QUERY = "SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_NAME_USER_KEY +" = ?";

    public static final String SELECT_IN_USER_KEY_QUERY = "SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_NAME_USER_KEY;

    public static final String SELECT_BY_CHAT_KEY_QUERY = "SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_NAME_CHAT_KEY +" = ?";

    public static final String SELECT_BY_CHAT_KEY_USER_KEY_QUERY = "SELECT * FROM "+TABLE_NAME+
            " WHERE "+COLUMN_NAME_CHAT_KEY +" = ? AND "+ SELECT_BY_USER_KEY_QUERY + " = ?";

    public static final String SELECT_BY_NEED_KEY_QUERY = "SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_NAME_NEED_KEY +" = ?";

    public static ChatUserDAO instance;

    private ChatUserDAO() {

    }


    public static ChatUserDAO getInstance(){
        if (instance==null){
            instance= new ChatUserDAO();
        }
        return instance;
    }


    @Override
    public String getCreateQuery() {
        return "CREATE TABLE "+TABLE_NAME
                +"("
                + COLUMN_NAME_USER_KEY + " LONG, "
                +COLUMN_NAME_CHAT_KEY+" LONG, "
                +COLUMN_NAME_NEED_KEY+" LONG, "
                +"PRIMARY KEY ("+COLUMN_NAME_USER_KEY+","+COLUMN_NAME_CHAT_KEY+","+COLUMN_NAME_NEED_KEY+"))";
    }

    @Override
    public String getDropQuery() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public boolean update(ChatUser chatUser){
        return true;
    }

    public Long insert(ChatUser chatUser) {
        SQLiteDatabase db = AppContext.dbHelper.getWritableDatabase();
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_NAME_USER_KEY, chatUser.getUserKey());
            contentValues.put(COLUMN_NAME_CHAT_KEY, chatUser.getChatKey());
            contentValues.put(COLUMN_NAME_NEED_KEY, chatUser.getNeedKey());
            return db.insert(TABLE_NAME, null, contentValues);
        }finally {
            if (db!=null){
                db.close();
            }
        }

    }

    public List<ChatUser> fetchChatUserByUserKey(Long userKey){
        List<ChatUser> result = new ArrayList<>();
        Cursor cursor;
        SQLiteDatabase db = AppContext.dbHelper.getWritableDatabase();
        try {
            cursor= db.rawQuery(SELECT_BY_USER_KEY_QUERY, new String[]{userKey.toString()});
            cursor.moveToFirst();
            ChatUser chatUser;
            for (int i = 0; i < cursor.getCount(); i++) {
                chatUser = new ChatUser();
                chatUser.setUserKey(cursor.getLong(0));
                chatUser.setChatKey(cursor.getLong(1));
                chatUser.setNeedKey(cursor.getLong(2));
                result.add(chatUser);
                cursor.moveToNext();
            }
        }finally {
            if (db!=null){
                db.close();
            }
        }
        return result;
    }

    public List<ChatUser> fetchChatUserByNeedKey(Long userKey){
        List<ChatUser> result = new ArrayList<>();
        Cursor cursor;
        SQLiteDatabase db = AppContext.dbHelper.getWritableDatabase();
        try {
            cursor= db.rawQuery(SELECT_BY_NEED_KEY_QUERY, new String[]{userKey.toString()});
            cursor.moveToFirst();
            ChatUser chatUser;
            for (int i = 0; i < cursor.getCount(); i++) {
                chatUser = new ChatUser();
                chatUser.setUserKey(cursor.getLong(0));
                chatUser.setChatKey(cursor.getLong(1));
                chatUser.setNeedKey(cursor.getLong(2));
                result.add(chatUser);
                cursor.moveToNext();
            }
        }finally {
            if (db!=null){
                db.close();
            }
        }
        return result;
    }

    public List<ChatUser> fetchChatUserByChatKey(Integer chatKey){
        List<ChatUser> result = new ArrayList<>();
        Cursor cursor;
        SQLiteDatabase db = AppContext.dbHelper.getWritableDatabase();
        try {
            cursor= db.rawQuery(SELECT_BY_CHAT_KEY_QUERY, new String[]{chatKey.toString()});
            cursor.moveToFirst();
            ChatUser chatUser;
            for (int i = 0; i < cursor.getCount(); i++) {
                chatUser = new ChatUser();
                chatUser.setUserKey(cursor.getLong(0));
                chatUser.setChatKey(cursor.getLong(1));
                chatUser.setNeedKey(cursor.getLong(2));
                result.add(chatUser);
                cursor.moveToNext();
            }
        }finally {
            if (db!=null){
                db.close();
            }
        }
        return result;
    }


    public List<ChatUser> fetchChatUserInUserKey(List<Long> listKey){
        List<ChatUser> result = new ArrayList<>();
        Cursor cursor;
        SQLiteDatabase db = AppContext.dbHelper.getWritableDatabase();
        try {
            String[] kies= UtilProcess.convertLongToArrayString(listKey);
            cursor= db.rawQuery(SELECT_IN_USER_KEY_QUERY+ SQLHelper.inExpression(kies), kies);
            cursor.moveToFirst();
            ChatUser chatUser;
            for (int i = 0; i < cursor.getCount(); i++) {
                chatUser = new ChatUser();
                chatUser.setUserKey(cursor.getLong(0));
                chatUser.setChatKey(cursor.getLong(1));
                chatUser.setNeedKey(cursor.getLong(2));
                result.add(chatUser);
                cursor.moveToNext();
            }
        }finally {
            if (db!=null){
                db.close();
            }
        }
        return result;
    }
}
