package com.example.attendance_project.qr_code;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

// Adapter class
public class QrCodeAdapter implements QrFileInterface {
    public String subject_name;
    private QrCodeGenerator qrCodeGenerator = QrCodeGenerator.getInstance();

    @Override
    public FileInputStream getQrCodeImage(String subject) throws FileNotFoundException {
        subject_name = subject;
        return new FileInputStream(qrCodeGenerator.getQrCode(subject));
    }
}
