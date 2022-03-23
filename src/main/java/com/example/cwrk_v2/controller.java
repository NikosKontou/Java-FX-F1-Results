package com.example.cwrk_v2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class controller {
    //inject fxml to access the field
    @FXML
     TextField usernameTField;
    @FXML
    TextField passwordTField;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void logInToStartMenu(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        //extract the textfiled value
        String username = usernameTField.getText();
        String password = passwordTField.getText();
        System.out.println("uname is: "+username + " password is: "+password);
        DBActions dba = new DBActions();
        dba.userLogIn(username, password);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("startingScene.fxml"));
        root = loader.load();

        startingSceneController scene2Controller = loader.getController();
        scene2Controller.displayName(username);

      //  root = FXMLLoader.load(getClass().getResource("startingScene.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void logoutFromMainMenu( ActionEvent event) throws IOException {

    }
    public void createAndPopulateTables( ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        DBActions db = new DBActions();
       // db.createTables();
        //db.populateTables();
        System.out.println("tables created!");
    }
}
