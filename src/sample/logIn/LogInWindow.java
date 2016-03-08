package sample.logIn;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.logIn.controller.LogInController;

public class LogInWindow extends Application {
     private LogInController logInController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/logInView.fxml"));
        Parent root =  loader.load();
        logInController = loader.getController();
        logInController.getNameField().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                logInController.setDisablePassField(newValue);
            } });
        logInController.getPassField().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                logInController.setDisableButtonSign(newValue);
            }
        });
        primaryStage.setTitle("Log In");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
      launch(args);

    }
}
