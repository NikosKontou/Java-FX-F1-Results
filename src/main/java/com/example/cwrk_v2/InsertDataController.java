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

public class InsertDataController {
    @FXML
    Label nameLabel;
    @FXML
    TextField driverNumberTF;
    @FXML
    TextField driverNameTF;
    @FXML
    Button insertDriverButton;
    @FXML
    Label feedbackLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initialize(){
        nameLabel.setText(UserHolder.getUserName());
    }

    public void insertDriver(ActionEvent event) throws SQLException, ClassNotFoundException {
        DBActions dba = new DBActions();
        if(CheckInputs.checkDriverNumber(driverNumberTF.getText())){
            if(dba.insertDriver(Integer.parseInt(driverNumberTF.getText()), driverNameTF.getText())){
                feedbackLabel.setTextFill(Color.rgb(30,250,80));
                feedbackLabel.setText("New Driver was successfully created");
            }else   {
                feedbackLabel.setTextFill(Color.rgb(250,80,30));
                feedbackLabel.setText("a Driver with this number already exists");
            }
        }else{
            feedbackLabel.setVisible(true);
            feedbackLabel.setTextFill(Color.rgb(250,80,30));
            feedbackLabel.setText("Invalid input");

        }

    }

    //to back button fortwnei diaforetiko scene sto stage
    public void goToMainMenuEvent (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("startingScene.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
