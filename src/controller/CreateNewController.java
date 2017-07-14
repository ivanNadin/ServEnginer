package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class CreateNewController extends StartUpController {
    @FXML
    private Button createNew;
    @FXML
    private Button addNewClient;

    public void onClickCreateNew() throws Exception {
           changePage("/view/CreateNewView.fxml",createNew);

    }
    public void onClickAddNewClient()throws Exception {
        changePage("/view/AddNewClientView.fxml",addNewClient);

    }

}
