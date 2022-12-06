package com.cscigroup9.myapplication;

import java.util.Random;

public class AlgebraMaker {

    //This class creates a random simple algebra problem by taking an arithmetic problem and
    //'rephrasing' the question.

    public int maskedNumber; //The position of the number in the equation to be replaced with 'X',
                            //the value of which is the solution to the task.
    //Ex: in  6 / 3 + 2 - 4 = 0, a maskedNumber of 2 would represent the 3, which should be hidden
    //when the task renders this equation, and 3 would be the solution the user must enter.

    public int num1;
    public int num2;
    public int num3;
    public int num4;

    public String[] ops = new String[3]; //Operators in the equation

    public int result; //Number that goes after the =


    public AlgebraMaker(){
        generateAlgebra();
    }

    public void generateAlgebra(){
        ArithmeticMaker Arithmetic = new ArithmeticMaker(3); //Create arithmetic problem with
                                                            //3 operators

        Random rand = new Random();
        maskedNumber = rand.nextInt(5) + 1; //Choose a random number to mask. Leftmost is 1.

        num1 = Arithmetic.nums[0];
        num2 = Arithmetic.nums[1];
        num3 = Arithmetic.nums[2];
        num4 = Arithmetic.nums[3];

        result = Arithmetic.solution;

        for(int i = 0; i < 3; i++){

            switch(Arithmetic.operators[i]){ //Converts the operators to strings to hopefully ease displaying.
                case 1:{
                    ops[i] = "+";
                    break;
                }
                case 2:{
                    ops[i] = "-";
                    break;
                }
                case 3:{
                    ops[i] = "*";
                    break;
                }
                case 4:{
                    ops[i] = "/";
                    break;
                }
            }
        }

    }


}
