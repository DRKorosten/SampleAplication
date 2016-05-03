package sample.dataEdit.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.changePass.ChangePassWindow;
import sample.dataBase.BDConnection;
import sample.dialogForms.successfulMWEditRow.SuccessfulMWEditRow;
import sample.mainProgram.MainController;
import sample.model.User;

public class EditDataController {
    private User user;
    private MainController mainController;
    @FXML
    private Button changePassButton;
    @FXML
    private TextField firstName;
    @FXML
    private TextField eMail;
    @FXML
    private TextField lastName;
    @FXML
    private Button saveButton;


    public void savePressed(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
        String sql;
        if (!firstName.getText().equals(user.getFirstName())){
            sql = "UPDATE public.\"user\" SET \"name\"='"+firstName.getText()+"' WHERE \"id\"='"+user.getID()+"';";
            BDConnection.createOtherQueries(sql);
            user.setFirstName(firstName.getText());
        }
        if (!lastName.getText().equals(user.getLastName())){
            sql = "UPDATE public.\"user\" SET \"surname\"='"+lastName.getText()+"' WHERE \"id\"='"+user.getID()+"';";
            BDConnection.createOtherQueries(sql);
            user.setLastName(lastName.getText());
        }
        if (!eMail.getText().equals(user.geteMail())){
            sql = "UPDATE public.\"user\" SET \"email\"='"+eMail.getText()+"' WHERE \"id\"='"+user.getID()+"';";
            BDConnection.createOtherQueries(sql);
            user.seteMail(eMail.getText());
        }
        SuccessfulMWEditRow successfulRegistrationWindow = new SuccessfulMWEditRow();
        try {
            successfulRegistrationWindow.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mainController.setCorrectNameSurnameLabel();

    }
    public void setParent(MainController mainController){
        this.mainController = mainController;
    }

    public void createDependenses(){
        firstName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals(user.getFirstName())){
                if (!newValue.equals("")){
                firstName.setStyle("-fx-background-color: greenyellow");
                checkAll();
            }else{
                    firstName.setStyle("-fx-background-color: lightcoral");
                    saveButton.setDisable(true);
                }
            }else{
                firstName.setStyle("-fx-background-color: white");
                saveButton.setDisable(true);
            }
        });
        lastName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals(user.getLastName())){
                if (!newValue.equals("")){
                    lastName.setStyle("-fx-background-color: greenyellow");
                    checkAll();
                }else{
                    lastName.setStyle("-fx-background-color: lightcoral");
                    saveButton.setDisable(true);
                }
            }else{
                lastName.setStyle("-fx-background-color: white");
                saveButton.setDisable(true);
            }
        });
        eMail.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals(user.geteMail())){
                if (!newValue.equals("")){
                    eMail.setStyle("-fx-background-color: greenyellow");
                    checkAll();
                }else{
                    eMail.setStyle("-fx-background-color: lightcoral");
                    saveButton.setDisable(true);
                }
            }else{
                eMail.setStyle("-fx-background-color: white");
                saveButton.setDisable(true);
            }
        });
    }

    private void checkAll() {
        if (!firstName.getText().equals(user.getFirstName())||!lastName.getText().equals(user.getLastName())||!eMail.getText().equals(user.geteMail())){
            if (!firstName.getText().equals("")||!lastName.getText().equals("")||!eMail.getText().equals("")){
                saveButton.setDisable(false);
            }else{
                saveButton.setDisable(true);
            }
        }
    }

    public void applyPrimarySettings(User user) {
        this.user = user;
        firstName.setText(user.getFirstName());
        eMail.setText(user.geteMail());
        lastName.setText(user.getLastName());
    }

    public void changePass(ActionEvent event) {
        ChangePassWindow changePassWindow = new ChangePassWindow();
        changePassWindow.setUser(user);
        try {
            changePassWindow.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exit(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
}