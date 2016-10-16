package org.knee.nonopoly.logik;

import org.jdom2.DataConversionException;
import org.jdom2.JDOMException;
import org.knee.nonopoly.entities.Bank;
import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.entities.Steuertopf;
import org.knee.nonopoly.entities.spielerStrategien.Strategie;
import org.knee.nonopoly.felder.Feld;
import org.knee.nonopoly.felder.FeldTypen;
import org.knee.nonopoly.felder.Los;
import org.knee.nonopoly.logik.XMLUtils.JDOMParsing;
import org.knee.nonopoly.logik.logging.Protokollant;
import org.knee.nonopoly.logik.wuerfel.Wuerfel;
import org.knee.nonopoly.logik.wuerfel.Wurf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nils on 11.09.2016.
 *
 * Die Klasse Schiedsrichter übernimmt die Regisseurs-Rolle beim Spiel
 */
public class Schiedsrichter {

    // Spieler
    private ArrayList<Spieler> teilnehmer;
    private int naechsterSpieler;

    // Spielwerkzeuge
    private Wuerfel wuerfel;
    private Wurf letzterWurf;
    private Bank bank;
    private Steuertopf steuertopf;
    private Protokollant protokollant;
    private List<Feld> spielbrett;
    private int rundenZaehler;

    // Parser
    private JDOMParsing jdomParser;

