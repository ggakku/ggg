package com.example.attendance_project.google_sheet;

import java.util.ArrayList;
import java.util.List;

public class GmailService {
    private static List<AbsenceGmailMessageListener> gmails = new ArrayList<>();

    public void setGmails(List<String> g){
        for(String i: g){
            gmails.add(new AbsenceGmailMessageListener(i));
        }
        for (int i = 0; i < gmails.size(); i++) {
            gmails.get(i).update();
        }
        System.out.println("Messages Send !!!");
    }
}
