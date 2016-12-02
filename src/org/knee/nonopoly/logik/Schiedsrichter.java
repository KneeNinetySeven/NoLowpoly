package org.knee.nonopoly.logik;

import org.jdom2.DataConversionException;
import org.jdom2.JDOMException;
import org.knee.nonopoly.entities.Bank;
import org.knee.nonopoly.entities.Entity;
import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.entities.Steuertopf;
import org.knee.nonopoly.entities.spielerStrategien.Strategie;
import org.knee.nonopoly.felder.Feld;
import org.knee.nonopoly.felder.FeldTypen;
import org.knee.nonopoly.felder.Los;
import org.knee.nonopoly.felder.immobilien.ImmobilienFeld;
import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.karten.ereigniskarten.*;
import org.knee.nonopoly.karten.gemeinschaftskarten.*;
import org.knee.nonopoly.karten.gemeinschaftskarten.DividendenKarte;
import org.knee.nonopoly.logik.XMLUtils.JDOMParsing;
import org.knee.nonopoly.logik.logging.Protokollant;
import org.knee.nonopoly.logik.wuerfel.Wuerfel;
import org.knee.nonopoly.logik.wuerfel.Wurf;

import java.io.IOException;
import java.util.*;

/**
 * Created by Nils on 11.09.2016.
 * <p>
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
    private Queue<Karte> gemeinschaftsKarten;
    private Queue<Karte> ereignisKarten;

    // Parser
    private JDOMParsing jdomParser;

    /**
     *
     */
    public Schiedsrichter() {
        // Werkzeuge für den Schiedsrichter einrichten
        this.wuerfel = new Wuerfel();
        this.setProtokollant(new Protokollant());
        this.bank = new Bank();
        this.steuertopf = new Steuertopf();
        this.rundenZaehler = 0;

        // Aktionskarten
        this.gemeinschaftsKarten = new ArrayDeque<>();
        this.ereignisKarten = new ArrayDeque<>();

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
     * Setzt intial den Besitzer aller Immobilien als Bank
     */
    private void besitzerInitialisieren() {
        spielbrett
                .stream()
                .filter((Feld f) -> f.istVomTyp(FeldTypen.IMMOBILIENFELD))
                .forEach(feld -> feld.initialisiereBesitzer(getBank()));
    }

    /**
     * Legt die Aktionskarten an und auf die entsprechenden Kartenstapel
     */
    private void legeKartenAn() {
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

        Collections.shuffle(tmp); // Gemeinschaftskarten werden gemischt
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

        Collections.shuffle(tmp); // Ereigniskarten werden gemischt
        ereignisKarten.addAll(tmp);
    }

    /**
     * Erstellt einen neuen Eintrag in der Liste der teilnehmenden Spieler
     * Generiert ein neues Objekt des Klasse Spieler
     *
     * @param name      Name des Spielers
     * @param strategie Strategie des anzulegenden Spielers
     */
    public void registriereSpieler(String name, Strategie strategie) {
        this.teilnehmer.add(Spieler.spielerErzeugen(name, strategie));
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
        return (nochImSpiel > 1) && (bank.getImSpiel());
    }

    /**
     * @return Gibt zurück, wie viele Spieler noch im Spiel sind
     */
    private int countSpielerImSpiel() {
        int anzahl = 0;
        for (Spieler s : teilnehmer) {
            if (s.istImSpiel()) {
                anzahl++;
            }
        }
        return anzahl;
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
        if (letzterWurf.istPasch()) {
            if (aktiverSpieler.registrierePasch()) {
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
     * Lässt den Schiedsrichter einen Spielzug ausrichten
     *
     * @return Gibt zurück, ob das Spiel noch läuft
     */
    private boolean spieleEinenSpielzug() {
        Spieler aktiverSpieler = teilnehmer.get(naechsterSpieler);
        Feld aktivesFeld = spielbrett.get(aktiverSpieler.getPosition());

        protokollant.printAs("Aktiver Spieler: "
                + aktiverSpieler.getName()
                + " [" + aktiverSpieler.getGuthaben() + " | " + aktiverSpieler.getPosition() + "]");
        // Verbleibende Teilnehmer sollen spielen können
        if (!aktiverSpieler.istAusgeschieden()) {
            if (aktiverSpieler.getImGefaengnis() > 0) {
                // Gefängnis-Insassen würfeln nicht
                aktivesFeld.fuehrePflichtAktionAus(this);
            } else {
                bewegeSpieler();
                aktivesFeld = spielbrett.get(aktiverSpieler.getPosition());
                protokollant.printAs(aktiverSpieler.getName()
                        + " steht auf Feld: "
                        + aktivesFeld.getName());
                aktivesFeld.fuehrePflichtAktionAus(this);
            }

            // Ist der aktive Spieler im Laufe der Runde rausgeflogen,
            // wird er ausscheiden gelassen
            if (!aktiverSpieler.getImSpiel()) {
                this.ausscheidenLassen(aktiverSpieler);
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
    private boolean spieleEineRunde() {
        for (int zuegeBisEnde = teilnehmer.size() - naechsterSpieler; zuegeBisEnde > 0; zuegeBisEnde--) {
            if (!spieleEinenSpielzug()) {
                return spielLäuftNoch();
            }
        }
        return spielLäuftNoch();
    }

    /**
     * Spielt so lange Runden, wie das Spiel noch nicht beendet ist
     */
    public void spieleSpielZuEnde() {
        while (this.spieleEineRunde()) {

        }
        this.spielBeenden();
    }

    /**
     * Lässt den übergebenen Spieler ausscheiden
     *
     * @param spieler
     */
    private void ausscheidenLassen(Spieler spieler) {
        spieler.setIstAusgeschieden(true);
        spieler.ueberweiseAn(spieler.getGuthaben(), getBank());
        getSpielbrett().forEach(feld -> {
            if (feld.istVomTyp(FeldTypen.IMMOBILIENFELD)) {
                ImmobilienFeld immobilienFeld = (ImmobilienFeld) feld;
                immobilienFeld.initialisiereBesitzer(getBank());
            }
        });
    }

    /**
     *
     */
    private void spielBeenden() {
        protokollant.printAs("Das Spiel ist beendet!");
        Spieler barSieger = teilnehmer.stream().max(Comparator.comparingInt(Entity::getGuthaben)).get();
        protokollant.printAs("Der Sieger mit dem höchsten Bargeldbestand ist: " + barSieger.getName());

        Map<Spieler, Integer> besitzverhältnisse = new HashMap<>();

        for (Spieler spieler : teilnehmer) {
            int sum = spielbrett.stream().filter(feld -> feld.istVomTyp(FeldTypen.IMMOBILIENFELD)).filter(feld -> {
                ImmobilienFeld immobilienFeld = (ImmobilienFeld) feld;
                return immobilienFeld.getBesitzer() == spieler;
            }).mapToInt(feld -> {
                ImmobilienFeld immobilienFeld = (ImmobilienFeld) feld;
                return immobilienFeld.berechneGrundwert();
            }).sum() + spieler.getGuthaben();
            besitzverhältnisse.put(spieler, sum);
        }
        Spieler gesamtSieger = teilnehmer.get(1);

        for (Spieler spieler : besitzverhältnisse.keySet()) {
            if (besitzverhältnisse.get(gesamtSieger) < besitzverhältnisse.get(spieler)) {
                gesamtSieger = spieler;
            }
        }

        protokollant.printAs("Der Gesamtsieger ist : " + gesamtSieger);

    }

    /**
     * Führt die nächste Gemeinschaftskarte aus und legt die gezogene Karte
     * wieder unter den Stapel.
     */
    public void naechsteGemeinschaftskarte() {
        Karte k = this.gemeinschaftsKarten.peek();
        k.fuehreKartenAktionAus(this);
        protokollant.printAs("Gezogene Karte: " + k.getClass().toString());
        this.gemeinschaftsKarten.add(this.gemeinschaftsKarten.poll());
    }

    /**
     * Führt die nächste Ereigniskarte aus und legt die gezogene Karte
     * wieder unter den Stapel.
     */
    public void naechsteEreigniskarte() {
        Karte k = this.ereignisKarten.peek();
        k.fuehreKartenAktionAus(this);
        protokollant.printAs("Gezogene Karte: " + k.getClass().toString());
        this.ereignisKarten.add(this.ereignisKarten.poll());
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
