package org.knee.nonopoly.logik.logging;

import org.knee.nonopoly.entities.SpielerFabrik;

import java.util.ArrayList;

/**
 * Created by Nils on 24.09.2016.
 */

public class ProtokollantFabrik {

    private StringBuilder logOutput;

    public ProtokollantFabrik() {
        this.logOutput = new StringBuilder();
    }

    public void addToLog(String s){
        this.logOutput.append("\n");
        this.logOutput.append(s);
    }

    public String getLogOutput(){
        return this.logOutput.toString();
    }

    public void printSpieler(ArrayList<SpielerFabrik> spielerListe){
        System.out.println("--- SPIELER ---");
        for(SpielerFabrik s : spielerListe){
            System.out.println(s.toString());
        }
    }

}
