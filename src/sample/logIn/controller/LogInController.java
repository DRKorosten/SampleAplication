package sample.logIn.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.dataBase.BDConnection;
import sample.logIn.model.User;
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
    private User unloginedUser = User.USER_WITHOUT_DATA;

    public PasswordField getPassField() {
        return PassField;
    }
    public TextField getNameField() {
        return NameField;
    }
    @FXML
    private void logInPressed(ActionEvent actionEvent) {
//        String sql = "SELECT \"password\" FROM public.\"User\" WHERE \"username\"='"+NameField.getText()+"';";
//        ResultSet resultSet= BDConnection.createSelectQuery(sql);
//        try {
//            resultSet.next();
//            if (resultSet.getString("password").equals(PassField.getText())) {
//                System.out.println("New object of user created successful");
                ((Node)actionEvent.getSource()).getScene().getWindow().hide();
                MainWindow mainWindow = new MainWindow();
                try {
                    mainWindow.start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
//            else messageToUser.setVisible(true);
//        } catch (SQLException e) {
//            messageToUser.setVisible(true);
//        }
//    }


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
        try {
            registrationWindow.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
