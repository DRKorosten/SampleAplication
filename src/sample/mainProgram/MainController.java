package sample.mainProgram;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.logIn.LogInWindow;

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

    public void setLableUser(String userName){
        lableUser.setText(userName);
        lableUser.setFont(new Font(lableUser.getFont().getName(),30));
    }

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
            buttonRight.setDisable(false);
        }
        if (current==0){
            buttonLeft.setDisable(true);
        }
    }
    @FXML
    private void nextPage(ActionEvent event) {
        if (current<pages.length-1){
            currentScene.setCenter(pages[++current]);
            buttonLeft.setDisable(false);
        }
        if (current==pages.length-1){
            buttonRight.setDisable(true);
        }
    }

    public void logOut(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        LogInWindow logInWindow = new LogInWindow();
        try {
            logInWindow.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

