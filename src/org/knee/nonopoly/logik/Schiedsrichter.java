package org.knee.nonopoly.logik;

import org.jdom2.JDOMException;
import org.knee.nonopoly.entities.Bank;
import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.entities.spielerStrategien.Strategie;
import org.knee.nonopoly.felder.Feld;
import org.knee.nonopoly.logik.XMLUtils.JDOMParsing;
import org.knee.nonopoly.logik.logging.Protokollant;
import org.knee.nonopoly.logik.wuerfel.Wuerfel;
import org.knee.nonopoly.logik.wuerfel.Wurf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nils on 11.09.2016.
 */
public class Schiedsrichter {

    private ArrayList<Spieler> teilnehmer;
    private int naechsterSpieler;
    private Wuerfel wuerfel;
    private Bank bank;
    private Protokollant protokollant;
    private JDOMParsing jdomParser;
    private List<Feld> spielbrett;

    public Schiedsrichter() {
        // Werkzeuge für den Schiedsrichter einrichten
        this.wuerfel = new Wuerfel();
        this.setProtokollant(new Protokollant());
        this.bank = new Bank();

        // Spielbrett
        spielbrett = new ArrayList<Feld>();

        // Spieler
        this.teilnehmer = new ArrayList<Spieler>();
        this.naechsterSpieler = 1;

        try {
            this.jdomParser = new JDOMParsing("nichtStrassen.xml");
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.spielbrettAnlegen();
    }

    private void spielbrettAnlegen() {
        this.jdomParser.dateiVerarbeiten();

    }

    public void registriereTeilnehmer(String name, Strategie strategie) {
        this.teilnehmer.add(Spieler.spielerErzeugen(name, strategie));
    }

    public boolean spieleEinenSpielzug() {
        Spieler aktiverSpieler = teilnehmer.get(naechsterSpieler);
        Feld aktivesFeld = spielbrett.get(aktiverSpieler.getPosition());
        // Verbleibende Teilnehmer sollen spielen können
        if(aktiverSpieler.getImSpiel()){
            Wurf wurf = wuerfel.wuerfeln();
            bewegeSpieler(wurf);

        }

        // Nach dem Zug ist der nächste Spieler dran!
        if(naechsterSpieler < teilnehmer.toArray().length){
            naechsterSpieler++;
        } else {
            naechsterSpieler = 1;
        }
        return spielLäuftNoch();
    }

    public boolean spieleEineRunde() {
        return spielLäuftNoch();
    }

    public void spieleSpielZuEnde() {
        while (this.spieleEineRunde()) {
            // DO NOTHING
        }
    }

    private boolean spielLäuftNoch() {
        int nochImSpiel = countSpielerImSpiel();
        return (nochImSpiel > 1) && (bank.getGuthaben() > 0);
    }

    private void bewegeSpieler(Wurf wurf){
        Spieler aktiverSpieler = teilnehmer.get(naechsterSpieler);
        aktiverSpieler.setPosition(aktiverSpieler.getPosition() + wurf.getSum());
    }

    private int countSpielerImSpiel(){
        int imSpiel = 0;
        for (Spieler spieler : this.teilnehmer) {
            if(spieler.getImSpiel()){
                imSpiel++;
            }
        }
        return imSpiel;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
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
}
