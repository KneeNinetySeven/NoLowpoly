package org.knee.nonopoly;

import org.knee.nonopoly.entities.spielerStrategien.AllesKaeufer;
import org.knee.nonopoly.entities.spielerStrategien.StrassenMogul;
import org.knee.nonopoly.felder.kartenFelder.EreignisFeld;
import org.knee.nonopoly.felder.kartenFelder.GemeinschaftsFeld;
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

        EreignisFeld ereignisFeld = new EreignisFeld(10, "Ereignis");
        GemeinschaftsFeld gemeinschaftsFeld = new GemeinschaftsFeld(11, "Gemeinschaftsfeld");

        System.out.println(ereignisFeld.istVomTyp(gemeinschaftsFeld.typ));

    }
}
