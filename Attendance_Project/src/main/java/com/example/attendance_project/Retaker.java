package com.example.attendance_project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class Retaker extends Application {

    HashMap<String, Integer> retakers;

    public void setRetakers(HashMap<String, Integer> map){
        this.retakers = map;
    }

    @Override
    public void start(Stage primaryStage){

        // Create a VBox to hold the labels
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);

        // Create labels based on the input data
        for (String labelText : retakers.keySet()) {
            Label label = new Label(labelText + " - " + retakers.get(labelText));
            root.getChildren().add(label);
        }
        Button button = new Button("Back");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setScene(scene);
                stage.show();
            }
        });
        root.getChildren().add(button);

        // Create the scene and set it on the stage
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

