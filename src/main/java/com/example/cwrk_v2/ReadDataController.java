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

public class ReadDataController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    Label queryResult;
    @FXML
    TextField yearTF;

    public void goToMainMenuEvent(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("loginScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void submitQuery(ActionEvent event) throws SQLException, ClassNotFoundException {
        DBActions dba = new DBActions();
        try {
            queryResult.setText(dba.showRacesPerYear(Integer.parseInt(yearTF.getText().toString())));
        }catch (NullPointerException e){
            System.out.println(e);
        }
    }
}
