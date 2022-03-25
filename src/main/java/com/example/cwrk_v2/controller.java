package com.example.cwrk_v2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class controller {
    //inject fxml to access the field
    @FXML
     TextField usernameTField;
    @FXML
    TextField passwordTField;
    @FXML
    Label warningLogIn;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void logInToStartMenu(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        //extract the textfiled value
        String username = usernameTField.getText();
        String password = passwordTField.getText();

        DBActions dba = new DBActions();
        //ean i leitourgia epistrpsi true, tha paei sto epomeno scene, alliws tha emfanisei minima lathous
        boolean[] res;
        res =dba.userLogIn(username, password);
        if(res[0]){
            UserHolder.setUserName(username);
            if (res[1]){
                UserHolder.setIsAdmin(true);
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("startingScene.fxml"));
            root = loader.load();
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            warningLogIn.setTextFill(Color.rgb(250,80,30));
            warningLogIn.setText("You typed wrong credentials, try again or sign up");
        }


    }

    public void logoutFromMainMenu( ActionEvent event) throws IOException {

    }
    //dimiourgia table stin DB me onoma test
    public void createAndPopulateTables( ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        DBActions db = new DBActions();
        //db.createTables();
        db.populateTables();
        System.out.println("tables created!");
    }
    public void registerUser(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("registerScene.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
