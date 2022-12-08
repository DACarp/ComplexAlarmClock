package com.cscigroup9.myapplication;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class AlarmNotifier extends Service {
    private static final String ALARM_CHANNEL = "alarmChannel";
    private MediaPlayer alarmSound;
    private Vibrator vibrator;

    @Override
    public void onCreate(){
        super.onCreate();

        alarmSound = MediaPlayer.create(this, R.raw.alarmsound);
        alarmSound.setLooping(true);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int id){
        Intent notificationIntent = new Intent(this, DisarmActivity.class);
        PendingIntent pending = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Notification notif = new NotificationCompat.Builder(this, ALARM_CHANNEL)
                .setContentTitle("ALARM OF PAIN")
                .setContentText("TIME TO WAKE UP")
                .setContentIntent(pending)
                .setSmallIcon(R.drawable.ic_baseline_access_alarms_24)
                .build();

        //Log.d("EEEE AlarmNotifier:", "Notification settings set");

        alarmSound.start();

        long[] pattern = { 0, 100, 500 };
        vibrator.vibrate(pattern, 0);

        startForeground(1, notif);

        return START_STICKY;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        alarmSound.stop();
        vibrator.cancel();
    }


    @Override public IBinder onBind(Intent intent){
        return null;
    }

}
