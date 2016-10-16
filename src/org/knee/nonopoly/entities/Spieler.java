package org.knee.nonopoly.entities;

import org.knee.nonopoly.entities.spielerStrategien.Strategie;
import org.knee.nonopoly.karten.Karte;

/**
 * Created by Nils on 09.09.2016.
 *
 *
 */
public class Spieler extends Entity {

    private boolean imSpiel;
    private int imGefaengnis;
    private Karte gefaengnisFreiKarte;
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
        super();
        this.setGuthaben(0);
        this.setPosition(0);
        this.setName("");
        this.setImSpiel(true);
    }

    public void geheInsGefaengnis(){
        setPosition(11);
        setImGefaengnis(3);
    }

    @Override
    public String toString(){
        return "Spieler[" + this.getGuthaben() + "," + this.getName() + "," +"imSpiel:" + this.imSpiel + "]" +
                "\n \t Seine Strategie: " + this.strategie.toString() ;
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

    public boolean isImSpiel() {
        return imSpiel;
    }

    public int getImGefaengnis() {
        return imGefaengnis;
    }

    public void setImGefaengnis(int imGefaengnis) {
        this.imGefaengnis = imGefaengnis;
    }

    public Karte getGefaengnisFreiKarte() {
        return gefaengnisFreiKarte;
    }

    public void setGefaengnisFreiKarte(Karte gefaengnisFreiKarte) {
        this.gefaengnisFreiKarte = gefaengnisFreiKarte;
    }
}
