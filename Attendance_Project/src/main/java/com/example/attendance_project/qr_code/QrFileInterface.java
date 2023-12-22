package com.example.attendance_project.qr_code;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public interface QrFileInterface {
    FileInputStream getQrCodeImage(String subject) throws FileNotFoundException;
}