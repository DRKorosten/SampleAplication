package sample.registration.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.dataBase.BDConnection;
import sample.dialogForms.SuccessfulWindow;
import sample.logIn.model.User;

public class RegistrationController {
    @FXML
    private Text text;
    @FXML
    private TextField firstName;
    @FXML
    private TextField eMail;
    @FXML
    private TextField lastName;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Button signUpButton;
    private User newUser = User.USER_WITHOUT_DATA;

    public TextField getUsernameField() {
        return usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public PasswordField getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public void SignUpPressed(ActionEvent event) {
        if (checkAll()&&passwordField.getText().equals(confirmPasswordField.getText())){
            text.setVisible(false);
            newUser = new User(usernameField.getText(),passwordField.getText(),firstName.getText(),lastName.getText(),eMail.getText());
            System.out.println("New user created. "+ newUser.toString());
            String sql = "INSERT INTO public.\"User\"(username, password, name, surname, email) VALUES ('"+newUser.getUsername()+"','"+newUser.getPassword()+"','"+newUser.getFirstName()+"','"+newUser.getLastName()+"','"+newUser.geteMail()+"');";
            BDConnection.createOtherQuerys(sql);
            SuccessfulWindow successfulWindow = new SuccessfulWindow();
            try {
                successfulWindow.start(new Stage());
                ((Node)event.getSource()).getScene().getWindow().hide();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            text.setText("Fill all fields please");
            text.setFill(Color.RED);
            text.setVisible(true);
        }
    }

    private boolean checkAll() {
        return (!firstName.getText().equals("")&&!lastName.getText().equals("")&&!eMail.getText().equals("")&&!usernameField.getText().equals("")&&!passwordField.getText().equals("")&&!confirmPasswordField.getText().equals(""));
    }
    public void manageNameField(String newValue) {
        if (newValue.equals("")) {
            passwordField.setDisable(true);
            confirmPasswordField.setDisable(true);
            signUpButton.setDisable(true);
        }else{
            passwordField.setDisable(false);
            if (!passwordField.getText().equals("")){
                passwordField.setDisable(false);
                if (confirmPasswordField.getText().equals("")) signUpButton.setDisable(true);
                else if (confirmPasswordField.getText().equals(passwordField.getText())){
                    confirmPasswordField.setDisable(false);
                    signUpButton.setDisable(false);
                    text.setText("Passwords equals");
                    text.setFill(Color.GREEN);
                    text.setVisible(true);
                }
            }
        }
    }
    public void managePassField(String newValue) {
        if (newValue.equals("")) {
            text.setVisible(false);
            confirmPasswordField.setDisable(true);
            signUpButton.setDisable(true);
        }else {
            confirmPasswordField.setDisable(false);
            if (confirmPasswordField.getText().equals(newValue)) {
                signUpButton.setDisable(false);
                text.setText("Passwords equals");
                text.setFill(Color.GREEN);
                text.setVisible(true);
            }else{
                text.setText("Passwords not equals");
                text.setFill(Color.RED);
                text.setVisible(true);
                signUpButton.setDisable(true);
            }
        }
    }

    public void manageConfirmPassField(String newValue) {
        if (newValue.equals("")) {
            signUpButton.setDisable(true);
            text.setText("Passwords not equals");
            text.setFill(Color.RED);
            text.setVisible(true);
        }else {
            if (newValue.equals(passwordField.getText())) {
                text.setText("Passwords equals");
                text.setFill(Color.GREEN);
                text.setVisible(true);
                signUpButton.setDisable(false);
            } else {
                text.setText("Passwords not equals");
                text.setFill(Color.RED);
                text.setVisible(true);
                signUpButton.setDisable(true);
                }
        }
    }
}