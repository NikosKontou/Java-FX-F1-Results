package com.example.cwrk_v2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

import java.io.IOException;

public class main extends Application {
    String userName;
    @Override
    public void start(Stage stage) throws IOException {

        //fortwnei kai provali to arxiko log in scene
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("loginScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("log in to the application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}