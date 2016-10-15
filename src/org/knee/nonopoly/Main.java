package org.knee.nonopoly;

import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.entities.spielerStrategien.AllesKaeufer;
import org.knee.nonopoly.entities.spielerStrategien.StrassenMogul;
import org.knee.nonopoly.felder.kartenFelder.EreignisFeld;
import org.knee.nonopoly.felder.kartenFelder.GemeinschaftsFeld;
import org.knee.nonopoly.logik.Schiedsrichter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nils on 09.09.2016.
 */
public class Main {

    Schiedsrichter schiedsrichter;
    List<Spieler> teilnehmer = new ArrayList<Spieler>();

    public Main() {
        schiedsrichter = new Schiedsrichter();
    }

    public static void  main(String[] args){
        new Main().runGame();
    }

    public void runGame(){
        schiedsrichter.registriereTeilnehmer("Nils", new AllesKaeufer());
        schiedsrichter.registriereTeilnehmer("Matze", new StrassenMogul());
        schiedsrichter.spieleEineRunde();
        schiedsrichter.spieleEinenSpielzug();
        schiedsrichter.spieleEineRunde();
        EreignisFeld ereignisFeld = new EreignisFeld(10, "Ereignis");
        GemeinschaftsFeld gemeinschaftsFeld = new GemeinschaftsFeld(11, "Gemeinschaftsfeld");
    }
}
