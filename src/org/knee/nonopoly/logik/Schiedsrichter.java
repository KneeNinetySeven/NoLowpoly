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
        spielbrett = new ArrayList<Feld>();

        // Spieler
        this.teilnehmer = new ArrayList<Spieler>();
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

    private void spielbrettAnlegen() {
        try {
            spielbrett = jdomParser.legeSpielbrettAn();
        } catch (DataConversionException e) {
            e.printStackTrace();
        }

        spielbrett.forEach(feld -> {
            getProtokollant().printAs("Anlegen auf: " + getSpielbrett().indexOf(feld)
                    + " Index: " + feld.getIndex()
                    + " von: " + feld.getName());
        });
    }

    public void registriereTeilnehmer(String name, Strategie strategie) {
        this.teilnehmer.add(Spieler.spielerErzeugen(name, strategie));
    }

    public boolean spieleEinenSpielzug() {
        Spieler aktiverSpieler = teilnehmer.get(naechsterSpieler);
        Feld aktivesFeld = spielbrett.get(aktiverSpieler.getPosition());

        protokollant.printAs("Aktiver Spieler: " + aktiverSpieler.getName() + " [" + aktiverSpieler.getGuthaben() + "]");
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
                        + " (" + (aktivesFeld.getIndex() + 1) + ")");
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

    public boolean spieleEineRunde() {
        for (int zuegeBisEnde = teilnehmer.toArray().length - naechsterSpieler;
             zuegeBisEnde > 0; zuegeBisEnde--) {
            spieleEinenSpielzug();
        }
        return spielLäuftNoch();
    }

    public void spieleSpielZuEnde() {
        while (this.spieleEineRunde()) {
            // DO NOTHING
        }
    }

    public void zahleStartkapitalAus() {
        getBank().setGuthaben(200000);
        getTeilnehmer().forEach((Spieler s) -> {
            getBank().ueberweiseAn(30000, s);
        });
    }

    private boolean spielLäuftNoch() {
        int nochImSpiel = countSpielerImSpiel();
        return (nochImSpiel > 1) && (bank.getGuthaben() > 0);
    }

    private void bewegeSpieler() {
        Spieler aktiverSpieler = teilnehmer.get(naechsterSpieler);
        int neuePosition = aktiverSpieler.getPosition() + letzterWurf.getSum();

        if ((spielbrett.size() - 1) < neuePosition) {
            aktiverSpieler.setPosition(neuePosition - spielbrett.size() - 1);
            Los feld = (Los) spielbrett.get(0);
            bank.ueberweiseAn(feld.getUeberschreitung(), aktiverSpieler);
            getProtokollant().printAs(aktiverSpieler.getName() + " geht über Los und bekommt: " + feld.getUeberschreitung());
        } else {
            aktiverSpieler.setPosition(neuePosition);
        }
    }

    private int countSpielerImSpiel() {
        return Math.toIntExact(
                teilnehmer
                        .stream()
                        .filter((Spieler s) -> s.isImSpiel()).count());
    }

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
