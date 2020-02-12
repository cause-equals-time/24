package com.company;

import java.util.Map;

public class ClassBoard {

    private Num1 n1;
    private Num2 n2;
    private Num3 n3;
    private Num4 n4;

    public ClassBoard() {
        this.n1 = new Num1();
        this.n2 = new Num2();
        this.n3 = new Num3();
        this.n4 = new Num4();
    }

    public int getN1() {
        return n1.value;
    }

    public int getN2() {
        return n2.value ;
    }

    public int getN3() {
        return n3.value;
    }

    public int getN4() {
        return n4.value;
    }


    // --> Retrieves the solution to the puzzle, by back tracing the performed operations:
    // --> (((Num4.value operation3 Num3.value) operation2 Num2.value) operation1 Num1.value)
    // --> Map operations relates the operation numbers used to the corresponding char, already reversed in order to back trace.

    String getSolution(){
        Map <Integer,String> operations = Map.of(1,"/",2,"-",3,"+",4,"*");
        StringBuilder expression = new StringBuilder().append(n4.value).append(operations.get(n4.getSolution()[2])).append(n3.value);

        // --> Brackets will only be required if the first operation is addition or subtraction and the subsequent multiplication or division.

        if ((n4.getSolution()[2] == 2 || n4.getSolution()[2] == 3) && (n4.getSolution()[1] == 1 || n4.getSolution()[1] == 4)){
            expression.insert(0,'(').append(')');
        }
        expression.append(operations.get(n4.getSolution()[1])).append(n2.value);

        // --> Brackets required here if the second operation is addition or subtraction and the last one multiplication or division.
        // --> 2+5+1*3 has to be shown as (2+5+1)*3. 1*7+5*2 has to be shown as (1*7+5)*2.
        // --> All Nums are calculated based on the value set by the previous operation, as such we have to reflect that when providing the solution.

        if ((n4.getSolution()[1] == 2 || n4.getSolution()[1] == 3) && (n4.getSolution()[0] == 1 || n4.getSolution()[0] == 4)){
            expression.insert(0,'(').append(')');
        }
        expression.append(operations.get(n4.getSolution()[0])).append(n1.value);

        return expression.toString();

    }
}
