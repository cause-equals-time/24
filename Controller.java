package com.company;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import java.util.*;

public class Controller {

    @FXML
    Label labelN1, labelN2, labelN3, labelN4;

    @FXML
    Label labelConsole, labelResult, controlButton;

    @FXML
    Label labelSeconds, labelDseconds, labelThescore;



    ClassBoard board;
    Stimer gameTimer;
    boolean gameOver=true;
    double ngames=0;

    ArrayList <String> currentNums = new ArrayList<>();
    Set <String> validNums = new HashSet<>();
    Set <String> validOps = Set.of("*", "+", "-", "/", "(", ")");


    @FXML
    public void initialize() {
        controlButton.setText("start");
        controlButton.setStyle("-fx-font-weight:bold");

    }


    //labelN1.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, Insets.EMPTY)));


    @FXML
    public void onMouseOver(MouseEvent mouse) {

        Label e = ((Label)mouse.getSource());


        if (!gameOver){

            if (validNums.contains(e.getText())){
                e.setStyle("-fx-font-weight:bold; -fx-border-width: 5px");
            } else if (!e.equals(controlButton)){
                e.setStyle("-fx-font-weight:bold; -fx-border-width: 2.5px");
            }
        } else if (e.equals(controlButton)) e.setStyle("-fx-font-weight:bold; -fx-background-color:#e0b194; -fx-text-fill:#323232");

    }

        @FXML
        public void onMouseOut(MouseEvent mouse){

            if (mouse.getSource().equals(controlButton)){
                if (gameOver) controlButton.setStyle("-fx-font-weight:bold");
            } else
                {
                ((Label) mouse.getSource()).setStyle("-fx-font-weight:normal");
            }

    }

    @FXML
    public void onMouseDown(MouseEvent mouse) {

        StringBuilder console = new StringBuilder(labelConsole.getText());
        Label l = ((Label) mouse.getSource());
        String e = l.getText();

        if (!gameOver){
            if (l.equals(labelN1) || l.equals(labelN2) || l.equals(labelN3) || l.equals(labelN4)){
                if (currentNums.contains(e)){
                    console.append(e);
                    currentNums.remove((e));
                }
            } else {
                switch (e) {
                    case "x":
                        console.append("*");
                        break;
                    case "(x)":
                        if (console.length() > 0) {
                            console.insert(0,"(");
                            console.append(")");
                        }
                        break;
                    case "del":
                        if (console.length() > 0) {
                            String lastChar = String.valueOf(console.charAt(console.length() - 1));
                            if (validNums.contains(lastChar) && (currentNums.size() < 4)) {
                                currentNums.add(lastChar);
                            }
                            console.deleteCharAt(console.length() - 1);
                        }
                        break;
                    case "clear":
                        currentNums.clear();
                        currentNums.addAll(Arrays.asList(labelN1.getText(), labelN2.getText(), labelN3.getText(), labelN4.getText()));
                        console.setLength(0);
                        break;
                    case "< >":
                        break;
                    default: console.append(e);
                }
            }

            labelConsole.setText(console.toString());
            handleExpression();

        } else if (e.equals("next") || e.equals("start")){
            restart();
        }


    }

    private void restart(){
        board = new ClassBoard();
        labelN1.setText(String.valueOf(board.getN1()));
        labelN2.setText(String.valueOf(board.getN2()));
        labelN3.setText(String.valueOf(board.getN3()));
        labelN4.setText(String.valueOf(board.getN4()));
        currentNums.clear();
        currentNums.addAll(Arrays.asList(labelN1.getText(), labelN2.getText(), labelN3.getText(), labelN4.getText()));
        validNums.addAll(currentNums);
        gameOver=false;
        labelConsole.setStyle("-fx-background-color:#d46520;");
        labelConsole.setText("");
        labelResult.setText("");
        controlButton.setText("< >");
        controlButton.setStyle("-fx-font-weight:normal; -fx-border-width: 1px;");
        gameTimer = new Stimer(30, this);
        gameTimer.start();
    }

    private void handleExpression(){
        int result = -6563;

        if (!labelConsole.getText().isEmpty()){
            try{
                result = new IntParser(labelConsole.getText()).evaluate();
            }catch(RuntimeException ex){
                result = -6562;
            }
        }

        switch (result){

            case -6563:
                labelResult.setText("");
                break;
            case -6562: break;
            case 24:
                if (currentNums.isEmpty()){
                    labelConsole.setStyle("-fx-background-color:#00e30f");
                    labelResult.setText("you've got 24!");
                    gameOver=true;
                    ngames++;
                    gameTimer.stop();
                    labelThescore.setText(calculateScore());
                    controlButton.setText("next");
                    controlButton.setStyle("-fx-font-weight:bold");
                    break;
                }
            default: labelResult.setText(String.valueOf(result));
        }

    }

    private String calculateScore(){

        return (String.valueOf((int)(gameTimer.getTime()*gameTimer.getTime()/7 + 10*ngames + 70 + Integer.parseInt(labelThescore.getText()))));

    }


    @FXML
    public void onKeyDown(KeyEvent key){

        StringBuilder console = new StringBuilder(labelConsole.getText());

        int consoleLength = console.length();


        if (key.getCode() == KeyCode.BACK_SPACE && consoleLength > 0) {
            String lastChar = String.valueOf(console.charAt(consoleLength-1));
            if (validNums.contains(lastChar) && (currentNums.size()<4)){
                currentNums.add(lastChar);
            }
            console.deleteCharAt(consoleLength-1);
        } else if (currentNums.contains(key.getText()) || validOps.contains(key.getText())){
            currentNums.remove(key.getText());
            console.append(key.getText());
        }

        if (key.getCode() == KeyCode.A){
            int result = new IntParser(labelConsole.getText()).evaluate();
            console.append(" = ").append(result);
            if (result == 24) {
                labelConsole.setStyle("-fx-text-fill:green");
            } else {
                labelConsole.setStyle("-fx-text-fill:red");
            }
        }
        labelConsole.setText(console.toString());

    }

}
