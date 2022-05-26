package com.example.notakto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
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
    private  Text winnerText1;
    @FXML
    private Text winnerText2;
    @FXML
    private Text winnerText3;
    @FXML
    private Text titleText;
    @FXML
    private Text playerText;
    private int tura=0;
    private ArrayList<Boolean> finishedBoards = new ArrayList<>(Arrays.asList(false,false,false));
    private ArrayList<Button> buttons1,buttons2,buttons3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons1 = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));
        buttons2 = new ArrayList<>(Arrays.asList(button11, button21, button31, button41, button51, button61, button71, button81, button91));
        buttons3 = new ArrayList<>(Arrays.asList(button111, button211, button311, button411, button511, button611, button711, button811, button911));

        buttons1.forEach(button -> {
            setupButton(button);
            button.setFocusTraversable(false);
        });
        buttons2.forEach(button -> {
            setupButton(button);
            button.setFocusTraversable(false);
        });
        buttons3.forEach(button -> {
            setupButton(button);
            button.setFocusTraversable(false);
        });
    }

    @FXML
    void restartGame(ActionEvent event) {
        finishedBoards.set(0,false);
        finishedBoards.set(1,false);
        finishedBoards.set(2,false);
        buttons1.forEach(this::resetButton);
        buttons2.forEach(this::resetButton);
        buttons3.forEach(this::resetButton);
        tura = 0;
        titleText.setText("NOTAKTO");
        winnerText1.setText("1 PLANSZA");
        winnerText2.setText("2 PLANSZA");
        winnerText3.setText("3 PLANSZA");
        playerText.setText("Tura gracza\n Zielonego");
    }

    public void resetButton(Button button) {
        button.setDisable(false);
        button.setText("");
    }

    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
            tura++;
            playerText.setText("Tura gracza\n "+(((tura%2)+1) ==1 ? "Zielonego":"Czerwonego"));
            checkIfGameIsOver();
        });
    }

    public void setPlayerSymbol(Button button) {
        button.setText("X");

        if((tura%2)+1 == 1){
            button.setTextFill(Color.GREEN);
        }
        else if((tura%2)+1 == 2){
            button.setTextFill(Color.RED);
        }
    }


    public boolean checkSingleBoard(ArrayList<Button> buttons,Text winnerText) {
        for (int a = 0; a < 8; a++) {
            String line1 = switch (a) {
                case 0 -> buttons.get(0).getText() + buttons.get(1).getText() + buttons.get(2).getText();// 0 1 2
                case 1 -> buttons.get(3).getText() + buttons.get(4).getText() + buttons.get(5).getText();// 3 4 5
                case 2 -> buttons.get(6).getText() + buttons.get(7).getText() + buttons.get(8).getText();// 6 7 8
                case 3 -> buttons.get(0).getText() + buttons.get(4).getText() + buttons.get(8).getText();// 0 4 8
                case 4 -> buttons.get(2).getText() + buttons.get(4).getText() + buttons.get(6).getText();// 2 4 6
                case 5 -> buttons.get(0).getText() + buttons.get(3).getText() + buttons.get(6).getText();// 0 3 6
                case 6 -> buttons.get(1).getText() + buttons.get(4).getText() + buttons.get(7).getText();// 1 4 7
                case 7 -> buttons.get(2).getText() + buttons.get(5).getText() + buttons.get(8).getText();// 2 5 8
                default -> null;
            };
            if (line1.equals("XXX")){
                winnerText.setText("Plansza skonczona!");
                buttons.forEach(button -> button.setDisable(true));
                return true;
            }
        }
        return false;
    }

    public void checkIfGameIsOver() {
        if (!finishedBoards.get(0)) finishedBoards.set(0,checkSingleBoard(buttons1,winnerText1));
        if (!finishedBoards.get(1)) finishedBoards.set(1,checkSingleBoard(buttons2,winnerText2));
        if (!finishedBoards.get(2)) finishedBoards.set(2,checkSingleBoard(buttons3,winnerText3));
        if(finishedBoards.get(0)&&finishedBoards.get(1)&&finishedBoards.get(2)) playerText.setText("Wygrał gracz "+(((tura%2)+1) ==1 ? "Zielony":"Czerwony")+".\nGra trwała "+tura+" tur.");
    }
}