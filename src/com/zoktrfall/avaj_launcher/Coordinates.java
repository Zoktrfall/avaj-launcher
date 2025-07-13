package com.zoktrfall.avaj_launcher;

import com.zoktrfall.avaj_launcher.exceptions.InvalidCoordinatesException;
import com.zoktrfall.avaj_launcher.exceptions.InvalidHeightException;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    Coordinates(int longitude, int latitude, int height) throws InvalidHeightException, InvalidCoordinatesException {
        if (longitude < 0 || latitude < 0)
            throw new InvalidCoordinatesException("Coordinates must be positive!");

        if (height < 0)
            throw new InvalidHeightException("Height cannot be negative!");

        this.longitude = longitude;
        this.latitude = latitude;
        this.height = Math.min(height, 100);
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }

    public void setLongitude(int p_longitude) {
        this.longitude = p_longitude;
    }

    public void setLatitude(int p_latitude) {
        this.latitude = p_latitude;
    }

    public void setHeight(int p_height) {
        this.height = p_height;
    }

}
