package com.company;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class Stimer {

    double time;
    Controller ctrl;
    Timeline timer;

    public Stimer(double time, Controller ctrl) {
        this.time = time + 0.101;
        this.ctrl = ctrl;
    }

    public double getTime() {
        return time;
    }

    public void start(){
        timer = new Timeline();
        timer.setCycleCount(Timeline.INDEFINITE);

        KeyFrame frame = new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                time=time-0.1;
                String timeString = String.format("%.2f", time);
                ctrl.labelSeconds.setText(timeString.substring(0,timeString.length()-3));
                ctrl.labelDseconds.setText(timeString.substring(timeString.length()-2));

                if (time<0.1) {
                    ctrl.labelConsole.setText("Solution: " + ctrl.board.getSolution());
                    ctrl.labelConsole.setStyle("-fx-background-color:red");
                    ctrl.labelResult.setText("you are out of time!");
                    ctrl.gameOver=true;
                    ctrl.ngames=0;
                    ctrl.labelThescore.setText((String.valueOf(Integer.parseInt(ctrl.labelThescore.getText())/2)));
                    ctrl.controlButton.setText("next");
                    ctrl.controlButton.setStyle("-fx-font-weight:bold");
                    timer.stop();
                }

            }
        });
        timer.getKeyFrames().add(frame);
        timer.playFromStart();
    }

    public void stop(){
        timer.stop();
    }
}
