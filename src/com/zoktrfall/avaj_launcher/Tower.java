package com.zoktrfall.avaj_launcher;

import com.zoktrfall.avaj_launcher.aircrafts.Flyable;
import com.zoktrfall.avaj_launcher.exceptions.DuplicateAircraftIdException;

import java.util.ArrayList;
import java.util.List;

public class Tower {
    private final List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) throws DuplicateAircraftIdException {
        boolean exists = observers.stream()
                .anyMatch(existing -> existing.getId() == flyable.getId());

        if (!exists) {
            observers.add(flyable);
            Logger.log("Tower says: " + flyable.getLogPrefix() + " registered to weather tower.");
        }
        else {
            throw new DuplicateAircraftIdException("Aircraft with ID " + flyable.getId() + " is already registered.");
        }
    }

    public void unregister(Flyable flyable) {
        if (observers.contains(flyable)) {
            observers.remove(flyable);
            Logger.log("Tower says: " + flyable.getLogPrefix() + " unregistered from weather tower.");
        }
    }

    protected void conditionsChanged() {
        List<Flyable> currentObservers = new ArrayList<>(observers);
        for (Flyable flyable : currentObservers) {
            flyable.updateConditions();
        }
    }
}
