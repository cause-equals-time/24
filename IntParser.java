package com.company;

public class IntParser {

    String expression;
    int currentPos = -1;
    int currentChar;

        public IntParser(String expression) {
            this.expression = expression;
        }

        void setExpression(String expression){
            this.expression = expression;
        }

        private void nextChar(){
            currentChar = (++currentPos < expression.length()) ? expression.charAt(currentPos) : -1;
        }

        private boolean eat(int ch){
            while (currentChar == ' ') nextChar();
            if (currentChar == ch) {
                nextChar();
                return true;
            }
            return false;
        }

        int evaluate() {
            nextChar();
            int x = prsExpression();
            if (currentPos < expression.length()) throw new RuntimeException("Unexpected: " + (char)currentChar);
            return x;
        }

        private int prsExpression() {
            int x = prsTerm();
            for (;;) {
                if      (eat('+')) x += prsTerm();
                else if (eat('-')) x -= prsTerm();
                else return x;
            }
        }

        private int prsTerm() {
            int x = prsFactor();
            for (;;) {
                if      (eat('*')) x *= prsFactor();
                else if (eat('/')) x /= prsFactor();
                else return x;
            }
        }

        private int prsFactor() {
            if (eat('+')) return prsFactor();
            if (eat('-')) return -prsFactor();

            int x;
            int startPos = currentPos;
            if (eat('(')) {
                x = prsExpression();
                eat(')');
            } else if (currentChar >= '0' && currentChar <= '9') {
                while (currentChar >= '0' && currentChar <= '9') nextChar();
                x =  Integer.parseInt(expression.substring(startPos, currentPos));
            } else {
                throw new RuntimeException("Unexpected: " + (char)currentChar);
            }
            return x;
        }


}
