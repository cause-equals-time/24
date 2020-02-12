package com.company;

public class Num3 extends  Element {

    public Num3() {
        this.generate();
    }


    // --> If current value is lesser or equal to 18 that means that single digit end-result can be achieved both by division and subtraction,
    // and as such lowerControl variable is used to ensure that only these operations will be used.
    // --> If current value is lesser than 9, that means that a single digit end result can also be attained by means of addition.
    // Multiplication is left out at this point for the number of cases where it would be useful is rather small.

    // --> If we get number == 0, that means that all the divisors have been used and it is not possible to obtain the desirable result with a division.
    // This will only happen if we get a current value of 13 or 17, as they are prime numbers.
    // They could simply be eliminated from the set of tolerated values when defining Num2 but that would limit the number of available options.
    // upperControl variable is used instead to make sure that if we have such case, division will not be tried further.
    @Override
    void generate() {

        boolean unassigned = true;
        int lowerControl = current <= 18 ? 3 : 4;
        lowerControl = current < 9 ? lowerControl-1 : lowerControl;
        int upperControl = 4;
        int result = 0;
        Divisors currentDivisors = new Divisors(current);

        while (unassigned) {
            int number = new RandomNumber().getRandom(1,9);
            int operation = new RandomNumber().getRandom(lowerControl, upperControl);
            number = operation == 4 ? currentDivisors.getDivisor() : number;

            if (number == 0){
                upperControl = 3;
            } else {
                result = super.operate(operation, number);
            }

            if (result <= 9 && result >=1) {
                unassigned = false;
                super.value = number;
                current = result;
                solution[2] = operation;
            }
        }
    }
}