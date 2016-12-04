package org.knee.nonopoly;

import org.knee.nonopoly.entities.spielerStrategien.AllesKaeufer;
import org.knee.nonopoly.entities.spielerStrategien.Interactive;
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
        try {
            new Main().runGame();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Lässt den Schiedsrichter (noch) nur die richtigen Dinge tun
     */
    public void runGame() throws InstantiationException, IllegalAccessException {
        new MainWindow(schiedsrichter);
        schiedsrichter.registriereSpieler("Spieler1", Interactive.class);
        schiedsrichter.registriereSpieler("Spieler2", AllesKaeufer.class);
        schiedsrichter.registriereSpieler("Spieler3", AllesKaeufer.class);
        schiedsrichter.registriereSpieler("Spieler4", AllesKaeufer.class);
        schiedsrichter.registriereSpieler("Spieler5", AllesKaeufer.class);
//        schiedsrichter.registriereSpieler("Spieler6", AllesKaeufer.class);
//        schiedsrichter.spielStarten();
//        schiedsrichter.spieleSpielZuEnde();

    }
}
