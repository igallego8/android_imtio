package com.agora.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.agora.app.AppConfig;
import com.agora.entity.User;

/**
 * Created by Ivan on 22/09/15.
 */
public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setUserInfo(User user) {
        prefs.edit().putString(AppConfig.PREF_KEY_USER_NAME, user.getName());
        prefs.edit().putString(AppConfig.PREF_KEY_USER_LAST_NAME, user.getLastName());
        prefs.edit().putString(AppConfig.PREF_KEY_USER_KEY, user.getUserKey().toString());
        prefs.edit().commit();
    }

    public Long getUserKey() {
        String userKey = prefs.getString(AppConfig.PREF_KEY_USER_KEY,"0");
        return Long.getLong(userKey);
    }

    public String getUserName() {
       return prefs.getString(AppConfig.PREF_KEY_USER_NAME, "");

    }

    public String getUserLastName() {
        return prefs.getString(AppConfig.PREF_KEY_USER_LAST_NAME, "");

    }

}
