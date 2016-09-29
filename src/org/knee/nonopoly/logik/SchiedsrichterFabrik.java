package org.knee.nonopoly.logik;

import org.knee.nonopoly.entities.Bank;
import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.entities.spielerStrategien.Strategie;
import org.knee.nonopoly.felder.abstracts.Feld;
import org.knee.nonopoly.felder.implementations.Los;
import org.knee.nonopoly.felder.implementations.immobilien.Strassen;
import org.knee.nonopoly.logik.logging.Protokollant;
import org.knee.nonopoly.logik.util.XML.SAXParsingUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nils on 11.09.2016.
 */
public class SchiedsrichterFabrik {

    private List<Feld> spielbrett;
    private ArrayList<Spieler> teilnehmer;
    private Bank bank;
    private Protokollant protokollant;
    private SAXParsingUtil strassenParser;
    private SAXParsingUtil nichtStrassenParser;

    public SchiedsrichterFabrik(){
        this.setProtokollant(new Protokollant());
        this.bank = new Bank();
        this.teilnehmer = new ArrayList<Spieler>();
        this.strassenParser = new SAXParsingUtil("nichtStrassen.xml");
        this.nichtStrassenParser = new SAXParsingUtil("nichtStrassen.xml");
        this.spielbrett = new ArrayList<Feld>(48);
    }

    private void spielbrettAnlegen(){
        this.spielbrett.add(new Los());
        this.spielbrett.add(new Strassen());
        this.spielbrett.get(this.spielbrett.toArray().length).setName("Badstrasse");
    }

    public Protokollant getProtokollant() {
        return protokollant;
    }

    private void setProtokollant(Protokollant protokollant) {
        this.protokollant = protokollant;
    }

    public ArrayList<Spieler> getTeilnehmer() {
        return teilnehmer;
    }

    public void registriereTeilnehmer(String name, Strategie strategie){
        this.teilnehmer.add(Spieler.spielerErzeugen(name, strategie));
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
