package br.com.diebold.partsrequest.listeners;

import android.app.Activity;

public interface OnWebSyncListener<T> {

    public void onFinished(T args);

    public Activity getContext();

}
