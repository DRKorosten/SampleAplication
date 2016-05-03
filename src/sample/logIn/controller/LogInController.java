package sample.logIn.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sample.dataBase.BDConnection;
import sample.model.User;
import sample.mainProgram.MainWindow;
import sample.registration.RegistrationWindow;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInController {
    @FXML
    private Label messageToUser;
    @FXML
    private Button Sign;
    @FXML
    private PasswordField PassField;
    @FXML
    private TextField NameField;
    @FXML
    private User unloginedUser ;

    public PasswordField getPassField() {
        return PassField;
    }
    public TextField getNameField() {
        return NameField;
    }
    @FXML
    private void logInPressed(ActionEvent actionEvent) {
        String sql = "SELECT * FROM public.\"user\" WHERE \"username\"='"+NameField.getText()+"';";
        try {
            ResultSet resultSet= BDConnection.createSelectQuery(sql);
            resultSet.next();
            unloginedUser = new User(resultSet.getString("username"),
                    resultSet.getString("password"),resultSet.getString("name"),
                    resultSet.getString("surname"),resultSet.getString("email"));
            if (unloginedUser.getPassword().equals(PassField.getText())) {
                ((Node)actionEvent.getSource()).getScene().getWindow().hide();
                MainWindow mainWindow = new MainWindow(unloginedUser);
                try {
                    mainWindow.start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else messageToUser.setVisible(true);
        } catch (SQLException e) {
            messageToUser.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDisableButtonSign(String current){
        messageToUser.setVisible(false);
        if (current.equals("")) {
            Sign.setDisable(true);
        }else
            Sign.setDisable(false);
    }

    public void setDisablePassField(String current) {
        messageToUser.setVisible(false);
        if (current.equals("")) {
            PassField.setDisable(true); Sign.setDisable(true);
        }
        else {
            PassField.setDisable(false);
            if (!PassField.getText().equals(""))
                Sign.setDisable(false);
        }
        }

    public void isPressedRegistrationButton(ActionEvent event) {
        RegistrationWindow registrationWindow = new RegistrationWindow();
        registrationWindow.setParent(((Node)event.getSource()).getScene().getWindow());
        try {
            registrationWindow.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tryLogIn(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)){
            if (!Sign.isDisable()){
                logInPressed(new ActionEvent(Sign,null));
            }
        }
    }
}
