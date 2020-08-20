package com.iparksimple.app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

public class PreferenceUtil {
    public static Context mcontext;
    static public void setPreferenceObject(Context c, Object modal,String key) {
        /**** storing object in preferences  ****/
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(c.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String jsonObject = gson.toJson(modal);
        prefsEditor.putString(key, jsonObject);
        prefsEditor.commit();

    }
    static public void clearPreferenceObject(Context c) {
        getSharedPreferences(c).edit().clear().apply();
    }
    public static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static String getAccessTokenFromLogin(Context context) {
        return getSharedPreferences(context).getString(Constants.PreferenceConstants.DEVICEID, "");
    }

    public static void setAccessTokenFromLogin(Context context, String DeviceId) {
        getSharedPreferences(context).edit().putString(Constants.PreferenceConstants.DEVICEID, DeviceId).apply();
    }

    public static String getUserId(Context context) {
        return getSharedPreferences(context).getString(Constants.PreferenceConstants.USERID, "");
    }

    public static void setUserId(Context context, String UserId) {
        getSharedPreferences(context).edit().putString(Constants.PreferenceConstants.USERID, UserId).apply();
    }

    public static String getUserEmail(Context context) {
        return getSharedPreferences(context).getString(Constants.PreferenceConstants.USEREMAIL, "");
    }

    public static void setUserEmail(Context context, String UserEmail) {
        getSharedPreferences(context).edit().putString(Constants.PreferenceConstants.USEREMAIL, UserEmail).apply();
    }

    public static String getUserMobile(Context context) {
        return getSharedPreferences(context).getString(Constants.PreferenceConstants.USERPHONE, "");
    }

    public static void setUserMobile(Context context, String UserMobile) {
        getSharedPreferences(context).edit().putString(Constants.PreferenceConstants.USERPHONE, UserMobile).apply();
    }

    public static String getUserName(Context context) {
        return getSharedPreferences(context).getString(Constants.PreferenceConstants.USERNAME, "");
    }

    public static void setUserName(Context context, String UserName) {
        getSharedPreferences(context).edit().putString(Constants.PreferenceConstants.USERNAME, UserName).apply();
    }

    public static String getUserLongitude(Context context) {
        return getSharedPreferences(context).getString(Constants.PreferenceConstants.LONGITUDE, "");
    }

    public static void setUserLongitude(Context context, String Longitude) {
        getSharedPreferences(context).edit().putString(Constants.PreferenceConstants.LONGITUDE, Longitude).apply();
    }
    public static String getUserLatitude(Context context) {
        return getSharedPreferences(context).getString(Constants.PreferenceConstants.LATITUDE, "");
    }

    public static void setUserLatitude(Context context, String Latitude) {
        getSharedPreferences(context).edit().putString(Constants.PreferenceConstants.LATITUDE, Latitude).apply();
    }

    public static String getSelectedView(Context context) {
        return getSharedPreferences(context).getString(Constants.PreferenceConstants.SELECTED_VIEW, "");
    }

    public static void setSelectedView(Context context, String view) {
        getSharedPreferences(context).edit().putString(Constants.PreferenceConstants.SELECTED_VIEW, view).apply();
    }
}
