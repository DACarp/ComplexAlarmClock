package com.cscigroup9.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

public class Alarm {

    private int hourSet;
    private int minuteSet;
    private boolean setAlgebra;
    private int numTasks;
    static int id = 989;




    public Alarm(int hour, int minute, boolean isAlgebra, String taskNum){
        hourSet = hour;
        minuteSet = minute;
        setAlgebra = isAlgebra;
        numTasks = Integer.parseInt(taskNum);

    }

    public void scheduleAlarm(Context context){

        //Log.d("EEEE Alarm", "scheduleAlarm start");

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("isAlgebra", setAlgebra);
        intent.putExtra("numTasks", numTasks);

        PendingIntent alarmPending = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_IMMUTABLE);



        String toast = null; //Show a confirmation toast.

        try {
            toast = String.format("Alarm Set!");

        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(context, toast, Toast.LENGTH_LONG).show();

        Calendar calendarObj = Calendar.getInstance();
        calendarObj.setTimeInMillis(System.currentTimeMillis());

        //Log.d("EEEE Alarm", "CurrentTime= " + calendarObj.getTimeInMillis());

        calendarObj.set(Calendar.HOUR_OF_DAY, hourSet);
        calendarObj.set(Calendar.MINUTE, minuteSet);
        calendarObj.set(Calendar.SECOND, 0);
        calendarObj.set(Calendar.MILLISECOND, 0); //Set alarm time

        //Log.d("EEEE Alarm", "alarmSetTime= " + calendarObj.getTimeInMillis());

        //If the time is in the past for today, the alarm is being set for tomorrow.
        if (calendarObj.getTimeInMillis() <= System.currentTimeMillis()) {
            calendarObj.set(Calendar.DAY_OF_MONTH, calendarObj.get(Calendar.DAY_OF_MONTH) + 1);
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendarObj.getTimeInMillis(), alarmPending);

    }
}
