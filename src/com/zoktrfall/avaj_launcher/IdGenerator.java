package com.zoktrfall.avaj_launcher;

public class IdGenerator {
    private static long currentId = 1;

    public static long nextId() {
        return currentId++;
    }
}