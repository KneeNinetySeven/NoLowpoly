package org.knee.nonopoly.logik.wuerfel;

/**
 *
 */
public class Wurf {

    private final int wurf1;
    private final int wurf2;

    public Wurf(int wurf1, int wurf2) {
        this.wurf1 = wurf1;
        this.wurf2 = wurf2;
    }

    /**
     * @return Gibt zurück, ob es sich bei dem Objekt um ein Pasch handelt
     */
    public boolean istPasch() {
        return wurf1 == wurf2;
    }

    /**
     * @return Gibt die Augensumme der Würfel zurück
     */
    public int getSum() {
        return getWurf1() + getWurf2();
    }

    public int getWurf1() {
        return wurf1;
    }

    public int getWurf2() {
        return wurf2;
    }

}
