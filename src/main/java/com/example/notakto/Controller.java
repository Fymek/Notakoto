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
    private Button  button01, button02, button03, button04, button05, button06, button07, button08, button09,button010, button011, button012, button013, button014, button015, button016,
                    button11, button12, button13, button14, button15, button16, button17, button18, button19,button110, button111, button112, button113, button114, button115, button116,
                    button21, button22, button23, button24, button25, button26, button27, button28, button29,button210, button211, button212, button213, button214, button215, button216;
    @FXML
    private Text winnerText1, winnerText2, winnerText3, titleText, playerText;
    private final int sizeOfBoard = 4;
    private int tura = 0;
    private ArrayList<Boolean> finishedBoards = new ArrayList<>(Arrays.asList(false,false,false));
    private ArrayList<Button> buttons1,buttons2,buttons3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons1 = new ArrayList<>(Arrays.asList(button01, button02, button03, button04, button05, button06, button07, button08, button09,button010, button011, button012, button013, button014, button015, button016));
        buttons2 = new ArrayList<>(Arrays.asList(button11, button12, button13, button14, button15, button16, button17, button18, button19,button110, button111, button112, button113, button114, button115, button116));
        buttons3 = new ArrayList<>(Arrays.asList(button21, button22, button23, button24, button25, button26, button27, button28, button29,button210, button211, button212, button213, button214, button215, button216));

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
        StringBuilder line1, line2, line3= new StringBuilder(), line4= new StringBuilder();
        StringBuilder wintext= new StringBuilder();
        wintext.append("X".repeat(sizeOfBoard));
        for(int n = 0; n<sizeOfBoard; n++)
        {
            line1 = new StringBuilder();
            line2 = new StringBuilder();
            for(int m = 0; m<sizeOfBoard; m++)
            {
                line1.append(buttons.get(sizeOfBoard * n + m).getText());
                line2.append(buttons.get(sizeOfBoard * m + n).getText());
            }
            line3.append(buttons.get(n * (sizeOfBoard + 1)).getText());
            line4.append(buttons.get((n+1) * (sizeOfBoard - 1)).getText());
            if (line1.toString().equals(wintext.toString()) || line2.toString().equals(wintext.toString())){
                winnerText.setText("Plansza skonczona!");
                buttons.forEach(button -> button.setDisable(true));
                return true;
            }
        }
        if (line3.toString().equals(wintext.toString()) || line4.toString().equals(wintext.toString())){
            winnerText.setText("Plansza skonczona!");
            buttons.forEach(button -> button.setDisable(true));
            return true;
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