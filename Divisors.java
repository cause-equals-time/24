package com.company;

import java.util.ArrayList;

public class Divisors {

    private ArrayList<Integer> listDivisors = new ArrayList<Integer>();

    // --> Opted to exclude 1 from the list of divisors for the simple reason that including it adds no real challenge.
    public Divisors(int number) {
        for (int i=2; i<=9; i++) {
            if (number % i == 0) {
                listDivisors.add(i);
            }
        }
    }

    // --> Randomly pops a divisor from the array. Returns zero if the array is empty.
    int getDivisor(){

        int length = listDivisors.size();
        if (length == 0){
            return 0;
        } else {
            return listDivisors.remove((int)(Math.random() * length));
        }
    }

}
