package com.cscigroup9.myapplication;

import java.util.Random;

public class ArithmeticMaker {

    //This class generates a random arithmetic problem when needed. By running the generateArithmetic
    //function, this class's variables are set to values that can be used to construct an arithmetic
    //problem for DisarmActivity.

    public int numOperators; //Number of operators in the problem
    public int[] nums = new int[4];  //Up to 4 numbers are used in these problems.
    public int[] operators = new int[3]; //Up to 3 operations are performed on the numbers. The first
                        // may be any operator, +-*/    1=+, 2=-, 3=*, 4=/
                        // The other two may only be +-.


    public int solution; //Solution to check against for completion of the task.

    public ArithmeticMaker(){ //Creates a problem when first constructed.
        generateArithmetic(0);
    }
    public ArithmeticMaker(int i){
        generateArithmetic(i);
    }

    public void generateArithmetic(int givenOperators){ //Give a 0 to pick randomly, 1-3 to choose
                                    //number of operators, also picks randomly for invalid integers.

        Random rand = new Random();
        int[] niceDividends = new int[]{2, 4, 6, 8, 9, 10, 12, 14, 15, 16};
        //First 10 numbers that can be divided easily.


        if(givenOperators > 0 && givenOperators <=3)//Pick or choose randomly
            numOperators = givenOperators;
        else
            numOperators = rand.nextInt(3) + 1; //Randomly choose 1, 2, or 3 operators

        operators[0] = rand.nextInt(4) + 1; //Randomly choose +, -, *, /
        operators[1] = rand.nextInt(2) + 1; //Randomly choose +, -
        operators[2] = rand.nextInt(2) + 1; //Randomly choose +, -

        nums[0] = rand.nextInt(10) + 1; //Randomly pick a number between 1 and 10
        nums[1] = rand.nextInt(10) + 1;
        nums[2] = rand.nextInt(10) + 1;
        nums[3] = rand.nextInt(10) + 1;

        int temp = 0; //Temp variable to help get the solution



        for(int i = 0; i < numOperators; i++){ //Executes for each operator to be used.
            //i will be the 'operators' index of the current operator, which is placed between the
            //numbers at 'nums' indices i and i+1. For any loop other than the first, this will
            //require the operation of temp and i+1.

            switch(operators[i]){
                case 1:{ //Addition
                    if(i==0) {//First loop
                        temp = nums[0] + nums[1];
                    }
                    else //Other loops
                        temp = temp + nums[i + 1];
                    break;
                }

                case 2:{ //Subtraction
                    if(i==0) {//First loop
                        temp = nums[0] - nums[1];
                    }
                    else //Other loops
                        temp = temp - nums[i + 1];
                    break;
                }

                case 3:{ //Multiplication. First operator only.
                    temp = nums[0]*nums[1];
                    break;
                }

                case 4:{ //Division. First operator only. For this, make the numbers play 'nice'.
                    nums[0] = niceDividends[nums[0]-1]; //Translate the first number into one
                                                        //of the chosen dividends.

                    nums[1] = getNiceDivisor(nums[0]); //Get a 'nice' new number for the divisor.

                    temp = nums[0]/nums[1];
                    break;
                }

            }   //End of switch

        }       //End of for loop

        solution = temp;

    }


    private int getNiceDivisor(int divid){  //Private method for choosing a good divisor for the
                                            //given dividend
        Random divRand = new Random();
        int divisor = 1;

        switch(divid){
            case 2:{
                divisor = divRand.nextInt(2) + 1; //Choose 1 or 2
            }
            case 4:{
                int[] divisors = new int[]{1, 2, 4}; //These are the allowed divisors
                divisor = divisors[divRand.nextInt(3)]; //Choose on at random
            }
            case 6:{
                int[] divisors = new int[]{1, 2, 3, 6};
                divisor = divisors[divRand.nextInt(4)];
            }
            case 8:{
                int[] divisors = new int[]{1, 2, 4, 8};
                divisor = divisors[divRand.nextInt(4)];
            }
            case 9:{
                int[] divisors = new int[]{1, 3, 9};
                divisor = divisors[divRand.nextInt(3)];
            }
            case 10:{
                int[] divisors = new int[]{1, 2, 5, 10};
                divisor = divisors[divRand.nextInt(4)];
            }
            case 12:{
                int[] divisors = new int[]{1, 2, 3, 6, 12};
                divisor = divisors[divRand.nextInt(5)];
            }
            case 14:{
                int[] divisors = new int[]{1, 2, 7, 14};
                divisor = divisors[divRand.nextInt(4)];
            }
            case 15:{
                int[] divisors = new int[]{1, 3, 5, 15};
                divisor = divisors[divRand.nextInt(4)];
            }
            case 16:{
                int[] divisors = new int[]{1, 2, 4, 8, 16};
                divisor = divisors[divRand.nextInt(5)];
            }
        }

    return divisor;

    }


}
