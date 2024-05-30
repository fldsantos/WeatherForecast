package org.example;
import java.io.*;
import java.net.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherChecker {
    private String key = "7a1f3f34a0d74681856171205242905";

    public String GetWeatherDetails(String location) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("http://api.weatherapi.com/v1/current.json?key=%s&q=%s", this.key, location)))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
