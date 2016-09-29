package org.knee.nonopoly;

import org.knee.nonopoly.logik.SchiedsrichterFabrik;
import org.knee.nonopoly.entities.spielerStrategien.AllesKaeufer;
import org.knee.nonopoly.entities.spielerStrategien.StrassenMogul;

/**
 * Created by Nils on 09.09.2016.
 */
public class Main {

    SchiedsrichterFabrik schiedsrichter;

    public Main() {
        this.schiedsrichter = new SchiedsrichterFabrik();
    }

    public static void  main(String[] args){
        new Main().runGame();
    }

    public void runGame(){
        schiedsrichter.registriereTeilnehmer("nils", new AllesKaeufer());
        schiedsrichter.registriereTeilnehmer("Matze", new StrassenMogul());
        System.out.println(schiedsrichter.getBank().toString());
        schiedsrichter.getProtokollant().printSpieler(schiedsrichter.getTeilnehmer());
    }
}
