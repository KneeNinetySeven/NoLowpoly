package org.knee.nonopoly;

import org.knee.nonopoly.entities.spielerStrategien.AllesKaeufer;
import org.knee.nonopoly.entities.spielerStrategien.StrassenMogul;
import org.knee.nonopoly.logik.Schiedsrichter;
import org.knee.nonopoly.ui.MainStage;

/**
 * Created by Nils on 09.09.2016.
 */
public class Main {

    Schiedsrichter schiedsrichter;
    MainStage ui;

    public Main() {
        schiedsrichter = new Schiedsrichter();
        this.ui = new MainStage();
    }

    public static void  main(String[] args){
        new Main().runGame();
    }

    public void runGame(){
        schiedsrichter.registriereTeilnehmer("nils", new AllesKaeufer());
        schiedsrichter.registriereTeilnehmer("Matze", new StrassenMogul());
    }
}
