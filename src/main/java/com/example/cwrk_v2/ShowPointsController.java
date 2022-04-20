package com.example.cwrk_v2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class ShowPointsController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    Label queryResult, queryResultPoints, errorMessage, nameLabel;
    @FXML
    TextField yearField;
    @FXML
    Button submitQuery;

    public void initialize() {
        nameLabel.setText(UserHolder.getUserName());
    }

    public void goToMainMenuEvent(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("startingScene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void submitQueryEvent(ActionEvent event) throws SQLException, ClassNotFoundException {
        DBActions dba = new DBActions();
        try {
            //show every result of a specific year
            String[] result = new String[2];
            int date;
            try {
                //save the user input in an integer
                date = Integer.parseInt(yearField.getText());

                if (!CheckInputs.checkYear(String.valueOf(date))) {
                    errorMessage.setTextFill(Color.rgb(230, 50, 50));
                    errorMessage.setText("You must enter a valid year (e.g. 2021)");
                } else {
                    errorMessage.setText("");
                    //save the function result in order to run it only once
                    result = dba.showDriversPerYear(date);
                    //show the names of the drivers
                    queryResult.setText(result[0]);
                    //show the points of the drivers
                    queryResultPoints.setText(result[1]);
                }
            } catch (NumberFormatException e) {
                errorMessage.setTextFill(Color.rgb(230, 50, 50));
                errorMessage.setText("You must enter only numbers");
            }


        } catch (NullPointerException e) {
            System.out.println("Null pointer exception at submitquery event \n" + e);
        }
    }

}
