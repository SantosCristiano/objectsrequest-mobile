package br.com.diebold.partsrequest.utils;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class NotificationBase {

    Context context;
    public NotificationBase (Context context){
        this.context = context;
    }

    public Notification defaultNotification() {

        String channelId ="relogio_service";
        String channelName = "Parts Request";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel chan = new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_NONE);//IMPORTANCE_LOW // IMPORTANCE_NONE
            chan.setLightColor(Color.BLUE);
            chan.setLockscreenVisibility(Notification.VISIBILITY_SECRET);//VISIBILITY_PRIVATE
            NotificationManager service = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE) ;
            service.createNotificationChannel(chan);
        }

        // If earlier version channel ID is not used
        // https://developer.android.com/reference/android/support/v4/app/NotificationCompat.Builder.html#NotificationCompat.Builder(android.content.Context)

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, channelId);
        Notification notification = notificationBuilder.setOngoing(true)
                //.setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationCompat.PRIORITY_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
        notification.defaults = 0;
        return notification;
    }

    @RequiresApi(Build.VERSION_CODES.O)
    public String createNotificationChannelAlert(){
        String channelId ="Parts Request Alerta";
        String channelName = "Parts Request Alerta";

        NotificationChannel chan = new NotificationChannel(channelName,
                channelName, NotificationManager.IMPORTANCE_HIGH);//IMPORTANCE_NONE
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);//VISIBILITY_PRIVATE
        NotificationManager service = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE) ;
        service.createNotificationChannel(chan);

        return  channelName;
    }


}
