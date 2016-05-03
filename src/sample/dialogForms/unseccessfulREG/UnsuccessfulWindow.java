package sample.dialogForms.unseccessfulREG;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Dmytro on 3/2/2016.
 */
public class UnsuccessfulWindow extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("unsuccess.fxml"));
        Parent root =  loader.load();
        primaryStage.setTitle("Warning");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void closeWindow(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
}
