package com.cscigroup9.myapplication;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.io.ObjectInputStream;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.

 */
public class GetItRight extends Fragment {

    private int count = 0;
    private int r1, r2;
    private ObjectInputStream.GetField buttonStates;

    private Button b;
    private Button b2;

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_get_it_right, container, false);

       b = (Button) view.findViewById(R.id.buttonl);
       b2= (Button) view.findViewById(R.id.buttonr);

        giveNewRandom(); //will allow giveNewRandom to generate numbers
        return view;
    }

    private void giveNewRandom () {
        //if statement makes condition that when player hits 5 points, game is completed
        if(count>=5){
            count=0;
            completePuzzle();
        }
        else
        {
            Random rand = new Random();
            r1 = rand.nextInt(10);
            //while loop ensures different numbers are generated in each button each play
            while (true) {
                r2 = rand.nextInt(10);
                if (r1 != r2)
                    break;
            }
        }

        b.setText(r1);

        b2.setText(r2);

    }

    public void onClickLeft(View view) {
        //todo
        if(r1 > r2)
            count++;
        else
            count--;
        //displays the variables
        TextView txt = (TextView) view.findViewById(R.id.textViewResult);
        txt.setText(R.string.points + count); //displays current points in textViewResult Button when player selects a button
        giveNewRandom(); //allows onClickLeft to generate random numbers between 1-10

    }

    public void onClickRight(View view) {
        //todo
        if(r1 < r2)
            count++;
        else
            count--;
        TextView txt = (TextView) view.findViewById(R.id.textViewResult);
        txt.setText(R.string.points + count);//displays current points in textViewResult button when player selects a button
        giveNewRandom(); //allows onClickLeft to generate random numbers between 1-10
    }

    public void completePuzzle() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("disarmed",true);
        getParentFragmentManager().setFragmentResult("disarmed",bundle);

    }

}
