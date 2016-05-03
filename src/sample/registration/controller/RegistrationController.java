package sample.registration.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.dataBase.BDConnection;
import sample.dialogForms.successfulREG.SuccessfulRegistrationWindow;
import sample.dialogForms.unseccessfulREG.UnsuccessfulWindow;
import sample.model.User;
import java.sql.ResultSet;


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
    private Window parent;

    public void setParent(Window parent){
        this.parent = parent;
    }

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
            String sql = "SELECT * FROM public.\"user\" WHERE \"username\"='"+getUsernameField().getText()+"';";
            try {
                ResultSet resultSet = BDConnection.createSelectQuery(sql);
                if (!resultSet.next()){
                  User  newUser = new User(usernameField.getText(),
                            passwordField.getText(),
                            firstName.getText(),
                            lastName.getText(),
                            eMail.getText());
                    sql = "INSERT INTO public.\"user\"(username, password, name, surname, email) VALUES " +
                            "('" + newUser.getUsername() +
                            "','" + newUser.getPassword() +
                            "','" + newUser.getFirstName() +
                            "','" + newUser.getLastName() +
                            "','" + newUser.geteMail() + "');";
                    BDConnection.createOtherQueries(sql);
                    sql = "INSERT INTO public.\"MaxWeights\"(user_id) VALUES ('" +newUser.getID()+ "');";
                    BDConnection.createOtherQueries(sql);
                    SuccessfulRegistrationWindow successfulWindow = new SuccessfulRegistrationWindow();
                    successfulWindow.setUser(newUser);
                    successfulWindow.start(new Stage());
                    parent.hide();
                    ((Node) event.getSource()).getScene().getWindow().hide();
                }
                else{
                    UnsuccessfulWindow unsuccessfulWindow = new UnsuccessfulWindow();
                    unsuccessfulWindow.start(new Stage());
                }
            } catch (Exception ex) {
                UnsuccessfulWindow unsuccessfulWindow = new UnsuccessfulWindow();
                try {
                    unsuccessfulWindow.start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ex.printStackTrace();
            }
        }else{
            text.setText("Fill all fields please");
            text.setFill(Color.RED);
            text.setVisible(true);
        }
    }

    private boolean checkAll() {
        return (!firstName.getText().equals("")&&!lastName.getText().
                equals("")&&!eMail.getText().equals("")&&!usernameField.getText().equals("")&&!passwordField.getText().
                equals("")&&!confirmPasswordField.getText().equals(""));
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