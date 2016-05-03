package sample.mainProgram.view.forGreed.UserData;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import java.util.LinkedHashMap;

/**
 * Created by Dmytro on 3/9/2016.
 */
public class MaxWeightPane extends BorderPane{
    public MaxWeightPane(LinkedHashMap<String,Double> cells){
        super();
        setStyle("-fx-background-color: rgba(0, 100, 100, 0.0)");
        setPrefSize(1200,500);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-background-color: rgba(0, 100, 100, 0.0)");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        GridPane gridPane = new GridPane();
        gridPane.setOpacity(1);
        gridPane.setStyle("-fx-background-color: rgba(0, 100, 100, 0.0)");
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        int row = 0;
        for (String key: cells.keySet() ) {
            Label label = new Label(key);
            label.setOpacity(1);
            label.setFont(new Font("Arial",36));
            TextField textField = new TextField(cells.get(key).toString());
            Button button = new Button("Save");
            GridPane.setConstraints(label,0,row);
            GridPane.setConstraints(textField,1,row);
            GridPane.setConstraints(button,2,row++);
            gridPane.getChildren().addAll(label,textField,button);
        }
        scrollPane.setContent(gridPane);
        scrollPane.getStylesheets().add("/sample/mainProgram/view/forGreed/UserData/MaxWeightStyle.css");
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        setCenter(scrollPane);
    }

}
