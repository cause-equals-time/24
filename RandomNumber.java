package com.company;

public class RandomNumber {

    int getRandom(int lower, int upper) {
        return (int)(Math.random() * ((upper-lower)+1)+lower);
    }
}
