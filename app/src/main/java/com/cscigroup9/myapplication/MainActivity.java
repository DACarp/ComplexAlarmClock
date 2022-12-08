package com.cscigroup9.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {


    public static final String HOUR_SET = "chosenHour";
    public static final String MINUTE_SET = "chosenMinute";
    public static final String ALGEBRA_BOOLEAN = "isAlgebra";
    public static final String TASK_NUM = "numTasks"; //Strings to be used in saved bundles and
                                                //broadcasts


    private TimePicker timePicker; //Clock for the user to set alarm time
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch algebraSwitch; //Toggle for the user to choose whether or not algebra is allowed
    private EditText numTasksText; //Text field for the user to choose how many tasks must be done


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Log.d("EEEEE Main", "Main onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button armButton = findViewById(R.id.armButton); //Button that arms the alarm
        //final View mainContent = findViewById(R.id.mainMenuLinearLayout); //The view

        algebraSwitch = findViewById(R.id.optionsAlgebraSwitch);
        numTasksText = findViewById(R.id.optionsTaskNumEditText);
        timePicker = findViewById(R.id.mainMenuTimePicker);



        if(savedInstanceState != null){ //Used saved bundle, or use defaults.
            timePicker.setCurrentHour(savedInstanceState.getInt(HOUR_SET));
            timePicker.setCurrentMinute(savedInstanceState.getInt(MINUTE_SET));
            algebraSwitch.setChecked(savedInstanceState.getBoolean(ALGEBRA_BOOLEAN));
            numTasksText.setText(savedInstanceState.getString(TASK_NUM));
        }
        else{
            algebraSwitch.setChecked(true);
            numTasksText.setText("5");
        }

        armButton.setOnClickListener(v -> {
            Alarm alarm = new Alarm(timePicker.getCurrentHour(), timePicker.getCurrentMinute(), algebraSwitch.isChecked(), numTasksText.getText().toString());
            alarm.scheduleAlarm(getApplicationContext());
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){ //Saves the options
        super.onSaveInstanceState(outState);
        outState.putInt(HOUR_SET, timePicker.getCurrentHour());
        outState.putInt(MINUTE_SET, timePicker.getCurrentMinute());
        outState.putBoolean(ALGEBRA_BOOLEAN, algebraSwitch.isChecked());
        outState.putString(TASK_NUM, String.valueOf(numTasksText.getText()));
    }


}