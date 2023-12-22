package com.example.attendance_project.google_sheet;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetData {

    private static GetData instance;
    private GetData(){}

    public static GetData getInstance(){
        if(instance == null){
            instance = new GetData();
        }
        return instance;
    }
    private List<Integer> last_absence = new ArrayList<>();
    private List<List<String>> data;

    public void sendAbsenseMessage(String subject_name) throws IOException{
        GmailService service = new GmailService();
        service.setGmails(getDataGoogleSheets(subject_name));
    }

    public HashMap<String, Integer> getRetaker(){
        int overall = data.size() - 2;
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < last_absence.size(); i++){
            System.out.println(Math.floor(overall / 2.0) + " " + last_absence.get(i));
            if(Math.floor(overall / 2.0) <= last_absence.get(i)){
                map.put(data.get(i).get(0), last_absence.get(i));
            }
        }
        System.out.println("Last absense: " + last_absence + " " + data.size());
        System.out.println("Retakers: " + map);
        return map;
    }


//  Method to get Data and check attendance
    public List<String> getDataGoogleSheets(String subject_name) throws IOException {
        // The URL of the JSON file you want to retrieve
        String url = "http://127.0.0.1:5000/" + subject_name;

        // Create a URL object from the string URL
        URL obj = new URL(url);

        // Open a connection to the URL
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Set the request method to GET
        con.setRequestMethod("GET");

        // Set the content type of the request to indicate that we expect JSON in response
        con.setRequestProperty("Content-Type", "application/json");

        // Get the response code
        int responseCode = con.getResponseCode();

        // Read the response from the server
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
            System.out.println("The Data Get: " + inputLine);
            System.out.println();
        }
        in.close();

        ObjectMapper objectMapper = new ObjectMapper();
        List<List<String>> data = objectMapper.readValue(response.toString(), List.class);

        List<Integer> absence = new ArrayList<>();

        for(int i = 0; i < data.size(); i++){
            int sum = 0;
            for(int j = 2; j < data.get(i).size(); j++){
                if(data.get(i).get(j).equals("0")){
                    sum++;
                }
            }
            absence.add(sum);
        }
        if(last_absence.isEmpty()){
            setLastAbsence(absence);
        }
        setData(data);

        return checkAttendance(absence);


    }

    private void setLastAbsence(List<Integer> absence){
        last_absence = List.copyOf(absence);
    }

    public List<List<String>> getData(){
        return data;
    }

    private void setData(List<List<String>> d){
        data = d;
    }

    public List<String> checkAttendance(List<Integer> absence){
        List<String> gmailsChanged = new ArrayList<>();
        if(last_absence.size() > 0){
            for(int i = 0; i < absence.size(); i++){
                if(!last_absence.get(i).equals(absence.get(i))){
                    gmailsChanged.add(data.get(i).get(1));
                }
            }
        }
        System.out.println("Absence: " + last_absence);
        setLastAbsence(absence);
        return List.copyOf(gmailsChanged);
    }

//    public static void main(String[] args) throws IOException{
//        GetData g = new GetData();
//        g.getDataGoogleSheets();
//    }
}
