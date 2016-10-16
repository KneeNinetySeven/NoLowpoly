package org.knee.nonopoly.entities.spielerStrategien;

import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.felder.immobilien.ImmobilienFeld;
import org.knee.nonopoly.felder.immobilien.ImmobilienTypen;
import org.knee.nonopoly.felder.immobilien.Strasse;

/**
 * Created by Nils on 24.09.2016.
 */
public class StrassenMogul extends Strategie {

    public StrassenMogul() {
        this.setName("Strassen-Mogul");
    }

    @Override
    public boolean erlaubtFeldKauf(Spieler spieler, ImmobilienFeld feld) {
        return feld.getKaufpreis() < spieler.getGuthaben() && feld.istImmobilienTyp(ImmobilienTypen.STRASSE);
    }

    @Override
    public boolean erlaubtHausbau(Spieler spieler, Strasse feld) {
        return false;
    }

    @Override
    public String toString(){
        return "Würde niemals eine Strasse mit einem Haus oder Hotel verunreinigen!";
    }
}
