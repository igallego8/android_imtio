package com.agora.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.agora.db.dao.ChatDAO;
import com.agora.db.dao.ChatUserDAO;
import com.agora.db.dao.ConversationDAO;
import com.agora.db.dao.MessageDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 7/11/15.
 */
public class FactoryDBHelper extends SQLiteOpenHelper {

    public static List<DatabaseTable> tables= new ArrayList<>();

    static {
        tables.add(MessageDAO.getInstance());
        tables.add( ChatDAO.getInstance());
        tables.add( ChatUserDAO.getInstance());
    }

    public FactoryDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (DatabaseTable t : tables) {
            db.execSQL(t.getCreateQuery());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (DatabaseTable t : tables) {
            db.execSQL(t.getDropQuery());
        }
        onCreate(db);
    }

}
