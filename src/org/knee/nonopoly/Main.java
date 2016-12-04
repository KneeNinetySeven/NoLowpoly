package org.knee.nonopoly;

import org.knee.nonopoly.logik.Schiedsrichter;
import org.knee.nonopoly.ui.MainWindow;

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
        new MainWindow(schiedsrichter);
//        schiedsrichter.registriereSpieler("Spieler1", AllesKaeufer.class);
//        schiedsrichter.registriereSpieler("Spieler2", Interactive.class);
//        schiedsrichter.registriereSpieler("Spieler3", AllesKaeufer.class);
//        schiedsrichter.registriereSpieler("Spieler4", new AllesKaeufer());
//        schiedsrichter.registriereSpieler("Spieler5", new AllesKaeufer());
//        schiedsrichter.registriereSpieler("Spieler6", new AllesKaeufer());
//        schiedsrichter.spielStarten();
//        schiedsrichter.spieleSpielZuEnde();

    }
}
