package org.knee.nonopoly.felder;

import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * Created by Nils on 24.09.2016.
 */
public class Gefaengnis extends Feld {

    private Spieler aktiverSpieler;

    public Gefaengnis(int index, String name) {
        super(index, name);
        this.typ = FeldTypen.GEFAENGNIS;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Gefaengnis{");
        sb.append("index='").append(this.getIndex()).append('\'');
        sb.append("name=").append(this.getName());
        sb.append('}');
        return sb.toString();
    }

    /**
     * <b>Pflichtaktion des Gefängnisses</b>
     * Landet ein Spieler auf dem Gefängnis, soll nichts passieren
     * Ist er im Gefängnis und hat noch Warterunden offen, wird diese Zahl angepasst
     *
     * @param schiedsrichter
     */
    @Override
    public void fuehrePflichtAktionAus(Schiedsrichter schiedsrichter) {

        aktiverSpieler = schiedsrichter.getAktiverSpieler();

        if (aktiverSpieler.getImGefaengnis() > 0) {
            if (aktiverSpieler.getGefaengnisFreiKarte() == null) {
                // Logging
                schiedsrichter
                        .getProtokollant()
                        .printAs(aktiverSpieler.getName() + " bleibt diese Runde im Gefängnis sitzen.");

                // Wartezeit anrechnen
                aktiverSpieler.setImGefaengnis(aktiverSpieler.getImGefaengnis() - 1);
            } else {
                // Logging
                schiedsrichter
                        .getProtokollant()
                        .printAs(aktiverSpieler.getName() + " entflieht dem Gefängnis mit der Gefängnis-Frei-Karte.");
                aktiverSpieler.verlasseDasGefängnis();
                aktiverSpieler.gefaengniskarteSpielen();
                aktiverSpieler.setGefaengnisFreiKarte(null);
            }
        } else {
            schiedsrichter.getProtokollant().printAs(schiedsrichter.getAktiverSpieler().getName() + " steht vor dem Gefängnis und tut nix.");
        }
    }
}
