package com.cscigroup9.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class guessIt extends AppCompatActivity {

    int chosenNum;
    static int getRand(int max, int min)
    {
        return (int)((Math.random()
                * (max - min)) + min);
    }

    public void commentz(String str)
    {
        Toast.makeText(
                        guessIt.this,
                        str,
                        Toast.LENGTH_SHORT)
                .show();
    }
    public void clickFunction(View view)
    {
        int userGuess;
        EditText variable
                = (EditText)findViewById(
                R.id.editId);
        userGuess
                = Integer.parseInt(
                variable
                        .getText()
                        .toString());
        if (userGuess < chosenNum) {

            commentz("Nah go higher, ur wrong");
        }
        else if (userGuess > chosenNum) {
            commentz("Nah go lower, ur wrong");
        }
        else {
            commentz(
                    "AYEEE,"
                            +" You Got it!");
        }
    }

    @Override
    protected void onCreate(
            Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int minNum = 1;
        int maxNum = 3;
        chosenNum = getRand(minNum, maxNum);
    }
}