package com.example.attendance_project.qr_code;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.nio.file.Paths;
import java.util.HashMap;

public class QrCodeGenerator {
    private static QrCodeGenerator instance;
    HashMap<String, String> map;

    private void generate(){
        try {
            String path = "src/main/java/com/example/attendance_project/qr_code/images/";
            for (String i : instance.map.keySet()) {
                BitMatrix matrix = new MultiFormatWriter().encode(instance.map.get(i), BarcodeFormat.QR_CODE, 500, 500);
                MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path + i + ".jpg"));
            }
        }catch (Exception e){
            System.out.println("Error in creating QRcode, " + e.getMessage());
        }
    }

    private QrCodeGenerator(){
        map = new HashMap<>();
        map.put("Math", "https://docs.google.com/spreadsheets/d/1WIWHjk_pJq96G1KI2bwrD405PV0Ohpt30YfX6J_PKkk/edit#gid=0");
        map.put("Physics", "https://docs.google.com/spreadsheets/d/1WIWHjk_pJq96G1KI2bwrD405PV0Ohpt30YfX6J_PKkk/edit#gid=501559272");
    }

    public static QrCodeGenerator getInstance(){
        if(instance == null){
            instance = new QrCodeGenerator();
            instance.generate();
        }
        return instance;
    }

    public String getQrCode(String subject){
        return "src/main/java/com/example/attendance_project/qr_code/images/" + subject + ".jpg";
    }

    public static void main(String[] args) {
        getInstance();
    }
}
