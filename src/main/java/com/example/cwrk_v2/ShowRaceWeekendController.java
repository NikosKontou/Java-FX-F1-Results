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
import java.util.Objects;

public class ShowRaceWeekendController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    Label nameLabel, qualifyingResult, raceResult, qlfResultPositions, raceResultPositions, warningLabel;
    @FXML
    TextField yearTF, roundTF;
    @FXML
    GridPane resultsPane;
    String greenColor = "#bbffbb";
    String redColor = "#ffbbbb";


    public void initialize() {
        //populate position labels for race and qualifying
        populatePosLabels("Q", qlfResultPositions);
        populatePosLabels("P", raceResultPositions);
        nameLabel.setText(UserHolder.getUserName());
    }

    public void goToMainMenuEvent(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("startingScene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void submitRaceWeekendQuery(ActionEvent event) throws SQLException, ClassNotFoundException {
        DBActions dba = new DBActions();
        //check for valid inputs
        if (CheckInputs.checkYear(yearTF.getText()) && CheckInputs.checkRound(roundTF.getText())) {
            warningLabel.setVisible(false);

            ArrayList<ArrayList> raceWeekendResult = dba.showRaceWeekend(Integer.parseInt(yearTF.getText()), Integer.parseInt(roundTF.getText()));
            //make the Arraylists appear pretier on the labels
            qualifyingResult.setText(String.valueOf(raceWeekendResult.get(0)).replace("[", "").replace("]", "").replace(",", "").replace(" ", ""));
            //depending on the contents display the position labels
            if (String.valueOf(raceWeekendResult.get(1)).contains("No-data")) {
                raceResultPositions.setVisible(false);
            } else {
                raceResultPositions.setVisible(true);
            }
            if (String.valueOf(raceWeekendResult.get(0)).contains("No-data")) {
                qlfResultPositions.setVisible(false);
            } else {
                qlfResultPositions.setVisible(true);
            }
            raceResult.setText(String.valueOf(raceWeekendResult.get(1)).replace("[", "").replace("]", "").replace(",", "").replace(" ", ""));
            //i need to parse every dname and pos change in order to colour it accordingly
            ArrayList<String> DNamesList = (ArrayList<String>) raceWeekendResult.get(2).get(0);
            ArrayList<Integer> posChangedList = (ArrayList<Integer>) raceWeekendResult.get(2).get(1);
            //clear every field in resultsPane to overwrite the cells
            resultsPane.getChildren().clear();

            for (int i = 0; i < DNamesList.size() && i < posChangedList.size(); i++) {
                //coditional formating for color
                if (posChangedList.get(i) > 0) {
                    resultsPane.add(new Label(DNamesList.get(i)), 0, i);
                    resultsPane.add(new Label(posChangedList.get(i).toString()), 1, i);
                    resultsPane.getChildren().get(i * 2).setStyle("-fx-background-color: " + redColor);
                    resultsPane.getChildren().get(i * 2 + 1).setStyle("-fx-background-color: " + redColor);
                } else if (posChangedList.get(i) == 0) {
                    resultsPane.add(new Label(DNamesList.get(i)), 0, i);
                    resultsPane.add(new Label(posChangedList.get(i).toString()), 1, i);

                } else {
                    resultsPane.add(new Label(DNamesList.get(i)), 0, i);
                    resultsPane.add(new Label(posChangedList.get(i).toString()), 1, i);
                    resultsPane.getChildren().get(i * 2).setStyle("-fx-background-color: " + greenColor);
                    resultsPane.getChildren().get(i * 2 + 1).setStyle("-fx-background-color: " + greenColor);
                }

            }
        } else {
            //in case of invalid input, show error message
            warningLabel.setVisible(true);
            warningLabel.setTextFill(Color.RED);
            warningLabel.setText("Fill every field with valid data");
        }


    }

    private static void populatePosLabels(String s, Label label) {
        //label.setVisible(true);
        for (int i = 1; i <= 20; i++) {
            label.setText(label.getText() + s + i + " \n");
        }
    }
}
