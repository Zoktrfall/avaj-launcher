package com.zoktrfall.avaj_launcher;

public class WeatherProvider {
    private static WeatherProvider instance = null;
    private final String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() {}

    public static WeatherProvider getInstance() {
        if (instance == null) {
            instance = new WeatherProvider();
        }

        return instance;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int hash = (coordinates.getLatitude() << 16) ^ (coordinates.getLongitude() << 8) ^ coordinates.getHeight();
        int index = Math.abs(hash) % weather.length;

        return weather[index];
    }
}
