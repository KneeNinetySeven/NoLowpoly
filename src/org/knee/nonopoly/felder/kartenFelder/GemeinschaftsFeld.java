package org.knee.nonopoly.felder.kartenFelder;

import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Nils
 */
public class GemeinschaftsFeld extends KartenFeld {

    /**
     * Konstruktor
     * @param index
     * @param name
     */
    public GemeinschaftsFeld(int index, String name) {
        super(index, name);
        this.kartenTyp = KartenTypen.GEMEINSCHAFTSFELD;
    }

    /**
     * Führt die Aktion des Feldes für den aktiven Spieler aus
     *
     * @param schiedsrichter
     */
    @Override
    public void fuehrePflichtAktionAus(Schiedsrichter schiedsrichter) {
        schiedsrichter.naechsteGemeinschaftskarte();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Gemeinschaftsfeld{");
        sb.append("index='").append(this.getIndex()).append('\'');
        sb.append("name='").append(this.getName()).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
