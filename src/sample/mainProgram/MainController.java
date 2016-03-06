package sample.mainProgram;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.ListIterator;

public class MainController {
    public BorderPane currentScene;
    @FXML
    private Button buttonLeft;
    @FXML
    private Button buttonRight;
    @FXML
    private Label lableUser;
    private GridPane[] pages;
    private int current;

    public void addGridsArray(GridPane[] pages){
        this.pages = new GridPane[pages.length+1];
        for (int i = 0; i < pages.length; i++) {
          this.pages[i+1] = pages[i];
        }
        this.pages[0] = new GridPane();
        current = 0;
        currentScene.setCenter(this.pages[current]);
    }

    @FXML
    private void previusPage(ActionEvent event) {
        if (current>0){
            currentScene.setCenter(pages[--current]);
        }
    }
    @FXML
    private void nextPage(ActionEvent event) {
        if (current<pages.length-1){
            currentScene.setCenter(pages[++current]);
        }
    }
}

