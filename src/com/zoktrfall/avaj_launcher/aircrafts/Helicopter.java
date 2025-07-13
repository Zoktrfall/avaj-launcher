package com.zoktrfall.avaj_launcher.aircrafts;

import com.zoktrfall.avaj_launcher.Coordinates;
import com.zoktrfall.avaj_launcher.Logger;

public class Helicopter extends Aircraft {
    public Helicopter(long id, String name, Coordinates coordinates) {
        super(id, name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(this.coordinates);

        switch (weather) {
            case "SUN":
                coordinates.setLongitude(coordinates.getLongitude() + 10);
                coordinates.setHeight(coordinates.getHeight() + 2);
                Logger.log(this.getLogPrefix() + ": This is hot.");
                break;
            case "RAIN":
                coordinates.setLongitude(coordinates.getLongitude() + 5);
                Logger.log(this.getLogPrefix() + ": Damn rain, the blades are slipping!");
                break;
            case "FOG":
                coordinates.setLongitude(coordinates.getLongitude() + 1);
                Logger.log(this.getLogPrefix() + ": My rotor is going to freeze!");
                break;
            case "SNOW":
                coordinates.setHeight(coordinates.getHeight() - 12);
                Logger.log(this.getLogPrefix() + ": Can someone turn the fog lights on?");
                break;
        }

        HandleLanding();
    }
}
