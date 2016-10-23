package org.knee.nonopoly.felder.kartenFelder;

import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * Created by Nils on 24.09.2016.
 */
public class EreignisFeld extends KartenFeld {

    /**
     * Konstruktor
     * @param index
     * @param name
     */
    public EreignisFeld(int index, String name) {
        super(index, name);
        this.kartenTyp = KartenTypen.EREIGNISFELD;
    }

    /**
     * Führt die Aktion des Feldes für den aktiven Spieler aus
     * Wird in den einzelnen Feldern überschrieben
     *
     * @param schiedsrichter
     */
    @Override
    public void fuehrePflichtAktionAus(Schiedsrichter schiedsrichter) {
        schiedsrichter.naechsteEreigniskarte();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ereignisfeld{");
        sb.append("index='").append(this.getIndex()).append('\'');
        sb.append("name='").append(this.getName()).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
