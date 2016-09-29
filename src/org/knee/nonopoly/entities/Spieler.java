package org.knee.nonopoly.entities;

import org.knee.nonopoly.entities.spielerStrategien.Strategie;

/**
 * Created by Nils on 09.09.2016.
 */
public class Spieler extends Entity {

    private boolean imSpiel;
    private String name;
    private int position;
    private Strategie strategie;

    public static final Spieler spielerErzeugen(String name, Strategie strategie){
        Spieler spieler = new Spieler();
        spieler.setName(name);
        spieler.setImSpiel(true);
        spieler.setStrategie(strategie);
        return spieler;
    }

    public Spieler() {
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
    }

    public Strategie getStrategie() {
        return strategie;
    }

    public void setStrategie(Strategie strategie) {
        this.strategie = strategie;
    }
}
