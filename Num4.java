package com.company;

public class Num4 extends  Element {

    public Num4() {
        this.generate();
    }

    // --> Num4 will be equal to the current value after all 3 operations have been performed.
    // --> Contains a getter for the static array solution, which stores the 3 performed operations.
    @Override
    void generate() {
        super.value=current;
        current=24;
    }
}