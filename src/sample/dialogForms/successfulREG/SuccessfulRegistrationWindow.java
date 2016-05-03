package sample.dialogForms.successfulREG;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.User;

public class SuccessfulRegistrationWindow extends Application {
    SRGController srgController;
    User user;

    public void setUser(User newUser){
        user = newUser;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("successReg.fxml"));
        Parent root =  loader.load();
        srgController = loader.getController();
        srgController.setUser(user);
        primaryStage.setTitle("Congratulations");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
