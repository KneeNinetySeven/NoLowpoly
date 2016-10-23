package org.knee.nonopoly.felder;

import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * Created by Nils on 24.09.2016.
 */
public class Gefaengnis extends Feld {
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

        if (schiedsrichter.getAktiverSpieler().getImGefaengnis() > 0) {
            if(schiedsrichter.getAktiverSpieler().getGefaengnisFreiKarte() == null) {
                // Logging
                schiedsrichter
                        .getProtokollant()
                        .printAs(schiedsrichter
                                .getAktiverSpieler()
                                .getName() + " bleibt diese Runde im Gefängnis sitzen.");

                // Wartezeit anrechnen
                schiedsrichter
                        .getAktiverSpieler()
                        .setImGefaengnis(schiedsrichter
                                .getAktiverSpieler()
                                .getImGefaengnis() - 1);
            } else {
                // Logging
                schiedsrichter
                        .getProtokollant()
                        .printAs(schiedsrichter
                                .getAktiverSpieler()
                                .getName() + " entflieht dem Gefängnis mit der Gefängnis-Frei-Karte.");
                schiedsrichter.getAktiverSpieler().verlasseDasGefängnis();
                schiedsrichter.getAktiverSpieler().gefaengniskarteSpielen();
                schiedsrichter.getAktiverSpieler().setGefaengnisFreiKarte(null);
            }
        } else {
            schiedsrichter.getProtokollant().printAs(schiedsrichter.getAktiverSpieler().getName() + " steht vor dem Gefängnis und tut nix.");
        }
    }
}
