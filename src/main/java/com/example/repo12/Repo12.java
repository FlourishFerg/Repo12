package com.example.repo12;

public class Repo12 {
    import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

    // Class representing the Weather Forecasting Application
    public class WeatherForecastApp {
        private static final String API_KEY = "YOUR_API_KEY"; // Replace with your OpenWeatherMap API key

        public static void main(String[] args) {
            String city = "New York"; // Example city
            try {
                JSONObject json = getWeatherData(city);
                if (json != null) {
                    displayCurrentWeather(json);
                } else {
                    System.out.println("Failed to retrieve weather data for " + city);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Method to retrieve weather data from OpenWeatherMap API
        private static JSONObject getWeatherData(String city) throws Exception {
            String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY;
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            return new JSONObject(response.toString());
        }

        // Method to display current weather conditions
        private static void displayCurrentWeather(JSONObject json) {
            double temperature = json.getJSONObject("main").getDouble("temp");
            double feelsLike = json.getJSONObject("main").getDouble("feels_like");
            int humidity = json.getJSONObject("main").getInt("humidity");
            String description = json.getJSONArray("weather").getJSONObject(0).getString("description");
            System.out.println("Current Weather:");
            System.out.println("Temperature: " + (temperature - 273.15) + "°C");
            System.out.println("Feels Like: " + (feelsLike - 273.15) + "°C");
            System.out.println("Humidity: " + humidity + "%");
            System.out.println("Description: " + description);
        }
    }

}
