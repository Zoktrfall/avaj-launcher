package com.zoktrfall.avaj_launcher.aircrafts;

import com.zoktrfall.avaj_launcher.Coordinates;
import com.zoktrfall.avaj_launcher.Logger;

public abstract class Aircraft extends Flyable {
    protected long id;
    protected String name;
    protected Coordinates coordinates;

    public String getLogPrefix() {
        return this.getClass().getSimpleName() + "#" + name + "(" + id + ")";
    }
    public long getId() {
        return id;
    }

    protected Aircraft(long id, String name, Coordinates coordinates) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
    }

    protected void handleLanding() {
        if (coordinates.getHeight() <= 0) {
            Logger.log(getLogPrefix() + ": landing.");
            if (weatherTower != null) {
                weatherTower.unregister(this);
            }
        }
    }
}
