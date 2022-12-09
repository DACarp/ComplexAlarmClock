package com.cscigroup9.myapplication;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class guessIt extends Fragment {

    public guessIt(){}

    int chosenNum;
    static int getRand(int max, int min)
    {
        return (int)((Math.random()
                * (max - min)) + min);
    }

    public void commentz(String str)
    {
        Toast.makeText(
                        getActivity(),
                        str,
                        Toast.LENGTH_SHORT)
                .show();
    }

    public void clickFunction(View view)
    {
        int userGuess;
        EditText variable
                = (EditText)view.findViewById(
                R.id.guessItEditText);
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
            completePuzzle();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memory_game, container, false);

        int minNum = 1;
        int maxNum = 10;
        chosenNum = getRand(minNum, maxNum);

        return view;
    }

    public void completePuzzle() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("disarmed",true);
        getParentFragmentManager().setFragmentResult("disarmed",bundle);

    }
}