package org.knee.nonopoly;

import org.knee.nonopoly.entities.spielerStrategien.AllesKaeufer;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * Created by Nils on 09.09.2016.
 */
public class Main {

    Schiedsrichter schiedsrichter;

    public Main() {
        schiedsrichter = new Schiedsrichter();
    }

    public static void  main(String[] args){
        new Main().runGame();
    }

    /**
     * Lässt den Schiedsrichter (noch) nur die richtigen Dinge tun
     */
    public void runGame(){
        schiedsrichter.registriereSpieler("Spieler1", new AllesKaeufer());
//        schiedsrichter.registriereSpieler("Spieler2", new RestBudgetBetrachter());
//        schiedsrichter.registriereSpieler("Spieler3", new AllesKaeufer());
//        schiedsrichter.registriereSpieler("Spieler4", new AllesKaeufer());
//        schiedsrichter.registriereSpieler("Spieler5", new AllesKaeufer());
//        schiedsrichter.registriereSpieler("Spieler6", new AllesKaeufer());
        schiedsrichter.spielStarten();
        schiedsrichter.spieleSpielZuEnde();

    }
}
