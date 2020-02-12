package com.company;

public class Num1 extends  Element {

    public Num1() {
        this.generate();
    }

    // --> No limitations here, any operation can be performed on the current value, using any number 1-9.
    @Override
    void generate() {

        int number = new RandomNumber().getRandom(1,9);
        int operation = new RandomNumber().getRandom(1,4);
        number = operation == 4 ? new Divisors(current).getDivisor() : number;
        int result = super.operate (operation, number);

        super.value = number;
        current = result;
        solution[0] = operation;
    }
}
