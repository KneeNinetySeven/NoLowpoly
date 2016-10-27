package org.knee.nonopoly.felder.immobilien;

import org.knee.nonopoly.entities.Bank;
import org.knee.nonopoly.entities.Entity;
import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.felder.Feld;
import org.knee.nonopoly.felder.FeldTypen;

/**
 * Created by Nils on 24.09.2016.
 */
public abstract class ImmobilienFeld extends Feld {

    private final int kaufpreis;

    public Entity besitzer;


    public ImmobilienTypen immobilienTyp;
    /**
     * Konstruktor
     *
     * @param index
     * @param name
     * @param kaufpreis
     */
    public ImmobilienFeld(int index, String name, int kaufpreis) {
        super(index, name);
        this.kaufpreis = kaufpreis;
        this.typ = FeldTypen.IMMOBILIENFELD;
        this.immobilienTyp = ImmobilienTypen.ABSTRACT;
    }

    /**
     * Implementiert die Initialisierungssequenz zur Festlegung des Initialbesitzers (Bank)
     *
     * @param bank Bank-Objekt, das als Initialbesitzer festgelegt werden soll
     */
    @Override
    public void initialisiereBesitzer(Bank bank) {
        this.besitzer = bank;
    }

    /**
     * Verkauft das Feld an den angegebenen Spieler und nimmt diesem auch das Geld dafür ab
     *
     * @param spieler
     * @param bank
     */
    public void wirdGekauftDurchSpieler(Spieler spieler, Bank bank) {
        spieler.ueberweiseAn(getKaufpreis(), bank);
        besitzer = spieler;
    }

    /**
     * Lässt den übergebenen Spieler Miete zahlen
     * @param spieler
     */
    public abstract void zahleMiete(Spieler spieler);

    /**
     * @param testImmoTyp
     * @return Gibt zurück, ob das Feld vom selben Immobilientyp ist, wie übergeben
     */
    public boolean istImmobilienTyp(ImmobilienTypen testImmoTyp) {
        return this.immobilienTyp == testImmoTyp;
    }

    public int getKaufpreis() {
        return kaufpreis;
    }

    public Entity getBesitzer() {
        return besitzer;
    }
}
