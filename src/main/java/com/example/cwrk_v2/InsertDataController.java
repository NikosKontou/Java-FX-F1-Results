package com.example.cwrk_v2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class InsertDataController {
    @FXML
    Label nameLabel;
    private Stage stage;
    private Scene scene;
    private Parent root;



    //to back button fortwnei diaforetiko scene sto stage
    public void goToMainMenuEvent (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("startingScene.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
