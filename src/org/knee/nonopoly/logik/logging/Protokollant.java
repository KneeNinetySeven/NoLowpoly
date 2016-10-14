package org.knee.nonopoly.logik.logging;

import org.knee.nonopoly.entities.Spieler;

import java.util.ArrayList;

/**
 * Created by Nils on 24.09.2016.
 */

public class Protokollant {

    private StringBuilder logOutput;
    private int count;

    public Protokollant() {
        this.logOutput = new StringBuilder();
        count = 0;
    }

    public void addToLog(String s){
        this.logOutput.append("\n");
        this.logOutput.append(s);
    }

    public String getLogOutput(){
        return this.logOutput.toString();
    }

    public void printSpieler(ArrayList<Spieler> spielerListe){
        System.out.println("--- SPIELER ---");
        spielerListe.forEach(System.out::println);
    }

    public void printAs(String s){
        System.out.println("[ SCHIEDSRICHTER ]::"+count+"\t" + s);
        count++;
    }



}
