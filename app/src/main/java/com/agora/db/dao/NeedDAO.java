package com.agora.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.agora.app.AppContext;
import com.agora.db.DatabaseTable;
import com.agora.db.FactoryDBHelper;
import com.agora.entity.Bid;
import com.agora.entity.Category;
import com.agora.entity.Chat;
import com.agora.entity.Need;
import com.agora.util.SQLHelper;
import com.agora.util.UtilProcess;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ivan on 7/11/15.
 */
public class NeedDAO implements DatabaseTable {

    private FactoryDBHelper helper=AppContext.dbHelper;

    public static final String TABLE_NAME = "need";
    public static final String COLUMN_NAME_ENTRY_ID = "needKey";
    public static final String COLUMN_NAME_LAST_DESCRIPTION = "description";
    public static final String COLUMN_NAME_SETTLEMENT_DATE = "settlementDate";
    public static final String COLUMN_NAME_EXPIRATION_DATE = "expirationDate";
    public static final String COLUMN_NAME_QTY_NEW_MESSAGES = "messagesNumber";
    public static final String COLUMN_NAME_QTY_NEW_BIDS = "bidsNumber";
    public static final String COLUMN_NAME_TYPE = "type";
    public static final String COLUMN_NAME_IMAGE1= "image1";
    public static final String COLUMN_NAME_IMAGE2 = "image2";
    public static final String COLUMN_NAME_IMAGE3 = "image3";
    public static final String COLUMN_NAME_CATEGORY_KEY = "categoryKey";
    public static final String COLUMN_NAME_CATEGORY_DESCRIPTION = "categoryDescription";
    public static final String COLUMN_NAME_CATEGORY_IMAGE = "categoryImage";


    public static final String SELECT_NEED_KEY_QUERY = "SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_NAME_ENTRY_ID+" = ?";
    public static final String SELECT_NEED_ALL_QUERY = "SELECT * FROM "+TABLE_NAME;

    public static NeedDAO instance;

    private NeedDAO() {

    }

    public static NeedDAO getInstance(){
        if (instance==null){
            instance= new NeedDAO();
        }
        return instance;
    }

    @Override
    public String getCreateQuery() {
        return "CREATE TABLE "+TABLE_NAME
                +"("+COLUMN_NAME_ENTRY_ID+" LONG PRIMARY KEY, "
                + COLUMN_NAME_LAST_DESCRIPTION + " TEXT, "
                +COLUMN_NAME_SETTLEMENT_DATE+" DATETIME DEFAULT CURRENT_TIMESTAMP, "
                +COLUMN_NAME_EXPIRATION_DATE+" DATETIME DEFAULT CURRENT_TIMESTAMP, "
                + COLUMN_NAME_QTY_NEW_MESSAGES + " INTEGER, "
                + COLUMN_NAME_QTY_NEW_BIDS + " INTEGER, "
                + COLUMN_NAME_TYPE + " TEXT, "
                + COLUMN_NAME_IMAGE1 + " TEXT, "
                + COLUMN_NAME_IMAGE2 + " TEXT, "
                + COLUMN_NAME_IMAGE3 + " TEXT, "
                +COLUMN_NAME_CATEGORY_KEY+" LONG, "
                + COLUMN_NAME_CATEGORY_DESCRIPTION + " TEXT, "
                + COLUMN_NAME_CATEGORY_IMAGE + " TEXT) ";

    }

    @Override
    public String getDropQuery() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public boolean update(Need need){
        SQLiteDatabase db = AppContext.dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_QTY_NEW_MESSAGES, need.getMessagesNumber());
        contentValues.put(COLUMN_NAME_QTY_NEW_BIDS,need.getBidsNumber());
        db.update(TABLE_NAME, contentValues, COLUMN_NAME_ENTRY_ID+" = ?", new String[]{need.getNeedKey().toString()});
        return true;
    }

    public boolean insert(Need need) {

        Need n= fetchNeedByNeedKey(need.getNeedKey());
        if (n==null) {
            SQLiteDatabase db = AppContext.dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_NAME_ENTRY_ID, need.getNeedKey());
            contentValues.put(COLUMN_NAME_LAST_DESCRIPTION, need.getDescription());
            contentValues.put(COLUMN_NAME_SETTLEMENT_DATE, UtilProcess.getDateTime(need.getSettlementDate()));
            contentValues.put(COLUMN_NAME_EXPIRATION_DATE, UtilProcess.getDateTime(need.getExpirationDate()));
            contentValues.put(COLUMN_NAME_QTY_NEW_MESSAGES, need.getMessagesNumber());
            contentValues.put(COLUMN_NAME_QTY_NEW_BIDS, need.getBidsNumber());
            contentValues.put(COLUMN_NAME_TYPE, need.getType());
            contentValues.put(COLUMN_NAME_IMAGE1, need.getImage1());
            contentValues.put(COLUMN_NAME_IMAGE2, need.getImage2());
            contentValues.put(COLUMN_NAME_IMAGE3, need.getImage3());
            contentValues.put(COLUMN_NAME_CATEGORY_KEY, need.getCategory().getCategoryKey());
            contentValues.put(COLUMN_NAME_CATEGORY_DESCRIPTION, need.getCategory().getDescription());
            contentValues.put(COLUMN_NAME_CATEGORY_IMAGE, need.getCategory().getImageName());
            db.insert(TABLE_NAME, null, contentValues);
            return true;
        }
        return false;


    }

    public synchronized List<Need> fetchAll(){
        List<Need> result = new ArrayList<>();
        Cursor cursor;
        SQLiteDatabase db = AppContext.dbHelper.getWritableDatabase();
        cursor= db.rawQuery(SELECT_NEED_ALL_QUERY, null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            result.add(populate(cursor));
            cursor.moveToNext();
        }
        return result;
    }

    public synchronized Need fetchNeedByNeedKey(Long needKey){
        Cursor cursor;
        SQLiteDatabase db = AppContext.dbHelper.getWritableDatabase();
        cursor= db.rawQuery(SELECT_NEED_KEY_QUERY, new String[]{needKey.toString()});
        if (cursor.getCount()>0) {
            cursor.moveToFirst();
            return populate(cursor);
        }
        return null;

    }

    protected Need populate (Cursor cursor){
        Need need = new Need();
        need.setNeedKey(cursor.getLong(0));
        need.setDescription(cursor.getString(1));
        need.setSettlementDate(UtilProcess.getDateTime(cursor.getString(2)));
        need.setExpirationDate(UtilProcess.getDateTime(cursor.getString(3)));
        need.setMessagesNumber(cursor.getInt(4));
        need.setBidsNumber(cursor.getInt(5));
        need.setType(cursor.getString(6));
        need.setImage1(cursor.getString(7));
        need.setImage2(cursor.getString(8));
        need.setImage3(cursor.getString(9));
        Long categoryKey= cursor.getLong(10);
        String categoryDescription=cursor.getString(11);
        String categoryImage=cursor.getString(12);
        Category category= new Category(categoryKey, categoryDescription,categoryImage);
        need.setCategory(category);
        return need;
    }

}
