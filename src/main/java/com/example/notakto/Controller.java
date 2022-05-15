package com.example.notakto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private Button button11;

    @FXML
    private Button button21;

    @FXML
    private Button button31;

    @FXML
    private Button button41;

    @FXML
    private Button button51;

    @FXML
    private Button button61;

    @FXML
    private Button button71;

    @FXML
    private Button button81;

    @FXML
    private Button button91;

    @FXML
    private Button button111;

    @FXML
    private Button button211;

    @FXML
    private Button button311;

    @FXML
    private Button button411;

    @FXML
    private Button button511;

    @FXML
    private Button button611;

    @FXML
    private Button button711;

    @FXML
    private Button button811;

    @FXML
    private Button button911;

    @FXML
    private Text winnerText1;
    @FXML
    private Text winnerText2;
    @FXML
    private Text winnerText3;
    @FXML
    private Text titleText;

    private int availableBoard = 3;
    boolean[] finishedBoards = new boolean[3];

    ArrayList<Button> buttons1;
    ArrayList<Button> buttons2;
    ArrayList<Button> buttons3;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons1 = new ArrayList<>(Arrays.asList(button1,button2,button3,button4,button5,button6,button7,button8,button9));
        buttons2 = new ArrayList<>(Arrays.asList(button11,button21,button31,button41,button51,button61,button71,button81,button91));
        buttons3 = new ArrayList<>(Arrays.asList(button111,button211,button311,button411,button511,button611,button711,button811,button911));

        buttons1.forEach(button ->{
            setupButton(button);
            button.setFocusTraversable(false);
        });
        buttons2.forEach(button ->{
            setupButton(button);
            button.setFocusTraversable(false);
        });
        buttons3.forEach(button ->{
            setupButton(button);
            button.setFocusTraversable(false);
        });
    }

    @FXML
    void restartGame(ActionEvent event) {
        finishedBoards[0]=false;
        finishedBoards[1]=false;
        finishedBoards[2]=false;
        buttons1.forEach(this::resetButton);
        buttons2.forEach(this::resetButton);
        buttons3.forEach(this::resetButton);
        titleText.setText("NOTAKTO");
        winnerText1.setText("1 PLANSZA");
        winnerText2.setText("2 PLANSZA");
        winnerText3.setText("3 PLANSZA");
    }

    public void resetButton(Button button){
        button.setDisable(false);
        button.setText("");
    }

    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
            checkIfGameIsOver();
        });
    }

    public void setPlayerSymbol(Button button){
            button.setText("X");
    }

    public void checkIfGameIsOver(){

        if(finishedBoards[0] == false) {
            for (int a = 0; a < 8; a++) {

                String line1 = switch (a) {
                    case 0 -> button1.getText() + button2.getText() + button3.getText();
                    case 1 -> button4.getText() + button5.getText() + button6.getText();
                    case 2 -> button7.getText() + button8.getText() + button9.getText();
                    case 3 -> button1.getText() + button5.getText() + button9.getText();
                    case 4 -> button3.getText() + button5.getText() + button7.getText();
                    case 5 -> button1.getText() + button4.getText() + button7.getText();
                    case 6 -> button2.getText() + button5.getText() + button8.getText();
                    case 7 -> button3.getText() + button6.getText() + button9.getText();
                    default -> null;
                };

                //X winner
                if (line1.equals("XXX")) {
                    finishedBoards[0]=true;
                    winnerText1.setText("1 plansza skonczona!");
                }


            }
        }
        if(finishedBoards[1] == false) {
            for (int a = 0; a < 8; a++) {

                String line1 = switch (a) {
                    case 0 -> button11.getText() + button21.getText() + button31.getText();
                    case 1 -> button41.getText() + button51.getText() + button61.getText();
                    case 2 -> button71.getText() + button81.getText() + button91.getText();
                    case 3 -> button11.getText() + button51.getText() + button91.getText();
                    case 4 -> button31.getText() + button51.getText() + button71.getText();
                    case 5 -> button11.getText() + button41.getText() + button71.getText();
                    case 6 -> button21.getText() + button51.getText() + button81.getText();
                    case 7 -> button31.getText() + button61.getText() + button91.getText();
                    default -> null;
                };

                //X winner
                if (line1.equals("XXX")) {
                    finishedBoards[1]=true;
                    winnerText2.setText("2 plansza skonczona!");
                }



            }
        }
        if(finishedBoards[2] == false) {
            for (int a = 0; a < 8; a++) {

                String line1 = switch (a) {
                    case 0 -> button111.getText() + button211.getText() + button311.getText();
                    case 1 -> button411.getText() + button511.getText() + button611.getText();
                    case 2 -> button711.getText() + button811.getText() + button911.getText();
                    case 3 -> button111.getText() + button511.getText() + button911.getText();
                    case 4 -> button311.getText() + button511.getText() + button711.getText();
                    case 5 -> button111.getText() + button411.getText() + button711.getText();
                    case 6 -> button211.getText() + button511.getText() + button811.getText();
                    case 7 -> button311.getText() + button611.getText() + button911.getText();
                    default -> null;
                };

                //X winner
                if (line1.equals("XXX")) {
                    finishedBoards[2]=true;
                    winnerText3.setText("3 plansza skonczona!");
                }


            }
        }
    }
}