package com.company;

import java.util.Set;

public class Num2 extends  Element {

    public Num2() {
        this.generate();
    }

    // --> After this point we know that we will have only one operation left and as such we have to make sure that the resulting value can end being single digit after that last operation.
    // As such, loopControl set containing all the values that meet that condition is created and only one of those values will be accepted as Num2.
    // --> upperControl is used to ensure we do not get an error if we happen to get a current value which gets all its divisors used before reaching an acceptable value for Num2.
    // If we get a current value of 19 and division was tried twice, we would get an error.
    @Override
    void generate() {

        boolean unassigned=true;
        Set <Integer> loopControl = Set.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,20,21,24,25,27,28,30,32,35,36,40,42,45,48,49,54,56,63,64,72,81);
        Divisors currentDivisors = new Divisors(current);
        int lowerControl = 2;
        if (current > 80) lowerControl = 3;
        if (current > 90) lowerControl = 4;
        int upperControl = 4;
        int result = 0;

        while (unassigned) {

            int number = new RandomNumber().getRandom(1,9);
            int operation = new RandomNumber().getRandom(lowerControl,upperControl);

            number = operation == 4 ? currentDivisors.getDivisor() : number;

            if (number == 0){
                upperControl = 3;
            } else {
                result = super.operate(operation, number);
            }


            if (loopControl.contains(result)){
                unassigned=false;
                super.value = number;
                current=result;
                solution[1] = operation;
            }
        }
    }
}
