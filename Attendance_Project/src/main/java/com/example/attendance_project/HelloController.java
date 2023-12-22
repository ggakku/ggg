package com.example.attendance_project;

import com.example.attendance_project.google_sheet.GetData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    GetData getData = GetData.getInstance();

    @FXML
    public void changeSceneToQrChoices(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("qr_choices.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void changeSceneToRetaker(ActionEvent event) throws IOException{
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Retaker retaker = new Retaker();
        retaker.setRetakers(getData.getRetaker());
        retaker.start(stage);
    }

}