package sample.mainProgram;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;


public class MainWindow extends Application {
    private Stage mainStage;
    private MainController mainController;
    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;
        Font.loadFont(getClass().getResource("/sample/registration/view/registration.ttf").toExternalForm(),15);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/MainWindowView.fxml"));
        Parent root = loader.load();
        mainController = loader.getController();
        ArrayList<GridPane> pages = new ArrayList<>();
        for (int i = 0; i <2 ; i++) {
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("view/CentralPane"+i+".fxml"));
            pages.add(loader1.load());
        }
        mainController.addGridsArray(pages.toArray(new GridPane[pages.size()]));
        mainController.setLableUser("admin");
        primaryStage.setTitle("Main");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
