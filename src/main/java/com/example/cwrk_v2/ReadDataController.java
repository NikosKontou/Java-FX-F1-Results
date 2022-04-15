package com.example.cwrk_v2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReadDataController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    Label queryResultTrackName, queryResultP1, queryResultP2, queryResultP3, queryResultP4, queryResultP5, queryResultP6, queryResultP7, queryResultP8, queryResultP9, queryResultP10,
            queryResultP11, queryResultP12, queryResultP13, queryResultP14, queryResultP15, queryResultP16, queryResultP17, queryResultP18, queryResultP19, queryResultP20,
            trackNameLabel, firstLabel, secondLabel, thirdLabel, p4Label, p5Label, p6Label, p7Label, p8Label, p9Label, p10Label, p11Label, p12Label, p13Label,
            p14Label, p15Label, p16Label, p17Label, p18Label, p19Label, p20Label;

    @FXML
    TextField yearTF;
    public void initialize(){



    }

    public void goToMainMenuEvent(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("startingScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void submitQuery(ActionEvent event) throws SQLException, ClassNotFoundException {
        DBActions dba = new DBActions();
        ArrayList<ArrayList> yearResult = new ArrayList<>();
        //set headers visible only after submiting a request
        trackNameLabel.setVisible(true);
        firstLabel.setVisible(true);
        secondLabel.setVisible(true);
        thirdLabel.setVisible(true);
        p4Label.setVisible(true);
        p5Label.setVisible(true);
        p6Label.setVisible(true);
        p7Label.setVisible(true);
        p8Label.setVisible(true);
        p9Label.setVisible(true);
        p10Label.setVisible(true);
        p11Label.setVisible(true);
        p12Label.setVisible(true);
        p13Label.setVisible(true);
        p14Label.setVisible(true);
        p15Label.setVisible(true);
        p16Label.setVisible(true);
        p17Label.setVisible(true);
        p18Label.setVisible(true);
        p19Label.setVisible(true);
        p20Label.setVisible(true);
        //Group headerLabels = new Group();
        //add every header label in a group so that it will be easier to controll it and change the visibility
        //headerLabels.getChildren().addAll(trackNameLabel, firstLabel, secondLabel, thirdLabel, p4Label, p5Label, p6Label, p7Label, p8Label, p9Label, p10Label, p11Label, p12Label, p13Label, p14Label, p15Label, p16Label, p17Label, p18Label, p19Label, p20Label);
       // headerLabels.setVisible(true);
        try {
      yearResult = dba.showRacesPerYear(Integer.parseInt(yearTF.getText().toString()));
            //provoli olwn ton apotelesmatwn enos sigkekrimenou etous
            //to arraylist molis ginetai to string prosthetei "[" "," kai "[" ta opoia fenontia asximai kai sinepos afairounte
            queryResultTrackName.setText(yearResult.get(0).toString().replace(",", "").replace("[", "").replace("]", ""));
            //set the styling for the podium finishers
            queryResultP1.setBackground(new Background(new BackgroundFill(Color.GOLD, CornerRadii.EMPTY, Insets.EMPTY)));
            queryResultP2.setBackground(new Background(new BackgroundFill(Color.SILVER, CornerRadii.EMPTY, Insets.EMPTY)));
            queryResultP3.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
            //set the styling for odd place finishers in order to improove readability
            queryResultP5.setBackground(new Background(new BackgroundFill(Color.rgb(220, 220, 220), CornerRadii.EMPTY, Insets.EMPTY)));
            queryResultP7.setBackground(new Background(new BackgroundFill(Color.rgb(220, 220, 220), CornerRadii.EMPTY, Insets.EMPTY)));
            queryResultP9.setBackground(new Background(new BackgroundFill(Color.rgb(220, 220, 220), CornerRadii.EMPTY, Insets.EMPTY)));
            queryResultP9.setBackground(new Background(new BackgroundFill(Color.rgb(220, 220, 220), CornerRadii.EMPTY, Insets.EMPTY)));
            queryResultP11.setBackground(new Background(new BackgroundFill(Color.rgb(220, 220, 220), CornerRadii.EMPTY, Insets.EMPTY)));
            queryResultP13.setBackground(new Background(new BackgroundFill(Color.rgb(220, 220, 220), CornerRadii.EMPTY, Insets.EMPTY)));
            queryResultP15.setBackground(new Background(new BackgroundFill(Color.rgb(220, 220, 220), CornerRadii.EMPTY, Insets.EMPTY)));
            queryResultP17.setBackground(new Background(new BackgroundFill(Color.rgb(220, 220, 220), CornerRadii.EMPTY, Insets.EMPTY)));
            queryResultP19.setBackground(new Background(new BackgroundFill(Color.rgb(220, 220, 220), CornerRadii.EMPTY, Insets.EMPTY)));
            //exception handling must be impemented in case of no data returned by the query
            //populate the labels with data
            queryResultP1.setText(yearResult.get(1).get(0).toString());
            queryResultP2.setText(yearResult.get(1).get(1).toString());
            queryResultP3.setText(yearResult.get(1).get(2).toString());
            queryResultP4.setText(yearResult.get(1).get(3).toString());
            queryResultP5.setText(yearResult.get(1).get(4).toString());
            queryResultP6.setText(yearResult.get(1).get(5).toString());
            queryResultP7.setText(yearResult.get(1).get(6).toString());
            queryResultP8.setText(yearResult.get(1).get(7).toString());
            queryResultP9.setText(yearResult.get(1).get(8).toString());
            queryResultP10.setText(yearResult.get(1).get(9).toString());
            queryResultP11.setText(yearResult.get(1).get(10).toString());
            queryResultP12.setText(yearResult.get(1).get(11).toString());
            queryResultP13.setText(yearResult.get(1).get(12).toString());
            queryResultP14.setText(yearResult.get(1).get(13).toString());
            queryResultP15.setText(yearResult.get(1).get(14).toString());
            queryResultP16.setText(yearResult.get(1).get(15).toString());
            queryResultP17.setText(yearResult.get(1).get(16).toString());
            queryResultP18.setText(yearResult.get(1).get(17).toString());
            queryResultP19.setText(yearResult.get(1).get(18).toString());
            queryResultP20.setText(yearResult.get(1).get(19).toString());
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }
}
