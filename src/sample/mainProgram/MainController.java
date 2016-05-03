package sample.mainProgram;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.dataEdit.EditDataWindow;
import sample.logIn.LogInWindow;
import sample.model.User;

import java.util.ArrayList;


public class MainController {
    private User user;
    public BorderPane currentScene;
    @FXML
    private Button buttonLeft;
    @FXML
    private Button buttonRight;
    @FXML
    private Label labelUser;
    private ArrayList<Pane> pages;
    private int current;

    public void setCorrectNameSurnameLabel(){
        labelUser.setText(user.getFirstName() +" "+user.getLastName());
        labelUser.setFont(new Font(labelUser.getFont().getName(),30));
    }

    public void addGridsArray(ArrayList<Pane> pages){
        this.pages = new ArrayList<Pane>();
        for (int i = 0; i < pages.size(); i++) {
          this.pages.add(pages.get(i));
        }
        current = 0;
        currentScene.setCenter(this.pages.get(current));
        if (current==0||current==pages.size()-1) {
            buttonLeft.setDisable(true);
        }
    }

    @FXML
    private void previusPage(ActionEvent event) {
        if (current>0){
            currentScene.setCenter(pages.get(--current));
            buttonRight.setDisable(false);
        }
        if (current==0){
            buttonLeft.setDisable(true);
        }
    }
    @FXML
    private void nextPage(ActionEvent event) {
        if (current<pages.size()-1){
            currentScene.setCenter(pages.get(++current));
            buttonLeft.setDisable(false);
        }
        if (current==pages.size()-1){
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

    public void editProfile(ActionEvent event) {
        EditDataWindow editDataWindow = new EditDataWindow(user ,this);
        try {
            editDataWindow.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUser(User currentUser) {
        user = currentUser;
    }
}

