package com.cscigroup9.myapplication;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class AppChannel extends Application {
    public static final String ALARM_CHANNEL = "alarmChannel";

    @Override
    public void onCreate() {
        super.onCreate();

        createChannel();
    }

    private void createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel alarmChannel = new NotificationChannel(
                    ALARM_CHANNEL, "Alarm Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(alarmChannel);
        }


    }

}
