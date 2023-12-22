package com.example.attendance_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class QrController {
    @FXML
    Button button1;
    @FXML
    Button button2;


    public void changeScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("qr_images.fxml"));
        Parent root = fxmlLoader.load();
        String buttonName = ((Button) event.getSource()).getText();

        System.out.println(buttonName);

        QrImagesController qrImagesController = fxmlLoader.getController();
        qrImagesController.setQr_image(buttonName);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
