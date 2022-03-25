package com.example.cwrk_v2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class startingSceneController {
    @FXML
    Label nameLabel;
    @FXML
    Button insertData;
    @FXML
    Button insertDriverData;
    private Stage stage;
    private Scene scene;
    private Parent root;
    String username;
    //pairnei to username kai to provalei kata tin ekinisi tou controller
    //o initialize ekteleite kathe fora stin arxi
    public void initialize(){
        nameLabel.setText(UserHolder.getUserName());
        if (UserHolder.isIsAdmin()){
            insertData.setVisible(true);
            insertDriverData.setVisible(true);
        } else {
            insertData.setVisible(false);
            insertDriverData.setVisible(false);

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
