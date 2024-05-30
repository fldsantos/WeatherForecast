package org.example;

import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        WeatherChecker wc = new WeatherChecker();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        String response = "";

        while(!input.trim().equals("quit")) {
            //bad solution to clear the console
            for (int i = 0; i <= 30; i++){
                System.out.println("");
            }

            System.out.print("Go ahead and write a place (or type 'quit' to exit): ");
            input = in.readLine();

            if(input.trim().equals("quit")) {
                System.out.println("bye bye!");
                Thread.sleep(1500);
            } else {
                response = wc.GetWeatherDetails(input);
                JSONObject json = new JSONObject(response);
                JSONObject location = new JSONObject(json.get("location").toString());
                JSONObject current = new JSONObject(json.get("current").toString());
                JSONObject condition = new JSONObject(current.get("condition").toString());

                String[] information = {
                        location.get("localtime").toString(),
                        location.get("country").toString(),
                        location.get("name").toString(),
                        current.get("temp_f").toString(),
                        condition.get("text").toString(),
                };

                System.out.println(String.format("Date and time: %s", information[0]));
                System.out.println(String.format("Location: %s/%s", information[1], information[2]));
                System.out.println(String.format("Temperature: %s", information[3]));
                System.out.println(String.format("Condition: %s", information[4]));

                System.out.println("");

                System.out.println("Would you like to save this report? (Y/N)");
                input = in.readLine();

                if(input.trim().toLowerCase().equals("y")) {

                } else {
                    System.out.println("Redirecting to main page...");
                    Thread.sleep(1000);
                }
            }
        }
    }
}