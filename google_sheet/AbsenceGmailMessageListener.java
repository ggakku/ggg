package com.example.attendance_project.google_sheet;

public class AbsenceGmailMessageListener {
    private final String email;
    public AbsenceGmailMessageListener(String email){
        this.email = email;
    }
    public void update(){
        System.out.println("Info updated");
    }
}
