package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * Created by ivan_ on 03.07.2017.
 */
public class StartUpController {

    protected void changePage(String urlPage, Button button) throws IOException {
        Stage curentStage = (Stage) button.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource(urlPage));
       // curentStage.setResizable(false);
        curentStage.setScene(new Scene(root));
        curentStage.show();
    }

}
