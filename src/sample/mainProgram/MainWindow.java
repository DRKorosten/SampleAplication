package sample.mainProgram;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.UserData.MaxWeightPane;
import sample.model.MaxWeightData;
import sample.model.User;
import java.util.ArrayList;

public class MainWindow extends Application {
    private final int PAGES = 2;
    private User currentUser;
    private MainController mainController;

    public MainWindow(User user){
       currentUser = user;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Font.loadFont(getClass().getResource("/sample/registration/view/registration.ttf").toExternalForm(),15);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/MainWindowView.fxml"));
        Parent root = loader.load();
        mainController = loader.getController();
        mainController.setUser(currentUser);
        ArrayList<Pane> pages = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>(16);
        names.add("Жим лёжа штанги");
        names.add("Жим лёжа штанги под наклоном");
        names.add("Жим лёжа гантелей под наклоном");
        names.add("Разводка рук");
        names.add("Становая тяга");
        names.add("Тяга верхнего блока к груди");
        names.add("Тяга штанги в наклоне");
        names.add("Тяга штанги к подбородку");
        names.add("Приседание со штангой на плечах");
        names.add("Жим штанги узким хватом");
        names.add("Французский жим");
        names.add("Жим штанги от груди стоя или сидя");
        names.add("Подъем штанги на бицепс стоя");
        names.add("Молоток");
        names.add("Концентрированный подъем на бицепс");
        names.add("Выпады с гантелями");
        MaxWeightData data = new MaxWeightData(currentUser,names);
        MaxWeightPane maxWeightPane = new MaxWeightPane(data);
        pages.add(maxWeightPane);
        for (int i = 0; i <PAGES ; i++) {
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("view/forGreed/CentralPane"+i+".fxml"));
            pages.add(loader1.load());
        }
        mainController.addGridsArray(pages);
        if (currentUser!=null)
        mainController.setCorrectNameSurnameLabel();
        primaryStage.setTitle("Main");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
