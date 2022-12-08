package com.cscigroup9.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){

        //startAlarm(context, intent);

        Intent intentServ = new Intent(context, AlarmNotifier.class);
        context.startService(intentServ);

    }




}
