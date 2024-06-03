package br.com.diebold.partsrequest.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import br.com.diebold.partsrequest.R;
import androidx.core.app.NotificationCompat;

public class NotificationUtil {

    private NotificationManager mNotificationManager;
    private Context context;

    public NotificationUtil(Context context){
        this.context = context;
        mNotificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

    }

    public void sendNotification(String categoria, String tipo, String msg, Integer notify_id) {
        try {

            String channelId = "";
            NotificationCompat.Builder mBuilder = null;

//            Intent notificationIntent = new Intent(context, FromNotification.class);
//            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
//                    notificationIntent, 0);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                channelId = new NotificationBase(context).createNotificationChannelAlert();//categoria | id.toString(),
                mBuilder = new NotificationCompat.Builder(context, channelId);
            } else {
                mBuilder = new NotificationCompat.Builder(context);
            }

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                channelId = new NotificationBase(service).createNotificationChannelAlert();//categoria | id.toString(),
//                mBuilder = new NotificationCompat.Builder(service, channelId);
//            } else {
//                mBuilder = new NotificationCompat.Builder(service);
//            }

            mBuilder.setSmallIcon(R.mipmap.ic_launcher);
            mBuilder.setContentTitle(tipo);
            mBuilder.setStyle(new NotificationCompat.BigTextStyle()
                    .bigText(msg));
            mBuilder.setContentText(msg);
//            mBuilder.setContentIntent(pendingIntent);
            mBuilder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
            mBuilder.setLights(Color.RED, 3000, 3000);
            mBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
            mBuilder.setAutoCancel(true);
            mBuilder.setPriority(5);
            mNotificationManager.notify(notify_id, mBuilder.build());

        } catch (Exception ex) {
            String erro = ex.getMessage();
        }
    }

}
