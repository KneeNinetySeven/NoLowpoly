package org.knee.nonopoly.entities.spielerStrategien;

import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.felder.immobilien.ImmobilienFeld;
import org.knee.nonopoly.felder.immobilien.Strasse;

/**
 * Created by Nils on 24.09.2016.
 */
public class AllesKaeufer extends Strategie {

    public AllesKaeufer() {
        this.setName("Alles-Kauefer");
    }

    @Override
    public boolean erlaubtFeldKauf(Spieler spieler, ImmobilienFeld feld) {
        return feld.getKaufpreis() < spieler.getGuthaben();
    }

    @Override
    public boolean erlaubtHausbau(Spieler spieler, Strasse feld) {
        return feld.getHauspreis() < spieler.getGuthaben();
    }

    @Override
    public String toString(){
        return "Kauft alles, was nicht bei drei auf dem Baum ist!";
    }
}
