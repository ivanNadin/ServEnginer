package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class AddNewClientController extends StartUpController{
    @FXML
    private Button addClient;
    @FXML
    private Button backButton;
    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField contPerson;
    @FXML
    private TextField phoneNum;

    public void addNewClient() throws SQLException {
        insertClient(name.getText(),address.getText(),contPerson.getText(),phoneNum.getText());

    }

    public void onInputText(){
        System.out.println("Hello");
    }

    public void backPage() throws IOException {
        changePage("/view/MenuView.fxml",backButton);
    }
    public void insertClient(String name,String address,String contPerson,String pnoneNum) throws SQLException {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            // db parameters
            String url = "jdbc:sqlite:C:/Users/ivan_/Documents/App.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String insertTableSQL = "INSERT INTO client"
                + "(name_c, adress_c, contPerson, phoneNum) VALUES"
                + "(?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, address);
        preparedStatement.setString(3, contPerson);
        //System.out.println(pnoneNum);
        preparedStatement.setString(4, pnoneNum);
        preparedStatement .executeUpdate();
        {
            try {
                if (conn != null) {

                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
