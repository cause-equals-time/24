package com.company;


public abstract class Element {

    static int current = 24;
    static int [] solution = new int [3];
    int value;

    int operate (int operation, int number){
        switch (operation){
            case 1:
                return current*number;
            case 2:
                return current+number;
            case 3:
                return current-number;
            default:
                return current/number;
        }
    }

    abstract void generate();

    int [] getSolution(){
        return solution;
    }
}
