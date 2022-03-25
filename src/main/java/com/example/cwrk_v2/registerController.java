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

public class registerController {
    //inject fxml to access the field
    @FXML
    TextField usernameTField;
    @FXML
    TextField passwordTField;
    @FXML
    TextField confirmPasswordTField;
    @FXML
    Label messageLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;


        public void goToLogIn(ActionEvent event) throws IOException{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginScene.fxml"));
            root = loader.load();

            //  root = FXMLLoader.load(getClass().getResource("startingScene.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    public void registerUser (ActionEvent event) throws SQLException, ClassNotFoundException {
        //extract the textfiled value
        String username = usernameTField.getText();
        String password = passwordTField.getText();
        DBActions dba = new DBActions();
        String confirmPassword = confirmPasswordTField.getText();
        if (password.equals(confirmPassword)){
            if(dba.userRegister(username, password)){
                messageLabel.setTextFill(Color.rgb(30,250,80));
                messageLabel.setText("New user was created");

            }else {
                messageLabel.setTextFill(Color.rgb(250,80,30));
                messageLabel.setText("username must be unique");
            }
        }else   {
            messageLabel.setTextFill(Color.rgb(250,80,30));
            messageLabel.setText("Passwords do not match, try again");
        }

    }

}