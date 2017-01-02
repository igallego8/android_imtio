package com.agora.preference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agora.R;
import com.agora.app.AppConfig;


public class NotificationPrefFragment extends PreferenceFragment {

    SharedPreferences mPrefs;


    public NotificationPrefFragment() {

    }
    @Override
    public void onStart(){
        super.onStart();
        mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.notification_preference);

        SwitchPreference vibNewBidPref = (SwitchPreference) findPreference(AppConfig.PREF_KEY_NOTIF_VIB_NEW_BID);
        if (vibNewBidPref != null) {
            vibNewBidPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference arg0, Object object) {
                    boolean isOn = ((Boolean) object).booleanValue();
                    SharedPreferences.Editor e = mPrefs.edit();
                    e.putBoolean(AppConfig.PREF_KEY_NOTIF_VIB_NEW_BID, isOn);
                    e.commit();

                    return true;
                }
            });
        }

        SwitchPreference soundNewBidPref = (SwitchPreference) findPreference(AppConfig.PREF_KEY_NOTIF_SOUND_NEW_BID);
        if (soundNewBidPref != null) {
            soundNewBidPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference arg0, Object object) {
                    boolean isOn = ((Boolean) object).booleanValue();
                    SharedPreferences.Editor e = mPrefs.edit();
                    e.putBoolean(AppConfig.PREF_KEY_NOTIF_SOUND_NEW_BID, isOn);
                    e.commit();

                    return true;
                }
            });
        }
    }


}
