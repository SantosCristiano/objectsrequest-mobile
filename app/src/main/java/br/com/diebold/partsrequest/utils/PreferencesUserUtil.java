package br.com.diebold.partsrequest.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


import br.com.diebold.partsrequest.modelView.LoginView;

public class PreferencesUserUtil {

    public static String PREFS_USER_LOGGED = "UserLogged";
    public static String PREFS_USER_DATA_LOGGED = "UserDataLogged";
    public static String PREFS_LOGIN_DATA = "LoginData";
    public static String PREFS_USER_TOKEN_LOGGED = "LoginToken";

    public static boolean isLogged(Context context) {
        return hasLoggedIn(context, PREFS_USER_LOGGED, false);
    }

    public static String getToken(Context context) {
        return PreferencesUserUtil.getFromPrefs(context, PREFS_USER_TOKEN_LOGGED).replace("\"", "");
    }


    public static void logout(Context context) {
        saveToPrefs(context, PREFS_USER_LOGGED, false);
        //saveObjectToPref(context, null, PREFS_USER_DATA_LOGGED);
    }


    private static boolean hasLoggedIn(Context context, String key, boolean defaultValue) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            Boolean  tk = sharedPrefs.getBoolean(key, false);
            return tk;
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    public static LoginView getUserSaved(Context context){
        try{
            return PreferencesUserUtil.getObjectFromPref(context, LoginView.class, PreferencesUserUtil.PREFS_LOGIN_DATA);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public static void saveToPrefs(Context context, String key, String value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void saveToPrefs(Context context, String key, boolean value) {
        if(context != null){
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            final SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(key, value);
            editor.apply();
        }
    }

    public static void saveToPrefs(Context context, String key, long value) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static String getFromPrefs(Context context, String key) {
        String defaultValue = "";
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            return sharedPrefs.getString(key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    public static <T> void saveToPrefs (Context context, String key, List<T> list){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
    }

    public static <T> List<T> getToPrefs(Context context, String key, Class<T[]> clazz){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String json = prefs.getString(key, null);
        T[] arr = new Gson().fromJson(json, clazz);
        return arr != null ? Arrays.asList(arr) : new ArrayList<T>();
    }

    public static long getFromLongPrefs(Context context, String key) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            return sharedPrefs.getLong(key, 0);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean getFromBooleanPrefs(Context context, String key) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        try {
            return sharedPrefs.getBoolean(key, false);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void saveObjectToPref(Context context, Object object, String key){
        String objectTojson = new GsonBuilder().create().toJson(object);
        PreferencesUserUtil.saveToPrefs(context, key, objectTojson);
    }

    public static <T> T getObjectFromPref(Context context, Class<T> instance, String key){
        String json = getFromPrefs(context, key);
        return new GsonBuilder().create().fromJson(json, instance);
    }


}
