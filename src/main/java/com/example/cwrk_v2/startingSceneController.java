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

public class startingSceneController {
    @FXML
    Label nameLabel;
    private Stage stage;
    private Scene scene;
    private Parent root;
    String username;
    //pairnei to username kai to provalei
    public void displayName (String username){
        UserHolder uh = null;
        //petaei exception otan einai null apo ta alla scenes pou den exoun ayti thn leitoyrgia akoma
        if (!username.isEmpty()) {
            nameLabel.setText(uh.userName);
            this.username= uh.userName;
        }
    }
    public void goToMainMenuEvent(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("loginScene.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void showDataEvent(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("readData.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void insertDataEvent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("insertRaceData.fxml"));
        root = loader.load();
        //apostoli username se allo scene gia tin provoli sto UI
        InsertRaceDataController scene2Controller = loader.getController();
        scene2Controller.displayName(username);

        //  root = FXMLLoader.load(getClass().getResource("startingScene.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void insertDriverDataEvent(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("insertData.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
