package com.zoktrfall.avaj_launcher.aircrafts;

import com.zoktrfall.avaj_launcher.Coordinates;
import com.zoktrfall.avaj_launcher.Logger;

public class Balloon extends Aircraft {
    public Balloon(long id, String name, Coordinates coordinates) {
        super(id, name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(this.coordinates);

        switch (weather) {
            case "SUN":
                coordinates.setLongitude(coordinates.getLongitude() + 2);
                coordinates.setHeight(coordinates.getHeight() + 4);
                Logger.log(this.getLogPrefix() + ": Let's enjoy the good weather and take some pics.");
                break;
            case "RAIN":
                coordinates.setHeight(coordinates.getHeight() - 5);
                Logger.log(this.getLogPrefix() + ": Damn you rain! You messed up my balloon.");
                break;
            case "FOG":
                coordinates.setHeight(coordinates.getHeight() - 3);
                Logger.log(this.getLogPrefix() + ": I can’t see anything through this fog!");
                break;
            case "SNOW":
                coordinates.setHeight(coordinates.getHeight() - 15);
                Logger.log(this.getLogPrefix() + ": It's snowing. We're gonna crash.");
                break;
        }

        handleLanding();
    }
}
