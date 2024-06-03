package br.com.diebold.partsrequest.services;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;

import br.com.diebold.partsrequest.R;
import br.com.diebold.partsrequest.data.api.response.UsuarioResponse;
import br.com.diebold.partsrequest.listeners.OnWebSyncListener;
import br.com.diebold.partsrequest.tasks.AtualizarPedidosTask;
import br.com.diebold.partsrequest.tasks.EnviarPedidosTask;
import br.com.diebold.partsrequest.utils.NotificationBase;
import br.com.diebold.partsrequest.utils.NotificationUtil;
import br.com.diebold.partsrequest.utils.PreferencesUserUtil;

public class Relogio extends Service implements OnWebSyncListener<String> {

    private Context contextRelogio;
    private int NOTIF_ID = 101;
    public static ResultReceiver resultReceiver;
    NotificationUtil notificationUtil;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        contextRelogio = this;
        notificationUtil = new NotificationUtil(getApplicationContext());
    }


    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        Bundle bundle = new Bundle();
        bundle.putString("end", "Timer Relogio Stopped....");

        if (resultReceiver != null)
            resultReceiver.send(200, bundle);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        super.onStartCommand(intent, flags, startId);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            startForeground(NOTIF_ID, new NotificationBase(this).defaultNotification());
        }

        iniciarSincronizacao();
        return START_STICKY;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onFinished(String result) {

        if (result.equals("sucesso")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("s");
            Bundle bundle = new Bundle();
            bundle.putString("onFinished", result);
            if (resultReceiver != null)
                resultReceiver.send(Integer.parseInt(dateFormat.format(System.currentTimeMillis())), bundle);
        }
    }

    @Override
    public Activity getContext() {
        return null;
    }


    private void iniciarSincronizacao() {

        if(contextRelogio == null)
            contextRelogio = this;

//        UsuarioResponse dadosUsuarioLogado = PreferencesUserUtil.getObjectFromPref(this.contextRelogio, UsuarioResponse.class, PreferencesUserUtil.PREFS_USER_DATA_LOGGED);
//        dadosUsuarioLogado != null &&
        if(PreferencesUserUtil.isLogged(this.contextRelogio)) {

            Bundle bundle = new Bundle();
            bundle.putString("start", "Timer Relogio Started....");

            if (resultReceiver != null)
                resultReceiver.send(100, bundle);


            //notificationUtil.sendNotification("Atualizacao", "Pedidos", "Baixando Pedidos do Servidor...", NOTIF_ID);

            EnviarPedidosTask enviarPedidosTask = new EnviarPedidosTask(this.contextRelogio);
            enviarPedidosTask.execute();

//            try {
//                Thread.sleep(60000); //1 minuto
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            AtualizarPedidosTask atualizarPedidosTask = new AtualizarPedidosTask(this.contextRelogio);
            atualizarPedidosTask.execute();

        }

    }



}
