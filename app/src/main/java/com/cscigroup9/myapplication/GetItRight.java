package com.cscigroup9.myapplication;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        giveNewRandom(); //will allow giveNewRandom to generate numbers

    }

    private void giveNewRandom () {
        //if statement makes condition that when player hits 5 points, game is completed
        if(count==5){
            count=0;
            //toast message will congratulate winner once 5 points are reached
            Toast.makeText(this, "You won!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Random rand = new Random();
            r1 = rand.nextInt(10);
            //while loop ensures different numbers are generated in each button each play
            while (true) {
                r2 = rand.nextInt(10);
                if (r1 != r2) ;
                break;
            }
        }
        Button b = (Button) b.findViewById();
        b.setText(Integer.toString(r1));
        Button b2= (Button) b2.findViewById();
        b2.setText(Integer.toString(r2));

    }

    public void onClickLeft(View view) {
        //todo
        if(r1 > r2)
            count++;
        else
            count--;
        //displays the variables
        TextView txt = (TextView) view.findViewById();
        txt.setText("Points: "+count); //displays current points in textViewResult Button when player selects a button
        giveNewRandom(); //allows onClickLeft to generate random numbers between 1-10

    }

    public void onClickRight(View view) {
        //todo
        if(r1 < r2)
            count++;
        else
            count--;
        TextView txt = (TextView) view.findViewById();
        txt.setText("Points: "+count);//displays current points in textViewResult button when player selects a button
        giveNewRandom(); //allows onClickLeft to generate random numbers between 1-10
    }

    public void completePuzzle() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("disarmed",true);
        getParentFragmentManager().setFragmentResult("disarmed",bundle);

    }

}
