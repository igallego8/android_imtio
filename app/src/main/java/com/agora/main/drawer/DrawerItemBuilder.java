package com.agora.main.drawer;

import com.agora.R;

/**
 * Created by Ivan on 14/09/15.
 */
public class DrawerItemBuilder {

    private static DrawerItemBuilder instance;
    private DrawerItem[] items;

    public void setItems(DrawerItem[] items) {
        this.items = items;
    }

    public DrawerItem[] getItems() {
        return items;
    }

    private DrawerItemBuilder() {
        items = new DrawerItem[10];
        items[0] = (new DrawerItem(R.string.drawer_header_pref, -1,-1,-1,-1));
        items[1] = (new DrawerItem(R.string.drawer_my_account_title,-1, R.drawable.ic_account_circle_white_48dp, R.drawable.ic_settings_white_24dp,-1));
        items[2] = (new DrawerItem(R.string.drawer_notification_title, -1,-1,-1,-1));
        items[3] = (new DrawerItem(R.string.drawer_more_title, -1,-1,-1,-1));
        items[4] = (new DrawerItem(R.string.drawer_country_pref,-1,-1,-1,-1));
        items[5] = (new DrawerItem(R.string.drawer_feedback_pref,-1,-1,-1,-1));
        items[6] = (new DrawerItem(R.string.drawer_help_pref,-1, -1,-1,-1));
        items[7] = (new DrawerItem(R.string.drawer_terms_use_pref,-1,-1,-1,-1));
        items[8] = (new DrawerItem(R.string.drawer_privacity_pref,-1, -1,-1,-1));
        items[9] = (new DrawerItem(R.string.drawer_logout_pref,-1, -1,-1,-1));
    }

    public static DrawerItemBuilder getInstance() {
        if (instance == null) {
            instance = new DrawerItemBuilder();
        }
        return instance;

    }
}
