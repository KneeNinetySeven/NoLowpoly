package org.knee.nonopoly.logik.logging;

import org.knee.nonopoly.entities.Spieler;

import java.util.ArrayList;

/**
 * Created by Nils on 24.09.2016.
 * <p>
 * Der Protokollant verwaltet die Konsolenausgaben und Logs
 */

public class Protokollant {

    private StringBuilder logOutput;
    private int count;

    public Protokollant() {
        this.logOutput = new StringBuilder();
        count = 0;
    }

    /**
     * Fügt einen String in die Logdatei an
     *
     * @param s String, der ins Log angefügt werden soll
     */
    public void addToLog(String s) {
        this.logOutput.append("\n");
        this.logOutput.append(s);
    }

    /**
     * @return Gibt das derzeitige Log zurück
     */
    public String getLogOutput() {
        return this.logOutput.toString();
    }

    /**
     * Gibt eine Liste der Spieler auf der Konsole aus
     */
    public void printSpieler(ArrayList<Spieler> spielerListe) {
        System.out.println("--- SPIELER ---");
        spielerListe.forEach(System.out::println);
    }

    /**
     * @param s String, der als Konsolenlog ausgegeben werden soll
     */
    public void printAs(String s) {
        System.out.printf("[%5s.%14s] %30s \n", new String[]{String.valueOf(count), "SCHIEDSRICHTER", s});
        count++;
    }


}
