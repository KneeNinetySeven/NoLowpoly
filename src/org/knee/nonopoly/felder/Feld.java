package org.knee.nonopoly.felder;

import org.knee.nonopoly.entities.Bank;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Nils
 *         Implementiert die Basis für jedes Spielfeld
 */
public abstract class Feld {

    private int index;
    private String name;
    public FeldTypen typ;

    /**
     * Konstruktor
     *
     * @param index Index des neuen Feldes
     * @param name  Name des neuen Feldes
     */
    public Feld(int index, String name) {
        this.index = index;
        this.name = name;
        this.typ = FeldTypen.ABSTRACT;
    }

    public Feld() {
        this.setName("Feld");
    }

    /**
     * Implementiert die Initialisierungssequenz zur Festlegung des Initialbesitzers (Bank)
     *
     * @param bank Bank-Objekt, das als Initialbesitzer festgelegt werden soll
     */
    public void initialisiereBesitzer(Bank bank) {

    }

    /**
     * Führt die Aktion des Feldes für den aktiven Spieler aus
     * Wird in den einzelnen Feldern überschrieben
     *
     * @param schiedsrichter
     */
    public void fuehrePflichtAktionAus(Schiedsrichter schiedsrichter) {
        // Tut nichts
    }

    public boolean istVomTyp(FeldTypen testType) {
        return this.typ == testType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return this.index;
    }

    @Override
    public String toString() {
        return "Feld{" +
                "index=" + index +
                ", name='" + name + '\'' +
                ", typ=" + typ +
                '}';
    }
}
