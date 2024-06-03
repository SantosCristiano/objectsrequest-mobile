package br.com.diebold.partsrequest.application;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import br.com.diebold.partsrequest.services.Relogio;

public class PartsRequestApplication  extends Application {

    private long SleepSyncData = 3000;
    private List<AsyncTask<?, ?, ?>> tasks = new ArrayList<AsyncTask<?, ?, ?>>();
    private static ScheduledExecutorService relogioScheduler;


    @Override
    public void onCreate() {
        super.onCreate();

        iniciarRelogioSchedule();
    }



    @Override
    public void onTerminate() {

        super.onTerminate();

        for (AsyncTask<?, ?, ?> task : tasks) {
            task.cancel(true);
        }

        pararRelogioSchedule();


    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }


    public void registrar(AsyncTask<?, ?, ?> task) {
        tasks.add(task);
    }

    public void desregistrar(AsyncTask<?, ?, ?> task) {
        tasks.remove(task);
    }

    public int getTasksSize() {
        return tasks.size();
    }




    public void iniciarRelogioSchedule(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            if(relogioScheduler == null | (relogioScheduler != null && (relogioScheduler.isShutdown() | relogioScheduler.isTerminated()))) {
                relogioScheduler =
                        Executors.newSingleThreadScheduledExecutor();

                relogioScheduler.scheduleAtFixedRate
                        (new Runnable() {
                            public void run() {

                                Intent intent = new Intent(getApplicationContext(), Relogio.class);
                                getApplicationContext().startService(intent);

                            }
                        }, 0, 5 * 60000, TimeUnit.MILLISECONDS);
            }

        } else {

            PendingIntent pintent = null;
            Intent intent = new Intent(getApplicationContext(), Relogio.class);

            AlarmManager alarm = (AlarmManager) this.getSystemService(getApplicationContext().ALARM_SERVICE);


            pintent = PendingIntent.getService(
                    this, 52247, intent, PendingIntent.FLAG_UPDATE_CURRENT);//FLAG_CANCEL_CURRENT .. FLAG_UPDATE_CURRENT

            alarm.setRepeating(AlarmManager.RTC_WAKEUP, 0, 5 * 60000, pintent);

        }


    }
    public void pararRelogioSchedule(){
//        LogActivity.appendLog(LogActivity.LogFrom.,"Parar Relogio Schedule.");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            if(relogioScheduler != null && !(relogioScheduler.isShutdown() | relogioScheduler.isTerminated())) {
                relogioScheduler.shutdown();
//                try {
                    Intent intent = new Intent(getApplicationContext(), Relogio.class);
                    getApplicationContext().stopService(intent);
//                }catch (Exception ex){
//                    LogActivity.appendLog(LogActivity.LogFrom.,"Parar Relogio Schedule. ERRO: 1 - " + ex.getMessage());
//                }
            }

        } else {
//            try {
                Intent intent = new Intent(getApplicationContext(), Relogio.class);
                PendingIntent pintent = PendingIntent.getService(getApplicationContext(), 52247, intent, 0);
                AlarmManager alarm = (AlarmManager) getApplicationContext().getSystemService(getApplicationContext().ALARM_SERVICE);
                alarm.cancel(pintent);
                stopService(intent);
//            }catch (Exception ex){
//                LogActivity.appendLog(LogActivity.LogFrom.,"Parar Relogio Schedule. ERRO: 2 - " + ex.getMessage());
//            }
        }


//        LogActivity.appendLog(LogActivity.LogFrom.,"Parou Relogio.");

    }

}
