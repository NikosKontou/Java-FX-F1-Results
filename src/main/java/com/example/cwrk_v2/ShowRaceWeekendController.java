package com.example.cwrk_v2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ShowRaceWeekendController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    Label nameLabel, qualifyingResult, raceResult, changedPositions;
    @FXML
    TextField yearTF, roundTF;


    public void initialize(){
        nameLabel.setText(UserHolder.getUserName());
    }

    public void goToMainMenuEvent (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("startingScene.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void submitRaceWeekendQuery(ActionEvent event) throws SQLException, ClassNotFoundException {
    DBActions dba = new DBActions();
        System.out.println(dba.showRaceWeekend(2022,1));
    }
}
