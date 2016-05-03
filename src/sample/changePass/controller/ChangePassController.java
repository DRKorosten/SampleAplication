package sample.changePass.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.dataBase.BDConnection;
import sample.dialogForms.successfulMWEditRow.SuccessfulMWEditRow;
import sample.model.User;



public class ChangePassController {
    private User user;
    @FXML
    private PasswordField oldPass;
    @FXML
    private PasswordField newPass;
    @FXML
    private PasswordField confirmPass;
    @FXML
    private Button saveButton;


    public void setUser(User user) {
        this.user = user;
    }
    public void defaultLink(){
        oldPass.textProperty().addListener((observable, oldValue, newValue) -> {
            oldPass.setStyle("-fx-background-color: white");
            if (newValue.equals("")) {
                newPass.setDisable(true);
                confirmPass.setDisable(true);
                saveButton.setDisable(true);
            }else {
                newPass.setDisable(false);
                if (!newPass.getText().equals("")){
                    confirmPass.setDisable(false);
                    if (confirmPass.getText().equals(newPass.getText())) {
                        saveButton.setDisable(false);
                    }else{
                        saveButton.setDisable(true);
                        confirmPass.setStyle("-fx-background-color: lightcoral");
                    }
                }else{
                    confirmPass.setDisable(true);
                    saveButton.setDisable(true);
                }
            }
        });
        newPass.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals("")){
                confirmPass.setDisable(false);
                if (confirmPass.getText().equals(newPass.getText())) {
                    confirmPass.setStyle("-fx-background-color: white");
                    newPass.setStyle("-fx-background-color: white");
                    saveButton.setDisable(false);
                }else{
                    saveButton.setDisable(true);
                }
            }else{
                confirmPass.setDisable(true);
                saveButton.setDisable(true);
            }
        });
        confirmPass.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals("")){
                if (confirmPass.getText().equals(newPass.getText())) {
                    confirmPass.setStyle("-fx-background-color: white");
                    newPass.setStyle("-fx-background-color: white");
                    saveButton.setDisable(false);
                }else{
                    confirmPass.setStyle("-fx-background-color: lightcoral");
                    saveButton.setDisable(true);
                }
            }else{
                saveButton.setDisable(true);
            }
        });
    }
    public void savePass(ActionEvent event) {
        if (oldPass.getText().equals(user.getPassword())){
            ((Node) event.getSource()).getScene().getWindow().hide();
            user.setPassword(newPass.getText());
            String sql = "UPDATE public.\"user\" SET \"password\"='"+user.getPassword()+"' WHERE \"id\"='"+user.getID()+"';";
            BDConnection.createOtherQueries(sql);
            SuccessfulMWEditRow successfulMWEditRow = new SuccessfulMWEditRow();
            try {
                successfulMWEditRow.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            oldPass.setStyle("-fx-background-color: lightcoral");
        }
    }
}
