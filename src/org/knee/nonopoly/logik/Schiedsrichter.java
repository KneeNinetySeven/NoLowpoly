package org.knee.nonopoly.logik;

import org.knee.nonopoly.entities.Bank;
import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.entities.spielerStrategien.Strategie;
import org.knee.nonopoly.felder.abstracts.Feld;
import org.knee.nonopoly.logik.logging.Protokollant;
import org.knee.nonopoly.logik.util.XML.DOMParsingUtil;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Nils on 11.09.2016.
 */
public class Schiedsrichter {

    private Feld[] spielbrett;
    private ArrayList<Spieler> teilnehmer;
    private Bank bank;
    private Protokollant protokollant;
    private DOMParsingUtil domParser;

    public Schiedsrichter() {
        this.setProtokollant(new Protokollant());
        this.bank = new Bank();
        this.teilnehmer = new ArrayList<Spieler>();

        try {
            this.domParser = new DOMParsingUtil("nichtStrassen.xml");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        this.spielbrett = new Feld[48];
        this.spielbrettAnlegen();
    }

    private void spielbrettAnlegen() {
        // TODO: domParser einrichten
        try {
            this.domParser.dateiVerarbeiten();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
