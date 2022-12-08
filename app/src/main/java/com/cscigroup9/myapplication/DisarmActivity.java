package com.cscigroup9.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentResultListener;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class DisarmActivity extends AppCompatActivity {
    //Activity that is created when the alarm triggers and the user opens the app. This class
    //handles the logic for the disarm tasks.

    private ArrayList<Integer> taskListIds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Log.d("EEEE DisarmActivity", "onCreate start");

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        setContentView(R.layout.activity_disarm);

        boolean isAlgebra = intent.getBooleanExtra("isAlgebra", true);
        int numTasks = intent.getIntExtra("numTasks", 5);

        //Log.d("EEEE DisarmActivity", "got intent");

        Random rand = new Random();

        //Each kind of task has an id. We add the id to the taskList in order to allow it for this
        //alarm. 1 = arithmetic, 2 = algebra, 3 = memory

         //Add ids of allowed tasks to the arraylist.
        taskListIds = new ArrayList<>();
        taskListIds.add(1); //Arithmetic always allowed
        if(isAlgebra)
            taskListIds.add(2); //Add algebra if allowed
        //could do with a multichoice dropdown for these
        taskListIds.add(3);

        //Log.d("EEEE DisarmActivity", "taskListIds created successfully");

        int chosen = rand.nextInt(taskListIds.size()); //Returns a random number between 0
        Class puzzle = ArithmeticGame.class;

        //Log.d("EEEE DisarmActivity", "chosenIndex = " + chosen);

        switch(taskListIds.get(chosen)) {
            case 1: //Id 1 = arithmetic
                break;
            //case 2: //Id 2 = algebra
            //    Log.d("EEEE DisarmActivity", "for loop, algebra");
            //    puzzle = AlgebraGame.Class;
            //    break;
            case 3: //Id 3 = Memory
                puzzle = MemoryGame.class;
                break;
        }

        //Initialize the puzzle in our activity's container
        Bundle args = new Bundle();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.disarm_fragment_container, puzzle, args)
                .commit();

        //Setup the listener for when the puzzle is finished
        getSupportFragmentManager().setFragmentResultListener("disarmed", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Boolean ret = result.getBoolean("disarmed");
                if(ret) {
                    disarmAlarm();
                }
            }
        });
    }

    public void disarmAlarm(){
        Intent intentService = new Intent(getApplicationContext(), AlarmNotifier.class);
        getApplicationContext().stopService(intentService);
        finish();
    }

}