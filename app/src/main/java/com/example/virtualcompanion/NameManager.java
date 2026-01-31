package com.example.virtualcompanion;

import android.content.Context;
import android.content.SharedPreferences;

public class NameManager {

    private static final String PREF_NAME = "pet_data";
    private static final String KEY_NAME = "pet_name";


    private static SharedPreferences getPrefs(Context c) {
        return c.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }


    // Save name
    public static void setName(Context c, String name) {
        getPrefs(c).edit().putString(KEY_NAME, name).apply();
    }


    // Get name (default = ECHO)
    public static String getName(Context c) {
        return getPrefs(c).getString(KEY_NAME, "ECHO");
    }
}
