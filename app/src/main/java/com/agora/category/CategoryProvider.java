package com.agora.category;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import com.agora.app.AppConfig;
import com.agora.data.DataProviderFactory;
import com.agora.data.IDataProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Ivan on 5/10/15.
 */
public class CategoryProvider extends ContentProvider {

    static final String AUTHORITY = "com.agora.category.CategoryProvider";

    static final String URL = "content://" + AUTHORITY + "/cte";
    static final Uri CONTENT_URI = Uri.parse(URL);
    public static final Uri SEARCH_URI = Uri.parse("content://"+AUTHORITY+"/search");

    public static final Uri DETAILS_URI = Uri.parse("content://"+AUTHORITY+"/details");

    private static final int SEARCH = 1;
    private static final int SUGGESTIONS = 2;
    private static final int DETAILS = 3;

    // Defines a set of uris allowed with this content provider
    private static final UriMatcher mUriMatcher = buildUriMatcher();

    private static UriMatcher buildUriMatcher() {

        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        // URI for "Go" button
        uriMatcher.addURI(AUTHORITY, "search", SEARCH );

        // URI for suggestions in Search Dialog
        uriMatcher.addURI(AUTHORITY, SearchManager.SUGGEST_URI_PATH_QUERY,SUGGESTIONS);

        // URI for Details
        uriMatcher.addURI(AUTHORITY, "details",DETAILS);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        Cursor c = null;

        CategoryJSONParser categoryParser = new CategoryJSONParser();

        String jsonString = "";

        List<HashMap<String, String>> list = null;
        List<HashMap<String, String>> detailsList = null;

        MatrixCursor mCursor = null;

        switch(mUriMatcher.match(uri)) {
            case SEARCH:
                // Defining a cursor object with columns description, lat and lng
                mCursor = new MatrixCursor(new String[] { "description" });
                break;
            case SUGGESTIONS:
                mCursor = new MatrixCursor(new String[] { "_id", SearchManager.SUGGEST_COLUMN_TEXT_1} );
                categoryParser = new CategoryJSONParser();
                list = categoryParser.parse(getCategories(selectionArgs));
                for(int i=0;i<list.size();i++){
                    HashMap<String, String> hMap = (HashMap<String, String>) list.get(i);
                    mCursor.addRow(new String[] { Integer.toString(i), hMap.get("description") });
                }
                c = mCursor;
                break;
            case DETAILS:
                // Defining a cursor object with columns description, lat and lng
                mCursor = new MatrixCursor(new String[] { "description" });
                break;
        }
        return mCursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    private JSONObject getCategories(String[] params){
            IDataProvider provider= AppConfig.dataProvider;
            return provider.getCategoryPrediction(params);

    }
}
