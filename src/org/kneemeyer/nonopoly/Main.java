package org.kneemeyer.nonopoly;

import org.kneemeyer.nonopoly.entities.Bank;
import org.kneemeyer.nonopoly.entities.SpielerFabrik;
import org.kneemeyer.nonopoly.entities.spielerStrategien.AllesKaeufer;
import org.kneemeyer.nonopoly.entities.spielerStrategien.StrassenMogul;
import org.kneemeyer.nonopoly.logik.SchiedsrichterFabrik;

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
