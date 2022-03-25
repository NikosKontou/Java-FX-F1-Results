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

public class InsertRaceDataController {
    @FXML
    Label nameLabel;
    @FXML
    TextField year, trackName, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initialize(){
        nameLabel.setText(UserHolder.getUserName());
    }

    //to back button fortwnei diaforetiko scene sto stage
    public void goToInsertMenuEvent (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("startingScene.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void insertRaceData(ActionEvent event) throws SQLException, ClassNotFoundException {
    DBActions dba = new DBActions();
    dba.insertRace(trackName.getText().toString(),Integer.parseInt(year.getText().toString()),  Integer.parseInt(p1.getText().toString())
            , Integer.parseInt(p2.getText().toString())
            , Integer.parseInt(p3.getText().toString())
            , Integer.parseInt(p4.getText().toString())
            , Integer.parseInt(p5.getText().toString())
            , Integer.parseInt(p6.getText().toString())
            , Integer.parseInt(p7.getText().toString())
            , Integer.parseInt(p8.getText().toString())
            , Integer.parseInt(p9.getText().toString())
            , Integer.parseInt(p10.getText().toString())
            , Integer.parseInt(p11.getText().toString())
            , Integer.parseInt(p12.getText().toString())
            , Integer.parseInt(p13.getText().toString())
            , Integer.parseInt(p14.getText().toString())
            , Integer.parseInt(p15.getText().toString())
            , Integer.parseInt(p16.getText().toString())
            , Integer.parseInt(p17.getText().toString())
            , Integer.parseInt(p18.getText().toString())
            , Integer.parseInt(p19.getText().toString())
            , Integer.parseInt(p20.getText().toString()));
    }
}
