package controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;


public class CreateNewSheetController extends StartUpController implements Initializable {
    @FXML
    private Button createNewSheet;
    @FXML
    private AnchorPane Apane;
    @FXML
    private GridPane myPane;
    @FXML
    private TextField clientName;
    @FXML
    private ComboBox<String> cbClientName;
    @FXML
    private Button sShot;

    @FXML
    private Button backButton;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {


        try {
            cbClientName.getItems().addAll(fillComBox());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void onSnapSot(){
        WritableImage image = myPane.snapshot(new SnapshotParameters(), null);
        // TODO: probably use a file chooser here
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(null);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            // TODO: handle exception here
        }
    }

    public void onClickCreatNS() throws Exception {
        String[] dbInf;
        dbInf = connect(cbClientName.getValue().toString());
        //System.out.println(cbClientName.getItems().toString());
        VBox vBox = new VBox();
        for (int i=0; i != dbInf.length;i++){
            vBox.getChildren().addAll(new TextField(dbInf[i]));
        }
        myPane.add(vBox,0,getRowCount(myPane)+1);
    }

    public void backPage() throws IOException {
        changePage("/view/MenuView.fxml",backButton);
    }

    private int getRowCount(GridPane pane) {
        int numRows = pane.getRowConstraints().size();
        for (int i = 0; i < pane.getChildren().size(); i++) {
            Node child = pane.getChildren().get(i);
            if (child.isManaged()) {
                Integer rowIndex = GridPane.getRowIndex(child);
                if(rowIndex != null){
                    numRows = Math.max(numRows,rowIndex+1);
                }
            }
        }
        return numRows;
    }
    private String[] connect(String clientName) throws SQLException {

        Connection conn = null;
        String[] allInf = new String[4];
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
       PreparedStatement stmt = conn.prepareStatement("SELECT * FROM client WHERE name_c = ?");
       // ResultSet rs = stmt.executeQuery( "SELECT * FROM client WHERE name_c = ?" );
        stmt.setString(1,clientName);
        ResultSet rs = stmt.executeQuery();
        while ( rs.next() ) {
            String  name = rs.getString("name_c");
            String  address = rs.getString("adress_c");
            String  contPerson = rs.getString("contPerson");
            String  phoneNum = rs.getString("phoneNum");
            allInf[0] = name;
            allInf[1] = address;
            allInf[2] = contPerson;
            allInf[3] = phoneNum;
        }
        rs.close();
        stmt.close();
        {
            try {
                if (conn != null) {

                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return allInf;
    }

    public List<String> fillComBox() throws SQLException {

        Connection conn = null;
        List<String> allInf = new ArrayList<String>() ;
        try {
            Class.forName("org.sqlite.JDBC");
            // db parameters
            String url = "jdbc:sqlite:C:/Users/ivan_/Documents/App.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

           // System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        PreparedStatement stmt = conn.prepareStatement("SELECT name_c FROM client");
        // ResultSet rs = stmt.executeQuery( "SELECT * FROM client WHERE name_c = ?" );
        ResultSet rs = stmt.executeQuery();
        while ( rs.next() ) {
            //int id = rs.getInt("id_c");
            String  name = rs.getString("name_c");
            allInf.add(name);
//            System.out.println( "ADDRESS = " + address );
//            System.out.println( "contPerson = " + contPerson );
//            System.out.println( "phoneNum= " + phoneNum );
//            System.out.println();
        }
        rs.close();
        stmt.close();
        {
            try {
                if (conn != null) {

                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return allInf;
    }
}
