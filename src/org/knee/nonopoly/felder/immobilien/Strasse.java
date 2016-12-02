package org.knee.nonopoly.felder.immobilien;

import org.knee.nonopoly.entities.Bank;
import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.logik.Schiedsrichter;

import java.util.List;

/**
 * @author Nils
 */
public class Strasse extends ImmobilienFeld {

    private int hausanzahl;
    private List<Integer> mietStaffel;
    int hauspreis;

    /**
     * Konstruktor
     *
     * @param index
     * @param name
     * @param kaufpreis
     * @param mietStaffel
     * @param hauspreis
     */
    public Strasse(int index, String name, int kaufpreis, List<Integer> mietStaffel, int hauspreis) {
        super(index, name, kaufpreis);
        this.immobilienTyp = ImmobilienTypen.STRASSE;
        this.mietStaffel = mietStaffel;
        this.hauspreis = hauspreis;
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
        if (this.besitzer == schiedsrichter.getBank()) {
            if (aktiverSpieler.getStrategie().erlaubtFeldKauf(aktiverSpieler, this)) {
                schiedsrichter.getProtokollant().printAs(aktiverSpieler.getName() + " kauft " + getName());
                wirdGekauftDurchSpieler(aktiverSpieler, schiedsrichter.getBank());
            }
        } else if (this.besitzer == aktiverSpieler) {
            if (aktiverSpieler.getStrategie().erlaubtHausbau(aktiverSpieler, this) && (getHausanzahl() < mietStaffel.size())) {
                schiedsrichter.getProtokollant().printAs(aktiverSpieler + " baut ein neues Haus");
                wirdNeuBebaut(aktiverSpieler, schiedsrichter.getBank());
                schiedsrichter.getProtokollant().printAs(aktiverSpieler + " baut Haus Nummer: " + getHausanzahl());
            }
        } else {
            zahleMiete(aktiverSpieler);
        }
    }

    /**
     * Bebaut die Strasse mit einem Weiteren Haus
     *
     * @param spieler
     * @param bank
     */
    public void wirdNeuBebaut(Spieler spieler, Bank bank) {
            spieler.ueberweiseAn(hauspreis, bank);
            baueHaus();
    }

    /**
     * Erhöht die Häuseranzahl um Eins
     */
    public void baueHaus() {
        this.setHausanzahl(getHausanzahl() + 1);
    }

    /**
     * Lässt den übergebenen Spieler die Miete nach Bebauung der Strasse zahlen
     * @param spieler
     */
    @Override
    public void zahleMiete(Spieler spieler) {
        if (getHausanzahl()>0){
            spieler.ueberweiseAn(mietStaffel.get(getHausanzahl()-1), besitzer);
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(this.getName() + "{");
        sb.append("index=").append(this.getIndex());
        sb.append(", name=").append(this.getName());
        sb.append(", kaufpreis=").append(this.getKaufpreis());
        sb.append(", mietStaffel=").append(this.mietStaffel);
        sb.append(", hauspreis=").append(this.hauspreis);
        sb.append(", besitzer={").append(this.getBesitzer()).append("}");
        sb.append(", hausanzahl=").append(this.getHausanzahl());
        sb.append('}');
        return sb.toString();
    }

    public int getHausanzahl() {
        return hausanzahl;
    }

    public void setHausanzahl(int hausanzahl) {
        this.hausanzahl = hausanzahl;
    }

    public int getHauspreis() {
        return hauspreis;
    }

}
