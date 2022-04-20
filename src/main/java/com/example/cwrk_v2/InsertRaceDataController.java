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
import java.util.Objects;
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
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("startingScene.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void insertRaceData(ActionEvent event) throws SQLException, ClassNotFoundException {
    DBActions dba = new DBActions();
    //if there are no null textFields and the insertRace function is successful, show the according message
    if ((checkForNulls(trackName, year, round, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20) )&&(dba.insertRace(trackName.getText(), Integer.parseInt(year.getText()), Integer.parseInt(round.getText()), Integer.parseInt(p1.getText())
                , Integer.parseInt(p2.getText())
                , Integer.parseInt(p3.getText())
                , Integer.parseInt(p4.getText())
                , Integer.parseInt(p5.getText())
                , Integer.parseInt(p6.getText())
                , Integer.parseInt(p7.getText())
                , Integer.parseInt(p8.getText())
                , Integer.parseInt(p9.getText())
                , Integer.parseInt(p10.getText())
                , Integer.parseInt(p11.getText())
                , Integer.parseInt(p12.getText())
                , Integer.parseInt(p13.getText())
                , Integer.parseInt(p14.getText())
                , Integer.parseInt(p15.getText())
                , Integer.parseInt(p16.getText())
                , Integer.parseInt(p17.getText())
                , Integer.parseInt(p18.getText())
                , Integer.parseInt(p19.getText())
                , Integer.parseInt(p20.getText())
        )
        )){
            submitResultLabel.setTextFill(Color.rgb(60, 250, 60));
            submitResultLabel.setText("Entry created!");


    } else{
        submitResultLabel.setTextFill(Color.rgb(250,60,60));
        submitResultLabel.setText("Please fill every field and see log for details");
    }
    }



    public void insertQualifyingData(ActionEvent event) throws SQLException, ClassNotFoundException {
        DBActions dba = new DBActions();
        //if there are no null textFields and the insertRace function is successful, show the according message
        if ((checkForNulls(trackName, year, round, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20) )&&(dba.insertQualifying(trackName.getText(), Integer.parseInt(year.getText()), Integer.parseInt(round.getText()), Integer.parseInt(p1.getText())
                , Integer.parseInt(p2.getText())
                , Integer.parseInt(p3.getText())
                , Integer.parseInt(p4.getText())
                , Integer.parseInt(p5.getText())
                , Integer.parseInt(p6.getText())
                , Integer.parseInt(p7.getText())
                , Integer.parseInt(p8.getText())
                , Integer.parseInt(p9.getText())
                , Integer.parseInt(p10.getText())
                , Integer.parseInt(p11.getText())
                , Integer.parseInt(p12.getText())
                , Integer.parseInt(p13.getText())
                , Integer.parseInt(p14.getText())
                , Integer.parseInt(p15.getText())
                , Integer.parseInt(p16.getText())
                , Integer.parseInt(p17.getText())
                , Integer.parseInt(p18.getText())
                , Integer.parseInt(p19.getText())
                , Integer.parseInt(p20.getText())
        )
        )){
            submitResultLabel.setTextFill(Color.rgb(60, 250, 60));
            submitResultLabel.setText("Entry created!");


        } else{
            submitResultLabel.setTextFill(Color.rgb(250,60,60));
            submitResultLabel.setText("Please fill every field and see log for details");
        }
    }
        //check that there are not blank fields submited and that the driverIDs are in the correct range
    private static Boolean checkForNulls (TextField year,TextField trackName,TextField round,TextField p1,TextField p2,TextField p3,TextField p4,TextField p5,TextField p6,TextField p7,TextField p8,TextField p9,TextField p10,TextField p11,TextField p12,TextField p13,TextField p14,TextField p15,TextField p16,TextField p17,TextField p18,TextField p19,TextField p20) {
        return Stream.of(year, trackName, round, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20).noneMatch(textField -> textField.getText().isEmpty()) && Stream.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20).noneMatch(textField -> !CheckInputs.checkDriverNumber(textField.getText()));
    }
}
