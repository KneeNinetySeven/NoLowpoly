package org.knee.nonopoly.entities;

/**
 * Created by Nils on 11.09.2016.
 * <p>
 * Bankklasse für das Spiel
 */
public class Bank extends Entity {

    /**
     * Konstruktor
     */
    public Bank() {
        super();
        setGuthaben(200000);
    }

    @Override
    public String toString() {
        return "Bank[" + getGuthaben() + "]";
    }
}
