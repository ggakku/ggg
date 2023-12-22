module com.example.attendance_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.zxing;
    requires com.google.zxing.javase;
    requires com.fasterxml.jackson.databind;


    opens com.example.attendance_project to javafx.fxml;
    exports com.example.attendance_project;
}