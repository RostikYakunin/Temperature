package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Button;

    @FXML
    private Text feeling;

    @FXML
    private Text max;

    @FXML
    private Text min;

    @FXML
    private TextField nameOfCity;

    @FXML
    private Text nameoftable;

    @FXML
    private Text pressure;

    @FXML
    private AnchorPane table;

    @FXML
    private Text temperature;

    @FXML
    void initialize() {
        Button.setOnAction(
                event -> {
                    getStart(nameOfCity.getText());
                    System.out.println("OK");
                }
        );
    }

    void getStart(String name) {

        if (name != null) {
            String json = null;
            try {
                json = getWeather(name);
            } catch (IOException e) {
                nameOfCity.setText("Данного города не найдено !");
            }
            JSONObject obj = new JSONObject(json);

            temperature.setText("Температура: " + obj.getJSONObject("main").getDouble("temp") + '\u2103');
            max.setText("Максимум: " + obj.getJSONObject("main").getDouble("temp_max") + '\u2103');
            min.setText("Минимум: " + obj.getJSONObject("main").getDouble("temp_min") + '\u2103');
            feeling.setText("Ощущается: " + obj.getJSONObject("main").getDouble("feels_like") + '\u2103');
            pressure.setText("Давление: " + obj.getJSONObject("main").getDouble("pressure"));
        }
    }

    String getWeather(String name) throws IOException {
        URL url = new URL(
                "https://api.openweathermap.org/data/2.5/weather?q=" + name + "&appid=fd3472abf94df529d9b8cb485d137192&units=metric");
        URLConnection connection = url.openConnection();

        BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder st = new StringBuilder();
        String a = "";

        while ((a = bf.readLine()) != null) {
            st.append(a);
        }

        bf.close();
        return st.toString();
    }

}