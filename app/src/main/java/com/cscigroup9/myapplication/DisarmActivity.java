package com.cscigroup9.myapplication;

import androidx.appcompat.app.AppCompatActivity;

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

    private View mathLayout;
    private TextView mathDisplayText;
    private EditText mathAnswer;
    private Button mathSubmitButton;
    private ArithmeticMaker arithmeticMaker;
    private AlgebraMaker algebraMaker;


    private ArrayList<Integer> taskListIds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("EEEE DisarmActivity", "onCreate start");

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        setContentView(R.layout.activity_disarm);

        boolean isAlgebra = intent.getBooleanExtra("isAlgebra", true);
        int numTasks = intent.getIntExtra("numTasks", 5);

        Log.d("EEEE DisarmActivity", "got intent");

        Random rand = new Random();

        arithmeticMaker = new ArithmeticMaker();
        algebraMaker = new AlgebraMaker(); //Instantiate math problem makers

        //Each kind of task has an id. We add the id to the taskList in order to allow it for this
        //alarm. 1 = arithmetic, 2 = algebra,

         //Add ids of allowed tasks to the arraylist.
        taskListIds = new ArrayList<>();
        taskListIds.add(1); //Arithmetic always allowed
        if(isAlgebra)
            taskListIds.add(2); //Add algebra if allowed

        Log.d("EEEE DisarmActivity", "taskListIds created successfully");

        doArithmeticTask(arithmeticMaker);

//        for(int i = 1; i <= numTasks; i++){ //Repeat for total tasks
//
//            Log.d("EEEE DisarmActivity", "for loop, i = " + i);
//
//            int chosen = rand.nextInt(taskListIds.size()); //Returns a random index of the taskListIds array
//            switch(taskListIds.get(chosen)){
//                case 1: //Id 1 = arithmetic
//                    doArithmeticTask(arithmeticMaker);
//                    break;
//                case 2: //Id 2 = algebra
//                    Log.d("EEEE DisarmActivity", "for loop, algebra");
//                    doAlgebraTask();
//                    break;
//            }
//
//
//
//        }


    }

    private void doArithmeticTask(ArithmeticMaker arithMaker){

        arithMaker.generateArithmetic(0); //Get new problem

        mathLayout = findViewById(R.id.MathLinearLayout);
        mathDisplayText = findViewById(R.id.MathDisplayText);
        mathAnswer = findViewById(R.id.MathAnswerText);
        mathSubmitButton = findViewById(R.id.MathSubmitButton); //Attach objects

        StringBuilder problemString = new StringBuilder();
        int charsToGet = arithMaker.numOperators * 2 + 1; //We need to get the numbers and the
                                                        //operators between.
        boolean gettingNum = true; //We will alternate between retrieving a number and operator.
        int index = 0;

        for(int i = 0; i < charsToGet; i++){

            index = (int) Math.floor((double) i / 2); //The index of
            if(gettingNum){
                problemString.append(arithMaker.nums[index]);
            }
            else{
                problemString.append(arithMaker.operators[index]);
            }

            problemString.append(" ");

            gettingNum = !gettingNum;
        }

        mathDisplayText.setText(problemString.toString());
        mathLayout.setVisibility(View.VISIBLE); //Display problem

        Log.d("EEEE DisarmActivity", "solution = " + String.valueOf(arithMaker.solution));


        mathSubmitButton.setOnClickListener(v -> {
            Log.d("EEEE DisarmActivity", "answer = " + mathAnswer.getText());
            if(arithMaker.solution - Integer.parseInt(String.valueOf(mathAnswer.getText())) == 0){
                disarmAlarm(); //Kills everything
            }
        });

    }

    private void doAlgebraTask(){

    }


    public void disarmAlarm(){
        Intent intentService = new Intent(getApplicationContext(), AlarmNotifier.class);
        getApplicationContext().stopService(intentService);
        finish();
    }


}