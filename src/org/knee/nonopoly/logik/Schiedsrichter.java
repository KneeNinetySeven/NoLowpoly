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
import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.karten.ereigniskarten.*;
import org.knee.nonopoly.karten.gemeinschaftskarten.*;
import org.knee.nonopoly.karten.gemeinschaftskarten.DividendenKarte;
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

    // Aktionskarten
    private int gemeinschaftsKartenPointer;
    private List<Karte> gemeinschaftsKarten;
    private int ereignisKartenPointer;
    private List<Karte> ereignisKarten;

    // Parser
    private JDOMParsing jdomParser;

    public Schiedsrichter() {
        // Werkzeuge für den Schiedsrichter einrichten
        this.wuerfel = new Wuerfel();
        this.setProtokollant(new Protokollant());
        this.bank = new Bank();
        this.steuertopf = new Steuertopf();
        this.rundenZaehler = 0;

        // Aktionskarten
        this.gemeinschaftsKartenPointer = 0;
        this.ereignisKartenPointer = 0;
        this.gemeinschaftsKarten = new ArrayList<>();
        this.ereignisKarten = new ArrayList<>();

        this.legeKartenAn();

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

        spielbrett.forEach(feld -> getProtokollant().printAs(
                "Anlegen auf: " + getSpielbrett().indexOf(feld)
                + " Index: " + feld.getIndex()
                + " von: " + feld.getName()));
    }

    /**
     * Legt die Aktionskarten an und auf die entsprechenden Kartenstapel
     */
    private void legeKartenAn(){
        ArrayList<Karte> tmp = new ArrayList<>();

        //Gemeinschaftskarten
        tmp.add(new ArztKarte());
        tmp.add(new BankIrrtumKarte());
        tmp.add(new DividendenKarte());
        tmp.add(new ErbschaftKarte());
        tmp.add(new EStRueckzahlungKarte());
        tmp.add(new GeburtstagKarte());
        tmp.add(new GefaengnisKarte());
        tmp.add(new JahresrenteKarte());
        tmp.add(new KrankenhausKarte());
        tmp.add(new KreuzwortraetselKarte());
        tmp.add(new LagerverkaeufeKarte());
        tmp.add(new org.knee.nonopoly.karten.gemeinschaftskarten.LosKarte());
        tmp.add(new SchoenheitspreisKarte());
        tmp.add(new SchulgeldKarte());
        tmp.add(new StrassenAusbesserungKarte());

        // Hier sollen die Karten gemischt werden. Daher eine tmp Liste
        gemeinschaftsKarten.addAll(tmp);

        // Ereigniskarten
        tmp = new ArrayList<>();
        tmp.add(new BadstrasseKarte());
        tmp.add(new DividendenKarte());
        tmp.add(new DreiFelderZurueckKarte());
        tmp.add(new HaeuserRenovierenKarte());
        tmp.add(new org.knee.nonopoly.karten.ereigniskarten.LosKarte());
        tmp.add(new NaechsterBahnhofKarte());
        tmp.add(new OpernplatzKarte());
        tmp.add(new SchlossalleeKarte());
        tmp.add(new SeestrasseKarte());
        tmp.add(new StrafeGemeinschaftKarte());
        tmp.add(new SuedbahnhofKarte());
        tmp.add(new VorstandKarte());
        tmp.add(new ZinsenKarte());
        tmp.add(new ZuSchnellKarte());
        // Ereigniskarten werden gemischt.
        ereignisKarten.addAll(tmp);
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

        letzterWurf = wuerfel.wuerfeln();
        protokollant.printAs(aktiverSpieler.getName() + " würfelt: " + letzterWurf.getWurf1() + " " + letzterWurf.getWurf2());

        // Auf Pasche überprüfen
        if(letzterWurf.istPasch()){
            if(aktiverSpieler.registrierePasch()) {
                // Beim dritten Pasch ins Gefängnis verschieben, statt auf dem Feld
                aktiverSpieler.geheInsGefaengnis();
                return;
            }
        } else {
            // Paschserie unterbrechen
            aktiverSpieler.pascheZuruecksetzen();
        }

        // Felderzahl betrachten
        int neuePosition = (aktiverSpieler.getPosition() + letzterWurf.getSum());
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

    /**
     * Führt die nächste Gemeinschaftskarte aus
     */
    public void naechsteGemeinschaftskarte(){
        if(gemeinschaftsKartenPointer == gemeinschaftsKarten.size()) {
            this.gemeinschaftsKartenPointer = 0;
        }
        this.gemeinschaftsKarten.get(gemeinschaftsKartenPointer).fuehreKartenAktionAus(this);
        getProtokollant().printAs(this.gemeinschaftsKarten.get(gemeinschaftsKartenPointer).toString() + " wurde gezogen.");
        this.gemeinschaftsKartenPointer++;
    }

    /**
     * Führt die nächste Ereigniskarte aus
     */
    public void naechsteEreigniskarte(){
        if(ereignisKartenPointer == ereignisKarten.size()) {
            ereignisKartenPointer = 0;
        }
        ereignisKarten.get(ereignisKartenPointer).fuehreKartenAktionAus(this);
        getProtokollant().printAs( ereignisKarten.get(ereignisKartenPointer) + " wurde gezogen.");
        this.ereignisKartenPointer++;
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
