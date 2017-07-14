package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MenuController extends StartUpController{
    @FXML
    private Button button;
    @FXML
    private TextField login;
    @FXML
    private PasswordField pass;

    public void onClickMethod() throws Exception{

        changePage("/view/MenuView.fxml",button);
    }
}
