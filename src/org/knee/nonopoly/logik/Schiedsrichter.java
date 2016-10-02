package org.knee.nonopoly.logik;

import org.knee.nonopoly.entities.Bank;
import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.entities.spielerStrategien.Strategie;
import org.knee.nonopoly.felder.abstracts.Feld;
import org.knee.nonopoly.logik.logging.Protokollant;
import org.knee.nonopoly.logik.util.XML.DOMParsingUtil;

import java.util.ArrayList;

/**
 * Created by Nils on 11.09.2016.
 */
public class Schiedsrichter {

    private Feld[] spielbrett;
    private ArrayList<Spieler> teilnehmer;
    private Bank bank;
    private Protokollant protokollant;
//    private SAXParsingUtil strassenParser;
//    private SAXParsingUtil nichtStrassenParser;
    private DOMParsingUtil domParser;

    public Schiedsrichter() {
        this.setProtokollant(new Protokollant());
        this.bank = new Bank();
        this.teilnehmer = new ArrayList<Spieler>();
//        this.strassenParser = new SAXParsingUtil("nichtStrassen.xml");
//        this.nichtStrassenParser = new SAXParsingUtil("nichtStrassen.xml");
        this.domParser = new DOMParsingUtil("nichtStrassen.xml");
        this.spielbrett = new Feld[48];
        spielbrettAnlegen();
    }

    private void spielbrettAnlegen() {

//        this.nichtStrassenParser = new SAXParsingUtil("nichtStrassen.xml");
//        try {
//            nichtStrassenParser.dateiVerarbeiten();
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

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

    public void registriereTeilnehmer(String name, Strategie strategie) {
        this.teilnehmer.add(Spieler.spielerErzeugen(name, strategie));
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
