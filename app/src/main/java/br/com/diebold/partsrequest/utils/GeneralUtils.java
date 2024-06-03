package br.com.diebold.partsrequest.utils;

import android.content.Context;

public class GeneralUtils {

    public static String getStringResourceByName(Context context, String nameOfString) {
        String packageName = context.getPackageName();
        int resId = context.getResources().getIdentifier(nameOfString, "string", packageName);
        return context.getString(resId);
    }


}
