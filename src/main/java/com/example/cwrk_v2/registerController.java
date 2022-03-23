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

public class registerController {
    //inject fxml to access the field
    @FXML
    TextField usernameTField;
    @FXML
    TextField passwordTField;
    @FXML
    TextField confirmPasswordTField;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void logInToStartMenu(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        //extract the textfiled value
        String username = usernameTField.getText();
        String password = passwordTField.getText();
        String confirmPassword = confirmPasswordTField.getText();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("startingScene.fxml"));
            root = loader.load();

            startingSceneController scene2Controller = loader.getController();
            scene2Controller.displayName(username);

            //  root = FXMLLoader.load(getClass().getResource("startingScene.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        public void goToLogIn(ActionEvent event) throws IOException{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginScene.fxml"));
            root = loader.load();

            //  root = FXMLLoader.load(getClass().getResource("startingScene.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    public void registerUser (ActionEvent event){

    }

}