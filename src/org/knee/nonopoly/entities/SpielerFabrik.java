package org.knee.nonopoly.entities;

import org.knee.nonopoly.entities.spielerStrategien.StrategieFabrik;

/**
 * Created by Nils on 09.09.2016.
 */
public class SpielerFabrik extends Entity {

    private boolean imSpiel;
    private String name;
    private int position;
    private StrategieFabrik strategie;

    public static final SpielerFabrik spielerErzeugen(String name, StrategieFabrik strategie){
        SpielerFabrik spieler = new SpielerFabrik();
        spieler.setName(name);
        spieler.setImSpiel(true);
        spieler.setStrategie(strategie);
        return spieler;
    }

    public SpielerFabrik() {
        this.setGuthaben(0);
        this.setPosition(0);
        this.setName("");
    }

    @Override
    public String toString(){
        return "Spieler[" + this.getGuthaben() + "," + this.getName() + "," +"imSpiel:" + this.imSpiel + "]" +
                "\n \t Seine Strategie: " + this.strategie.toString() ;
    }

    public boolean istImSpiel() {
        return imSpiel;
    }

    public void setImSpiel(boolean imSpiel) {
        this.imSpiel = imSpiel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    };

    public StrategieFabrik getStrategie() {
        return strategie;
    }

    public void setStrategie(StrategieFabrik strategieFabrik) {
        this.strategie = strategieFabrik;
    }
}
