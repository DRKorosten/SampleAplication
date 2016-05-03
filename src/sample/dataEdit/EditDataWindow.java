package sample.dataEdit;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.dataEdit.controller.EditDataController;
import sample.mainProgram.MainController;
import sample.model.User;

/**
 * Created by Dmytro on 3/2/2016.
 */
public class EditDataWindow extends Application{
    private EditDataController editDataController;
    private MainController mainController;
    private User user;

    public EditDataWindow(User user, MainController mainController){
        this.user = user;
        this.mainController = mainController;

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Font.loadFont(getClass().getResource("/sample/registration/view/registration.ttf").toExternalForm(),15);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/editData.fxml"));
        Parent root =  loader.load();
        editDataController = loader.getController();
        editDataController.applyPrimarySettings(user);
        editDataController.createDependenses();
        editDataController.setParent(mainController);
        primaryStage.setTitle("Editing Profile");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
