package com.example.animetrackingapp;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Api {

    public static void getJSON(String url, final ReadDataHandler readDataHandler){
        AsyncTask<String,Void,String> task = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String response = "";

                try{
                    URL link = new URL(strings[0]);
                    HttpURLConnection connection = (HttpURLConnection) link.openConnection();

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String row;
                    while((row = bufferedReader.readLine()) != null ){
                        response += row + "\n";
                    }
                    bufferedReader.close();
                    connection.disconnect();
                }
                catch(Exception e){

                }
                return response;
            }

            @Override
            protected void onPostExecute(String response) {
                readDataHandler.setJson(response);
                readDataHandler.sendEmptyMessage(0);
            }
        };
        task.execute(url);
    }
}
