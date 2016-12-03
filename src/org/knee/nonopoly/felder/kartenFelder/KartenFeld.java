package org.knee.nonopoly.felder.kartenFelder;

import org.knee.nonopoly.felder.Feld;
import org.knee.nonopoly.felder.FeldTypen;
import org.knee.nonopoly.logik.Schiedsrichter;
import org.knee.nonopoly.logik.logging.Protokollant;

/**
 * Created by Nils on 24.09.2016.
 */
public abstract class KartenFeld extends Feld {
    private int index;
    private String name;
    public KartenTypen kartenTyp;

    /**
     * Konstruktor
     * @param index Index des neuen Felds
     * @param name Name des neuen Felds
     */
    public KartenFeld(int index, String name) {
        this.index = index;
        this.name = name;
        this.typ = FeldTypen.KARTENFELD;
        this.kartenTyp = KartenTypen.ABSTRACT;
    }

    /**
     * Führt die Aktion des Feldes für den aktiven Spieler aus
     * Wird in den einzelnen Feldern überschrieben
     *
     * @param schiedsrichter
     */
    @Override
    public void fuehrePflichtAktionAus(Schiedsrichter schiedsrichter) {
        Protokollant
                .printAs(this,schiedsrichter
                        .getAktiverSpieler()
                        .getName() + " zieht eine Karte auf dem " + this.getName());
    }

    /**
     * @param testKartenTyp
     * @return Gibt zurück, ob der Typ des Kartenfeldes mit dem übergebenen übereinstimmt
     */
    public boolean istKartenTyp(KartenTypen testKartenTyp) {
        return this.kartenTyp == testKartenTyp;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
