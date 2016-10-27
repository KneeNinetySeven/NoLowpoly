package org.knee.nonopoly.karten.gemeinschaftskarten;

import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Adrian
 * Gemeinschaftskarte 6
 */
public class BankIrrtumKarte implements Karte {
    /**
     * Der Spieler erhält eine Zahlung von der Bank.
     * Grund: Bank-Irrtum
     * @param schiedsrichter
     */
    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        schiedsrichter.getBank().ueberweiseAn(4000, schiedsrichter.getAktiverSpieler());
    }
}
