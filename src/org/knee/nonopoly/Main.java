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
        schiedsrichter.registriereSpieler("Nils", new AllesKaeufer());
        schiedsrichter.registriereSpieler("Adrian", new AllesKaeufer());
        schiedsrichter.registriereSpieler("Matze", new AllesKaeufer());
        schiedsrichter.zahleStartkapitalAus();
        schiedsrichter.spieleEinenSpielzug();
        schiedsrichter.spieleEineRunde();
        schiedsrichter.spieleEineRunde();
        schiedsrichter.spieleEineRunde();
        schiedsrichter.spieleEineRunde();

    }
}
