package com.zoktrfall.avaj_launcher;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    private static PrintWriter writer;

    public static void init(String filename) throws IOException {
        writer = new PrintWriter(new FileWriter(filename));
    }

    public static void log(String message) {
        if (writer != null) {
            writer.println(message);
        }
    }

    public static void close() {
        if (writer != null) {
            writer.close();
        }
    }
}
