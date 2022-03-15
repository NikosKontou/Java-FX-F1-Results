package com.example.cwrk_v2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class startingSceneController {
    @FXML
    Label nameLabel;

    public void displayName (String username){
        nameLabel.setText("Hello "+username);
    }
    public void goToMainMenuEvent(){


    }

}
