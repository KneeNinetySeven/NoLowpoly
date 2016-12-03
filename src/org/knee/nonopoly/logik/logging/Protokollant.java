package org.knee.nonopoly.logik.logging;

import org.knee.nonopoly.entities.Spieler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Created by Nils on 24.09.2016.
 * <p>
 * Der Protokollant verwaltet die Konsolenausgaben und Logs
 */

public class Protokollant {

    private static boolean writeLogToFile = false;
    private static StringBuilder logOutput = new StringBuilder();
    private static int count = 1;
    private static File f = new File(System.currentTimeMillis() + ".log");

    public Protokollant() {
        logOutput = new StringBuilder();
        count = 0;
    }

    /**
     * Fügt einen String in die Logdatei an
     *
     * @param s String, der ins Log angefügt werden soll
     */
    public void addToLog(String s) {
        logOutput.append("\n");
        logOutput.append(s);
    }

    /**
     * @return Gibt das derzeitige Log zurück
     */
    public String getLogOutput() {
        return logOutput.toString();
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
    public static void printAs(Object o, String s) {
        String format = "[ %14s ] ::%6s - %s \n";
        String[] splittedObjectPath = o.getClass().getName().split("\\.");
        String className = splittedObjectPath[splittedObjectPath.length - 1];
        Object[] msg = {className, count, s};
        System.out.printf(format, msg);
        logOutput.append(String.format(format, msg));
        writeLogToFile();
        count++;
    }

    /**
     * @param s String, der als Konsolenlog ausgegeben werden soll
     */
    public static void printAs(boolean linebreak, Object o, String s) {
        String format;
        if(linebreak){
            format = "[ %14s ] ::%6s - %s \n";
        }else {
            format = "[ %14s ] ::%6s - %s";
        }
        String[] splittedObjectPath = o.getClass().getName().split("\\.");
        String className = splittedObjectPath[splittedObjectPath.length - 1];
        Object[] msg = {className, count, s};
        System.out.printf(format, msg);
        logOutput.append(String.format(format, msg));
        writeLogToFile();
        count++;
    }

    /**
     * Wenn ein Logfile geschrieben werden soll, wird dieses Aktualisiert
     */
    private static void writeLogToFile(){
        if (writeLogToFile) {
            try {
                PrintStream printStream = new PrintStream(f);
                printStream.append(logOutput.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
