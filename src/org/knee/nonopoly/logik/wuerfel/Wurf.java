package org.knee.nonopoly.logik.wuerfel;

/**
 * Created by Nils on 24.09.2016.
 */
public class Wurf {

    private int wurf1;
    private int wurf2;

    public Wurf(int wurf1, int wurf2 ){
        this.setWurf1(wurf1);
        this.setWurf2(wurf2);
    }

    public int getSum(){
        return getWurf1() + getWurf2();
    }

    public int getWurf1() {
        return wurf1;
    }

    public void setWurf1(int wurf1) {
        this.wurf1 = wurf1;
    }

    public int getWurf2() {
        return wurf2;
    }

    public void setWurf2(int wurf2) {
        this.wurf2 = wurf2;
    }
}
