package com.zoktrfall.avaj_launcher.aircrafts;

import com.zoktrfall.avaj_launcher.Coordinates;
import com.zoktrfall.avaj_launcher.Logger;

public class JetPlane extends Aircraft {
    public JetPlane(long id, String name, Coordinates coordinates) {
        super(id, name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(this.coordinates);

        switch (weather) {
            case "SUN":
                coordinates.setLatitude(coordinates.getLatitude() + 10);
                coordinates.setHeight(coordinates.getHeight() + 2);
                Logger.log(this.getLogPrefix() + ": Perfect day to fly fast and high!");
                break;
            case "RAIN":
                coordinates.setLatitude(coordinates.getLatitude() + 5);
                Logger.log(this.getLogPrefix() + ": It's raining. Better watch out for lightings.");
                break;
            case "FOG":
                coordinates.setLatitude(coordinates.getLatitude() + 1);
                Logger.log(this.getLogPrefix() + ": Flying blind in the fog, hope for the best.");
                break;
            case "SNOW":
                coordinates.setHeight(coordinates.getHeight() - 7);
                Logger.log(this.getLogPrefix() + ": OMG! Winter is coming!");
                break;
        }

        handleLanding();
    }
}
