package sample.UserData;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.dataBase.BDConnection;
import sample.dialogForms.successfulMWEditRow.SuccessfulMWEditRow;
import sample.model.MaxWeightData;

public class MaxWeightPane extends BorderPane{
    public MaxWeightPane(MaxWeightData maxWeightData){
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
        for (String key: maxWeightData.getData().keySet() ) {
            Label label = new Label(key);
            label.setTextFill(Color.CYAN);
            TextField textField = new TextField(maxWeightData.getData().get(key).toString());
            Button button = new Button("Save");
            button.setOnAction(event -> {
                int row1 = GridPane.getRowIndex(button);
               if (checkString(textField.getText())){
                   String sql = "UPDATE public.\"MaxWeights\" SET \"ex"+(row1+1)+
                           "\" = '"+textField.getText()+
                           "' WHERE \"user_id\"='"+maxWeightData.getUser().getID()+"';";
                   BDConnection.createOtherQueries(sql);
                   textField.setText(new Double(textField.getText()).toString());
                   textField.setStyle("-fx-background-color: white");
                   SuccessfulMWEditRow successfulMWEditRow = new SuccessfulMWEditRow();
                   try {
                       successfulMWEditRow.start(new Stage());
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }else{

               }
            });
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.equals("")){
                    textField.setStyle("-fx-background-color: white");
                    button.setDisable(true);
                }else {
                    if (checkString(newValue)) {
                        textField.setStyle("-fx-background-color: greenyellow");
                        button.setDisable(false);
                    } else {
                        textField.setStyle("-fx-background-color: lightcoral");
                        button.setDisable(true);
                    }
                }
            });
            GridPane.setConstraints(label,0,row);
            GridPane.setConstraints(textField,1,row);
            GridPane.setConstraints(button,2,row++);
            gridPane.getChildren().addAll(label,textField,button);
        }
        scrollPane.setContent(gridPane);
        scrollPane.getStylesheets().add("/sample/UserData/MaxWeightStyle.css");
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        setCenter(scrollPane);
    }
    private boolean checkString(String string) {
        try {
            Double.parseDouble(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
