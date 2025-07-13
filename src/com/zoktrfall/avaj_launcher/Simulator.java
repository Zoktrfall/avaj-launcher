package com.zoktrfall.avaj_launcher;

import com.zoktrfall.avaj_launcher.aircrafts.AircraftFactory;
import com.zoktrfall.avaj_launcher.aircrafts.Flyable;
import com.zoktrfall.avaj_launcher.exceptions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Simulator {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java -jar avaj_launcher.jar <scenario_file>");
            return;
        }

        var fileName = args[0];
        File scenarioFile = new File(fileName);

        if (!scenarioFile.exists()) {
            System.out.println("Scenario file '" + fileName + "' does not exist.");
            return;
        }

        try {
            Logger.init("simulation.txt");
            List<Flyable> aircrafts = new ArrayList<>();
            WeatherTower weatherTower = new WeatherTower();

            int simulationCount = ProcessSimulation(fileName, aircrafts, weatherTower);

            for (int i = 0; i < simulationCount; i++) {
                weatherTower.changeWeather();
            }

        } catch (IOException e) {
            System.out.println("Logger or file error: " + e.getMessage());
        } catch (SimulationException | NumberFormatException e) {
            System.out.println("Simulation error: " + e.getMessage());
        } finally {
            Logger.close();
        }
    }

    private static int ProcessSimulation(String fileName, List<Flyable> aircrafts, WeatherTower weatherTower)
            throws IOException, SimulationException, NumberFormatException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;
            int simulationCount = 0;

            AircraftFactory aircraftFactory = AircraftFactory.getInstance();

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (lineNumber == 0) {
                    simulationCount = Integer.parseInt(line);
                    if (simulationCount <= 0) {
                        throw new SimulationException("Simulation count must be positive.");
                    }
                } else {
                    String[] aircraftData = line.split("\\s+");
                    if (aircraftData.length != 5) {
                        throw new SimulationException("Invalid aircraft format at line " + (lineNumber + 1));
                    }

                    var aircraft = aircraftFactory.newAircraft(
                            aircraftData[0],
                            aircraftData[1],
                            new Coordinates(
                                    Integer.parseInt(aircraftData[2]),
                                    Integer.parseInt(aircraftData[3]),
                                    Integer.parseInt(aircraftData[4])
                            )
                    );

                    aircraft.registerTower(weatherTower);
                    weatherTower.register(aircraft);
                    aircrafts.add(aircraft);
                }

                lineNumber++;
            }

            return simulationCount;
        }
    }
}