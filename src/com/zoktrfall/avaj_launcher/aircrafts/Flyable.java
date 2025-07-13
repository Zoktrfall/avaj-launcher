package com.zoktrfall.avaj_launcher.aircrafts;

import com.zoktrfall.avaj_launcher.WeatherTower;

public abstract class Flyable {
    protected WeatherTower weatherTower;

    public abstract void updateConditions();

    public abstract String getLogPrefix();
    public abstract long getId();

    public void registerTower(WeatherTower p_tower) {
        this.weatherTower = p_tower;
    }
}
