package com.example.attendance_project;

import com.example.attendance_project.google_sheet.GetData;
import com.example.attendance_project.qr_code.QrCodeAdapter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class QrImagesController {
    QrCodeAdapter qrCodeAdapter = new QrCodeAdapter();
    GetData getData = GetData.getInstance();

    @FXML
    ImageView qr_image = new ImageView();
    Button back;


    public void setQr_image(String subject_name) throws FileNotFoundException {
        Image image = new Image(qrCodeAdapter.getQrCodeImage(subject_name));
        this.qr_image.setImage(image);
    }

    public void backButtonAction(ActionEvent event) throws IOException {
        getData.sendAbsenseMessage(qrCodeAdapter.subject_name);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

}
