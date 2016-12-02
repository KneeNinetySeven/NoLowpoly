package org.knee.nonopoly.felder.immobilien;

import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.logik.Schiedsrichter;

import java.util.List;

/**
 * @author Nils
 */
public class Bahnhof extends ImmobilienFeld {

    private List<Integer> mietStaffel;

    /**
     * Konstruktor
     *
     * @param index
     * @param name
     * @param kaufpreis
     * @param mietStaffel
     */
    public Bahnhof(int index, String name, int kaufpreis, List<Integer> mietStaffel) {
        super(index, name, kaufpreis);
        this.immobilienTyp = ImmobilienTypen.BAHNHOF;
        this.mietStaffel = mietStaffel;
    }

    /**
     * Führt die Aktion des Feldes für den aktiven Spieler aus
     * Wird in den einzelnen Feldern überschrieben
     *
     * @param schiedsrichter
     */
    @Override
    public void fuehrePflichtAktionAus(Schiedsrichter schiedsrichter) {
        Spieler aktiverSpieler = schiedsrichter.getAktiverSpieler();

        if (this.besitzer == aktiverSpieler) {
            return;
        } else if (this.besitzer == schiedsrichter.getBank()) {

            if (aktiverSpieler.getStrategie().erlaubtFeldKauf(aktiverSpieler, this)) {
                wirdGekauftDurchSpieler(aktiverSpieler, schiedsrichter.getBank());
            } else {
                return;
            }

        } else {
            zahleMiete(aktiverSpieler);
        }
    }

    @Override
    public void zahleMiete(Spieler spieler) {
        spieler.ueberweiseAn(mietStaffel.get(1), besitzer);
    }

    /**
     * Gibt den aktuellen Wert der Immobilie zurück.
     *
     * @return
     */
    @Override
    public int berechneGrundwert() {
        return getKaufpreis();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bahnhof{");
        sb.append("index='").append(this.getIndex()).append('\'');
        sb.append('}');
        sb.append("name=").append(this.getName());
        sb.append('}');
        sb.append("kaufpreis=").append(this.getKaufpreis());
        sb.append('}');
        sb.append("mietStaffel=").append(mietStaffel);
        sb.append(", besitzer={").append(this.getBesitzer()).append("}");
        sb.append('}');
        return sb.toString();
    }
}
