package sample.logIn.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import sample.dataBase.BDConnection;
import sample.logIn.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;


public class LogInController {
    @FXML
    private GridPane MainPane;
    @FXML
    private Button Registration;
    @FXML
    private Button Sign;
    @FXML
    private PasswordField PassField;
    @FXML
    private TextField NameField;
    @FXML
    private User unloginedUser;

    public PasswordField getPassField() {
        return PassField;
    }
    public TextField getNameField() {
        return NameField;
    }
    @FXML
    private void logInPressed(ActionEvent actionEvent) {
        String sql = "SELECT \"password\" FROM public.\"user\" WHERE \"username\"='"+NameField.getText()+"';";
        ResultSet resultSet= BDConnection.createSelectQuery(sql);
        try {
            resultSet.next();
//            if (resultSet!=null) System.out.println(resultSet.getString("Password")); else System.out.println("resultset is null");
            if (resultSet.getString("password").equals(PassField.getText())) {
                 unloginedUser = new User(NameField.getText(),PassField.getText());
                System.out.println("New object of user created successful");
                ((Node)actionEvent.getSource()).getScene().getWindow().hide();
            }
        } catch (SQLException e) {
            System.out.println("Не получилось, не фартануло");
        }
    }


    public void setDisableButtonSign(String current){
        if (current.equals("")) {
            Sign.setDisable(true);
        }else
            Sign.setDisable(false);
    }

    public void setDisablePassField(String current) {
        if (current.equals("")) {
            PassField.setDisable(true); Sign.setDisable(true);
        }
        else {
            PassField.setDisable(false);
            if (!PassField.getText().equals(""))
                Sign.setDisable(false);
        }
        }
}
