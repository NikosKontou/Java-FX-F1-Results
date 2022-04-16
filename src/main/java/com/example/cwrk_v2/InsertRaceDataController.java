package com.example.cwrk_v2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Stream;

public class InsertRaceDataController {
    @FXML
    Label nameLabel, submitResultLabel;
    @FXML
    TextField year, trackName, round, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20;

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
    if ((checkForNulls(trackName, year, round, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20) )&&(dba.insertRace(trackName.getText().toString(), Integer.parseInt(year.getText().toString()), Integer.parseInt(round.getText().toString()), Integer.parseInt(p1.getText().toString())
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
                , Integer.parseInt(p20.getText().toString())
        )
        )){
            submitResultLabel.setTextFill(Color.rgb(60, 250, 60));
            submitResultLabel.setText("Entry created!");


    } else{
        submitResultLabel.setTextFill(Color.rgb(250,60,60));
        submitResultLabel.setText("Please fill every field and see log for details");
    }
    }

    private static Boolean checkForNulls (TextField year,TextField trackName,TextField round,TextField p1,TextField p2,TextField p3,TextField p4,TextField p5,TextField p6,TextField p7,TextField p8,TextField p9,TextField p10,TextField p11,TextField p12,TextField p13,TextField p14,TextField p15,TextField p16,TextField p17,TextField p18,TextField p19,TextField p20) {
        if (Stream.of(year, trackName, round, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20).anyMatch(textField -> textField.getText().isEmpty())) {
            return false;
        } else return true;
    }
}