    public Schiedsrichter() {
        // Werkzeuge für den Schiedsrichter einrichten
        this.wuerfel = new Wuerfel();
        this.setProtokollant(new Protokollant());
        this.bank = new Bank();
        this.steuertopf = new Steuertopf();
        this.rundenZaehler = 0;

        // Spielbrett
        spielbrett = new ArrayList<>();

        // Spieler
        this.teilnehmer = new ArrayList<>();
        this.naechsterSpieler = 0;

        try {
            this.jdomParser = new JDOMParsing("nichtStrassen.xml", "strassen.xml");
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.spielbrettAnlegen();
        this.besitzerInitialisieren();
    }

    /**
     * Legt ein Spielbrett mittels XMLParser an
     */
    private void spielbrettAnlegen() {
        try {
            spielbrett = jdomParser.legeSpielbrettAn();
        } catch (DataConversionException e) {
            e.printStackTrace();
        }

        spielbrett.stream().forEach(feld -> {
            if(feld.getIndex() != spielbrett.indexOf(feld)){
                System.out.println("Feld " + feld.getName());
            }
        });

        spielbrett.forEach(feld -> getProtokollant().printAs(
                "Anlegen auf: " + getSpielbrett().indexOf(feld)
                + " Index: " + feld.getIndex()
                + " von: " + feld.getName()));
    }

    /**
     * Erstellt einen neuen Eintrag in der Liste der teilnehmenden Spieler
     * Generiert ein neues Objekt des Klasse Spieler
     *
     * @param name      Name des Spielers
     * @param strategie Strategie des anzulegenden Spielers
     */
    public void registriereTeilnehmer(String name, Strategie strategie) {
        this.teilnehmer.add(Spieler.spielerErzeugen(name, strategie));
    }

    /**
     * Lässt den Schiedsrichter einen Spielzug ausrichten
     *
     * @return Gibt zurück, ob das Spiel noch läuft
     */
    public boolean spieleEinenSpielzug() {
        Spieler aktiverSpieler = teilnehmer.get(naechsterSpieler);
        Feld aktivesFeld = spielbrett.get(aktiverSpieler.getPosition());

        protokollant.printAs("Aktiver Spieler: "
                + aktiverSpieler.getName()
                + " [" + aktiverSpieler.getGuthaben() + " | " + aktiverSpieler.getPosition() + "]");
        // Verbleibende Teilnehmer sollen spielen können
        if (aktiverSpieler.getImSpiel()) {
            if (aktiverSpieler.getImGefaengnis() > 0) {
                // Gefängnis-Insassen würfeln nicht
                aktivesFeld.fuehrePflichtAktionAus(this);
            } else {
                letzterWurf = wuerfel.wuerfeln();
                protokollant.printAs(aktiverSpieler.getName() + " würfelt: " + letzterWurf.getWurf1() + " " + letzterWurf.getWurf2());
                bewegeSpieler();
                aktivesFeld = spielbrett.get(aktiverSpieler.getPosition());
                protokollant.printAs(aktiverSpieler.getName()
                        + " steht auf Feld: "
                        + aktivesFeld.getName()
                        + " (" + (aktivesFeld.getIndex()) + ")");
                aktivesFeld.fuehrePflichtAktionAus(this);
            }
        }

        // Nach dem Zug ist der nächste Spieler dran!
        if (naechsterSpieler < teilnehmer.toArray().length - 1) {
            naechsterSpieler++;
        } else {
            rundenZaehler++;
            naechsterSpieler = 0;
            protokollant.printAs("\t ** Rundenübertrag auf: " + rundenZaehler);
        }
        return spielLäuftNoch();
    }

    /**
     * Spielt so lange Züge, bis die Runde beendet ist
     *
     * @return Gibt zurück, ob das Spiel weitergeht
     */
    public boolean spieleEineRunde() {
        for (int zuegeBisEnde = teilnehmer.toArray().length - naechsterSpieler;
             zuegeBisEnde > 0; zuegeBisEnde--) {
            spieleEinenSpielzug();
        }
        return spielLäuftNoch();
    }

    /**
     * Spielt so lange Runden, wie das Spiel noch nicht beendet ist
     */
    public void spieleSpielZuEnde() {
        while (this.spieleEineRunde()) {
            // DO NOTHING
        }
    }

    /**
     * Zahlt das Startkapital an alle Spieler aus.
     * Das Kapital wird von der Bank zur Verfügung gestellt
     */
    public void zahleStartkapitalAus() {
        getBank().setGuthaben(200000);
        getTeilnehmer().forEach((Spieler s) -> {
            getBank().ueberweiseAn(30000, s);
        });
    }

    /**
     * Ermittelt anhand des Bankguthabens und der Spielereigenschaften, ob das Spiel weiterläuft
     *
     * @return Gibt zurück, ob das Spiel beendet ist
     */
    private boolean spielLäuftNoch() {
        int nochImSpiel = countSpielerImSpiel();
        return (nochImSpiel > 1) && (bank.getGuthaben() > 0);
    }

    /**
     * Bewegt den Spieler auf das nächste Feld.
     * Verwendet wird der letzte Würfelwurf.
     */
    private void bewegeSpieler() {
        Spieler aktiverSpieler = teilnehmer.get(naechsterSpieler);
        int neuePosition = (aktiverSpieler.getPosition() + letzterWurf.getSum());
        System.out.println("CROSSCHECK: "
                + letzterWurf.getWurf1() + " | "
                + letzterWurf.getWurf2() + " | "
                + letzterWurf.getSum() + " \n >> \t "
                + aktiverSpieler.getPosition() + " --> " + neuePosition);
        if ((spielbrett.size() - 1) < neuePosition) {
            // Sollte der Spieler über das letze Feld hinausgehen, wird wieder vorn angefangen
            aktiverSpieler.setPosition(neuePosition - spielbrett.size());
            Los feld = (Los) spielbrett.get(0);
            bank.ueberweiseAn(feld.getUeberschreitung(), aktiverSpieler);
            getProtokollant().printAs(aktiverSpieler.getName() + " geht über Los und bekommt: " + feld.getUeberschreitung());
        } else {
            // Die Spielerposition wird gesetzt
            aktiverSpieler.setPosition(neuePosition);
        }
    }

    /**
     * @return Gibt zurück, wie viele Spieler noch im Spiel sind
     */
    private int countSpielerImSpiel() {
        return Math.toIntExact(
                teilnehmer
                        .stream()
                        .filter((Spieler s) -> s.isImSpiel()).count());
    }

    /**
     * Setzt intial den Besitzer aller Immobilien als Bank
     */
    private void besitzerInitialisieren() {
        spielbrett
                .stream()
                .filter((Feld f) -> f.istVomTyp(FeldTypen.IMMOBILIENFELD))
                .forEach(feld -> feld.initialisiereBesitzer(getBank()));
    }

    public List<Feld> getSpielbrett() {
        return spielbrett;
    }

    public Bank getBank() {
        return bank;
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

    public Spieler getAktiverSpieler() {
        return teilnehmer.get(naechsterSpieler);
    }

    public Wurf getLetzterWurf() {
        return letzterWurf;
    }

    public Steuertopf getSteuertopf() {
        return steuertopf;
    }
}
