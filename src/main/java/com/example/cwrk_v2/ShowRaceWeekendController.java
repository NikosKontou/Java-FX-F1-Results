package com.example.cwrk_v2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShowRaceWeekendController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    Label nameLabel, qualifyingResult, raceResult, qlfResultPositions, raceResultPositions;
    @FXML
    TextField yearTF, roundTF;
    @FXML
    GridPane resultsPane;
    String greenColor="#bbffbb";
    String redColor="#ffbbbb";


    public void initialize() {
        //populate position labels for race and qualifying
        populatePosLabels("Q", qlfResultPositions);
        populatePosLabels("P", raceResultPositions);
        nameLabel.setText(UserHolder.getUserName());
    }

    public void goToMainMenuEvent(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("startingScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void submitRaceWeekendQuery(ActionEvent event) throws SQLException, ClassNotFoundException {
        DBActions dba = new DBActions();
        ArrayList<ArrayList> raceWeekendResult = dba.showRaceWeekend(Integer.parseInt(yearTF.getText()), Integer.parseInt(roundTF.getText()));
        qualifyingResult.setText(String.valueOf(raceWeekendResult.get(0)).replace("[", "").replace("]", "").replace(",", "").replace(" ", ""));
        raceResult.setText(String.valueOf(raceWeekendResult.get(1)).replace("[", "").replace("]", "").replace(",", "").replace(" ", ""));
        //i need to parse every dname and pos change in order to colour it accordingly
        ArrayList<String> DNamesList = (ArrayList<String>) raceWeekendResult.get(2).get(0);
        ArrayList<Integer> posChangedList = (ArrayList<Integer>) raceWeekendResult.get(2).get(1);


        for (int i = 0; i < DNamesList.size() && i < posChangedList.size(); i++) {
            if(posChangedList.get(i)>0){
                resultsPane.add(new Label(DNamesList.get(i)), 0, i);
                resultsPane.add(new Label(posChangedList.get(i).toString()), 1, i);
                resultsPane.getChildren().get(i*2).setStyle("-fx-background-color: "+redColor);
                resultsPane.getChildren().get(i*2+1).setStyle("-fx-background-color: "+redColor);
            }
            else if(posChangedList.get(i)==0) {
                resultsPane.add(new Label(DNamesList.get(i)), 0, i);
                resultsPane.add(new Label(posChangedList.get(i).toString()), 1, i);

            } else{
                resultsPane.add(new Label(DNamesList.get(i)), 0, i);
                resultsPane.add(new Label(posChangedList.get(i).toString()), 1, i);
                resultsPane.getChildren().get(i*2).setStyle("-fx-background-color: "+greenColor);
                resultsPane.getChildren().get(i*2+1).setStyle("-fx-background-color: "+greenColor);
            }

        }

    }

    private static void populatePosLabels(String s, Label label){
        //label.setVisible(true);
        for (int i=1;i<=20;i++){
            label.setText(label.getText()+s+i+" \n");
        }
    }
}
