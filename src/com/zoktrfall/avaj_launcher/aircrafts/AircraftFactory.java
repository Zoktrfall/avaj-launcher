package com.zoktrfall.avaj_launcher.aircrafts;

import com.zoktrfall.avaj_launcher.*;
import com.zoktrfall.avaj_launcher.exceptions.InvalidAircraftTypeException;

public class AircraftFactory {
    private static AircraftFactory instance = null;

    private AircraftFactory() {}

    public static AircraftFactory getInstance() {
        if (instance == null) {
            instance = new AircraftFactory();
        }

        return instance;
    }

    public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates)
            throws InvalidAircraftTypeException {
        long id = IdGenerator.nextId();

        return switch (p_type.toUpperCase()) {
            case "BALLOON" -> new Balloon(id, p_name, p_coordinates);
            case "HELICOPTER" -> new Helicopter(id, p_name, p_coordinates);
            case "JETPLANE" -> new JetPlane(id, p_name, p_coordinates);
            default -> throw new InvalidAircraftTypeException("Unknown aircraft type: " + p_type);
        };
    }
}
