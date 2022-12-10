package com.cscigroup9.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class DisarmActivity extends AppCompatActivity {
    //Activity that is created when the alarm triggers and the user opens the app. This class
    //handles the logic for the disarm tasks.

    private ArrayList<Integer> taskListIds;
    private int tasksLeft = 1;
    private final String TASK_FRAGMENT = "taskFrag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Log.d("EEEE DisarmActivity", "onCreate start");

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        setContentView(R.layout.activity_disarm);

        boolean isAlgebra = intent.getBooleanExtra("isAlgebra", true);
        int numTasks = intent.getIntExtra("numTasks", 3);

        Log.d("EEEE DisarmActivity", "numTasks = " + numTasks);



        //Each kind of task has an id. We add the id to the taskList in order to allow it for this
        //alarm. 1 = arithmetic, 2 = algebra, 3 = memory

         //Add ids of allowed tasks to the arraylist.
        taskListIds = new ArrayList<>();
        taskListIds.add(1); //Arithmetic always allowed
        if(isAlgebra)
            taskListIds.add(2); //Add algebra if allowed
        //could do with a multichoice dropdown for these
        taskListIds.add(3);
        taskListIds.add(4);



        Log.d("EEEE DisarmActivity", "taskListIds size = " + taskListIds.size());


        Class puzzle = ArithmeticGame.class; //Default instantiate
        puzzle = getRandomTask(taskListIds); //Get random puzzle class from the allowed lists






        //Initialize the puzzle in our activity's container
        instantiateTask(puzzle);

        tasksLeft = numTasks;

        //Setup the listener for when the puzzle is finished
        getSupportFragmentManager().setFragmentResultListener("disarmed", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Boolean ret = result.getBoolean("disarmed");
                if(ret) {//If a task is disarmed
                    Log.d("EEEE DisarmActivity", "Disarmed! tasksLeft = " + tasksLeft);

                    if(tasksLeft < 1) //If there are no more tasks, disarm.
                        disarmAlarm();
                    else {
                        tasksLeft--; //Else, decrement tasksLeft and get new task.
                        replaceTask(getRandomTask(taskListIds));
                    }
                }
            }
        });
    }

    public Class getRandomTask(ArrayList<Integer> list){
        Class puzzle = ArithmeticGame.class;

        Random rand = new Random();

        int chosen = rand.nextInt(list.size()); //Returns a random number between 0 and size-1

        Log.d("EEEE DisarmActivity", "chosenIndex = " + list.get(chosen));

        switch(list.get(chosen)) {

            case 1: //Id 1 = arithmetic. Default.
                break;
            case 2: //Id 2 = algebra. Not implemented, stick with default.
                break;
            case 3: //Id 3 = Memory
                puzzle = MemoryGame.class;
                break;
            case 4: //id 4 = guessIt
                puzzle = guessIt.class;
                break;
        }

        Log.d("EEEE DisarmActivity", "puzzle class = " + puzzle.getSimpleName());

        return puzzle;
    }

    public void instantiateTask(Class puzzle){
        Bundle args = new Bundle();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.disarm_fragment_container, puzzle, args)
                .commit();
    }

    public void replaceTask(Class puzzle){
        Bundle args = new Bundle();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.disarm_fragment_container, puzzle, args)
                .commit();
    }

    public void disarmAlarm(){
        Intent intentService = new Intent(getApplicationContext(), AlarmNotifier.class);
        getApplicationContext().stopService(intentService);
        finish();
    }

}