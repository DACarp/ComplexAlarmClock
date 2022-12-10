package com.cscigroup9.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ArithmeticGame extends Fragment {

    private View mathLayout;
    private TextView mathDisplayText;
    private EditText mathAnswer;
    private Button mathSubmitButton;
    private ArithmeticMaker arithmeticMaker;
    private AlgebraMaker algebraMaker;


    private ArrayList<Integer> taskListIds;

    public ArithmeticGame() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_arithmetic_game, container, false);

        ArithmeticMaker arithMaker = new ArithmeticMaker();
        arithMaker.generateArithmetic(0); //Get new problem

        mathLayout = view.findViewById(R.id.MathLinearLayout);
        mathDisplayText = view.findViewById(R.id.MathDisplayText);
        mathAnswer = view.findViewById(R.id.MathAnswerText);
        mathSubmitButton = view.findViewById(R.id.MathSubmitButton); //Attach objects

        StringBuilder problemString = new StringBuilder();
        int charsToGet = arithMaker.numOperators * 2 + 1; //We need to get the numbers and the
        //operators between.
        boolean gettingNum = true; //We will alternate between retrieving a number and operator.
        int index = 0;

        for (int i = 0; i < charsToGet; i++) {

            index = (int) Math.floor((double) i / 2); //The index of
            if (gettingNum) {
                problemString.append(arithMaker.nums[index]);
            } else {
                problemString.append(arithMaker.operators[index]);
            }

            problemString.append(" ");

            gettingNum = !gettingNum;
        }

        mathDisplayText.setText(problemString.toString());
        mathLayout.setVisibility(View.VISIBLE); //Display problem

        //Log.d("EEEE ArithmeticGame", "solution = " + String.valueOf(arithMaker.solution));


        mathSubmitButton.setOnClickListener(v -> {

            //Log.d("EEEE ArithmeticGame", "answer = " + mathAnswer.getText());

            boolean correct = false;

            try { //Input is text field, therefore we must catch the exception.
                if (arithMaker.solution - Integer.parseInt(String.valueOf(mathAnswer.getText())) == 0) {
                    correct = true;
                }
            }
            catch(Exception ignored){

            }

            if(correct){
                Bundle bundle = new Bundle();
                bundle.putBoolean("disarmed", true);
                getParentFragmentManager().setFragmentResult("disarmed", bundle);
            }

        });

        return view;
    }
}