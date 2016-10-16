package org.knee.nonopoly;

import org.knee.nonopoly.entities.spielerStrategien.AllesKaeufer;
import org.knee.nonopoly.entities.spielerStrategien.StrassenMogul;
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
        schiedsrichter.registriereTeilnehmer("Nils", new AllesKaeufer());
        schiedsrichter.registriereTeilnehmer("Matze", new StrassenMogul());
        schiedsrichter.zahleStartkapitalAus();
        schiedsrichter.spieleEineRunde();
        schiedsrichter.spieleEineRunde();
        schiedsrichter.spieleEineRunde();
        schiedsrichter.spieleEineRunde();
        schiedsrichter.spieleEineRunde();
        schiedsrichter.spieleEineRunde();
    }
}
