package com.cscigroup9.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {


    private final String BUNDLE_HOUR_SET = "chosenHour";
    private final String BUNDLE_MINUTE_SET = "chosenMinute";
    private final String BUNDLE_ALGEBRA_BOOLEAN = "isAlgebra";
    private final String BUNDLE_TASK_NUM = "numTasks"; //Strings to be used in saved bundles

    private TimePicker timePicker; //Clock for the user to set alarm time
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch algebraSwitch; //Toggle for the user to choose whether or not algebra is allowed
    private EditText numTasksText; //Text field for the user to choose how many tasks must be done


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button armButton = findViewById(R.id.armButton); //Button that arms the alarm
        final View mainContent = findViewById(R.id.mainMenuLinearLayout); //The view

        algebraSwitch = findViewById(R.id.optionsAlgebraSwitch);
        numTasksText = findViewById(R.id.optionsTaskNumEditText);
        timePicker = findViewById(R.id.mainMenuTimePicker);



        if(savedInstanceState != null){ //Used saved bundle, or use defaults.
            timePicker.setCurrentHour(savedInstanceState.getInt(BUNDLE_HOUR_SET));
            timePicker.setCurrentMinute(savedInstanceState.getInt(BUNDLE_MINUTE_SET));
            algebraSwitch.setChecked(savedInstanceState.getBoolean(BUNDLE_ALGEBRA_BOOLEAN));
            numTasksText.setText(savedInstanceState.getString(BUNDLE_TASK_NUM));
        }
        else{
            algebraSwitch.setChecked(true);
            numTasksText.setText("5");
        }


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){ //Saves the options
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_HOUR_SET, timePicker.getCurrentHour());
        outState.putInt(BUNDLE_MINUTE_SET, timePicker.getCurrentMinute());
        outState.putBoolean(BUNDLE_ALGEBRA_BOOLEAN, algebraSwitch.isChecked());
        outState.putString(BUNDLE_TASK_NUM, String.valueOf(numTasksText.getText()));
    }






}