package com.cscigroup9.myapplication;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemoryGame extends Fragment {

    private static final int NUM_BUTTONS = 16;
    private static final int NUM_ROWS = 4;
    private static final int NUM_COLS = 4;

    private List<Boolean> buttonStates;
    private List<Button> buttons;

    private int progress;

    public MemoryGame() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memory_game, container, false);

        // Initialize the list of Button views
        buttons = new ArrayList<>();

        // Generate a random pattern of buttons that should be "on" and "off"
        generateButtonStates();

        TableLayout tableLayout = (TableLayout) view.findViewById(R.id.table_layout);

        // Iterate over the rows and columns of the grid
        for (int i = 0; i < NUM_ROWS; i++) {
            TableRow row = new TableRow(getActivity());
            for (int j = 0; j < NUM_COLS; j++) {
                // Create a new Button for each position in the grid
                Button button = new Button(getActivity());

                // Add the Button to the list of Button views
                buttons.add(button);

                // Add the Button to the TableRow
                row.addView(button);

                // If the button is part of the pattern light it up until we're finished
                if(buttonStates.get(buttons.indexOf(button))) {
                    button.setBackgroundColor(Color.GREEN);
                }
                else {
                    button.setBackgroundColor(Color.WHITE);
                }
            }
            // Add the TableRow to the TableLayout
            tableLayout.addView(row);
        }

        for (int i = 0; i < NUM_BUTTONS; i++) {
            Button button = buttons.get(i);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Check if the clicked Button is part of the pattern
                    checkButton((Button) view);
                }
            });
        }

        Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < NUM_BUTTONS; i++) {
                    Button button = buttons.get(i);
                    // If the Button is part of the pattern, set its background color to the default
                    if (buttonStates.get(i)) {
                        button.setBackgroundColor(ContextCompat.getColor(getActivity(), android.R.color.white));
                    }
                }
            }
        };

        // Post the Runnable to the Handler with a delay of 2 seconds
        handler.postDelayed(runnable, 2000);

        return view;
    }

    private void generateButtonStates() {
        // Initialize the list of booleans that represents the on/off state of the buttons
        buttonStates = new ArrayList<>();

        // Generate a list of booleans where each value is set to true or false randomly
        Random random = new Random();
        for (int i = 0; i < NUM_BUTTONS; i++) {
            Boolean present = random.nextBoolean();
            buttonStates.add(present);
            if(present) {
                progress--;
            }
        }
    }

    private void checkButton(Button button) {
        int index = buttons.indexOf(button);

        // Check if the clicked Button is part of the pattern through its truthfulness
        if (buttonStates.get(index)) {

            // If the Button is part of the pattern, change its color to indicate that it is part of the pattern
            button.setBackgroundColor(Color.GREEN);

            buttonStates.set(index,false);
            progress++;

            if (progress >= 0) {
                completePuzzle();
            }
        }
    }

    public void completePuzzle() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("disarmed",true);
        getParentFragmentManager().setFragmentResult("disarmed",bundle);

    }
}