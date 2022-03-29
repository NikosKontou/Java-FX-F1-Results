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
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ShowPointsController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    Label queryResult, queryResultPoints;
    @FXML
    Button submitQuery;


    public void goToMainMenuEvent(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("startingScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void submitQueryEvent(ActionEvent event) throws SQLException, ClassNotFoundException {
        DBActions dba = new DBActions();
        try {
            //provoli olwn ton apotelesmatwn enos sigkekrimenou etous
            String [] result= new String[2];
            //save the function result in order to run it only once
            result=dba.showDriversPerYear(2021);

           queryResult.setText(result[0]);
           queryResultPoints.setText(result[1]);
        }catch (NullPointerException e){
            System.out.println("Null pointer exception at submitquery event \n"+e);
        }
    }

}
