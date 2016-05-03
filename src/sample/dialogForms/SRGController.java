package sample.dialogForms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import sample.mainProgram.MainWindow;
import sample.model.User;

/**
 * Created by Dmytro on 01.05.2016.
 */
public class SRGController {
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
        MainWindow mainWindow = new MainWindow(user);
        try {
            mainWindow.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
