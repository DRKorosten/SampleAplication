package sample.changePass;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.changePass.controller.ChangePassController;
import sample.model.User;

public class ChangePassWindow extends Application {
    private User user;
    private ChangePassController controller;

    public void setUser(User user){
        this.user = user;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/changePass.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        controller.setUser(user);
        controller.defaultLink();
        primaryStage.setTitle("Change your pass");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}