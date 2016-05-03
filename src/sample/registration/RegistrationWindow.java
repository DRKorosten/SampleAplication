package sample.registration;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.logIn.LogInWindow;
import sample.registration.controller.RegistrationController;

/**
 * Created by Dmytro on 3/2/2016.
 */
public class RegistrationWindow extends Application{
    private Window parent;
    private RegistrationController registrationController;

    public void setParent(Window parent){
        this.parent = parent;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Font.loadFont(getClass().getResource("/sample/registration/view/registration.ttf").toExternalForm(),15);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/registration.fxml"));
        Parent root =  loader.load();
        registrationController = loader.getController();
        registrationController.setParent(parent);
        registrationController.getUsernameField().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                registrationController.manageNameField(newValue);
            } });
        registrationController.getPasswordField().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                registrationController.managePassField(newValue);
            } });
        registrationController.getConfirmPasswordField().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                registrationController.manageConfirmPassField(newValue);
            } });
        primaryStage.setTitle("Registration");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
