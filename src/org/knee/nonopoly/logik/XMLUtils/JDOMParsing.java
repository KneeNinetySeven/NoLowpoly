package org.knee.nonopoly.logik.XMLUtils;

import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.knee.nonopoly.entities.Steuertopf;
import org.knee.nonopoly.felder.*;
import org.knee.nonopoly.felder.immobilien.Bahnhof;
import org.knee.nonopoly.felder.immobilien.Strassen;
import org.knee.nonopoly.felder.immobilien.Werk;
import org.knee.nonopoly.felder.kartenFelder.EreignisFeld;
import org.knee.nonopoly.felder.kartenFelder.GemeinschaftsFeld;
import org.knee.nonopoly.felder.kartenFelder.KartenFeld;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adrian on 05.10.2016.
 * TODO: XML-Validierung
 * TODO: Exception-Handling
 * TODO: Strings externalisieren
 */
public class JDOMParsing {

    private String strassenPath;
    private String nichtStrassenPath;
    private Document strassenDoc;
    private Document nichtStrassenDoc;
    private Element strassenRoot;
    private Element nichtStrassenRoot;

    public JDOMParsing(String nichtStrassenPath, String strassenPath) throws JDOMException, IOException {
        this.strassenPath = strassenPath;
        this.nichtStrassenPath = nichtStrassenPath;
        this.strassenDoc = new SAXBuilder().build(strassenPath);
        this.nichtStrassenDoc = new SAXBuilder().build(nichtStrassenPath);
        this.strassenRoot = strassenDoc.getRootElement();
        this.nichtStrassenRoot = nichtStrassenDoc.getRootElement();
    }


    public List<Feld> legeSpielbrettAn() throws DataConversionException {
        List<Feld> felder = new ArrayList<>();
        felder.addAll(this.legeStrassenAn());
        felder.addAll(this.legeBahnhoefeAn());
        felder.addAll(this.legeWerkeAn());
        felder.addAll(this.legeGemeinschaftsfelderAn());
        felder.addAll(this.legeEreignisfelderAn());
        felder.addAll(this.legeSteuerfelderAn());
        felder.add(this.legeFreiParkenAn());
        felder.add(this.legeGefaengnisAn());
        felder.add(this.legeLosAn());
        felder.add(this.legePolizistAn());
        return felder;
    }

    private List<Strassen> legeStrassenAn() throws DataConversionException {
        List<Strassen> strassenListe = new ArrayList<>();
        List<Element> strassen = this.strassenRoot.getChildren("Strasse");
        for (Element current : strassen) {
            int index = current.getAttribute("index").getIntValue();
            String name = current.getChildText("Name");
            int kaufpreis = Integer.parseInt(current.getChildText("Preis"));
            List<Integer> mietstaffel = new ArrayList<>();
            current.getChild("Mietpreise").getChildren("Miete").forEach(element -> {
                mietstaffel.add(Integer.parseInt(element.getText()));
            });
            int hauspreis = Integer.parseInt(current.getChildText("Preis1Haus"));
            strassenListe.add(new Strassen(index, name, kaufpreis, mietstaffel, hauspreis));
        }
        return strassenListe;
    }

    private Polizist legePolizistAn() throws DataConversionException {
        Element polizist = this.nichtStrassenRoot.getChild("Polizist");
        int index = polizist.getAttribute("index").getIntValue();
        String name = polizist.getChildText("Name");
        return new Polizist(index, name);
    }

    private FreiParken legeFreiParkenAn() throws DataConversionException {
        Element freiParken = this.nichtStrassenRoot.getChild("Freiparken");
        int index = freiParken.getAttribute("index").getIntValue();
        String name = freiParken.getChildText("Name");
        return new FreiParken(index, name);
    }

    private Gefaengnis legeGefaengnisAn() throws DataConversionException {
        Element gefaengnis = this.nichtStrassenRoot.getChild("Gefaengnis");
        int index = gefaengnis.getAttribute("index").getIntValue();
        String name = gefaengnis.getChildText("Name");
        return new Gefaengnis(index, name);
    }

    private Los legeLosAn() throws DataConversionException {
        Element los = this.nichtStrassenRoot.getChild("Los");
        int index = los.getAttribute("index").getIntValue();
        String name = los.getChildText("Name");
        int treffer = Integer.parseInt(los.getChildText("Treffer"));
        int ueberschreitung = Integer.parseInt(los.getChildText("Ueberschreitung"));
        return new Los(index, name, treffer, ueberschreitung);
    }

    private List<SteuerFeld> legeSteuerfelderAn() throws DataConversionException {
        List<SteuerFeld> steuerFeldListe = new ArrayList<>();
        List<Element> steuerfelder = this.nichtStrassenRoot.getChild("Steuerfelder").getChildren("Steuerfeld");
        for (Element current : steuerfelder) {
            int index = current.getAttribute("index").getIntValue();
            String name = current.getChildText("Name");
            int steuer = Integer.parseInt(current.getChildText("Preis"));
            steuerFeldListe.add(new SteuerFeld(index, name, new Steuertopf(), steuer));
        }
        return steuerFeldListe;
//        TODO: Steuertopf richtig implementieren
    }

    private List<Bahnhof> legeBahnhoefeAn() throws DataConversionException {
        List<Bahnhof> bahnhofListe = new ArrayList<>();
        List<Element> bahnhoefe = this.nichtStrassenRoot.getChild("Bahnhoefe").getChildren("Bahnhof");
        for (Element current : bahnhoefe) {
            int index = current.getAttribute("index").getIntValue();
            String name = current.getChild("Name").getText();
            int kaufpreis = Integer.parseInt(current.getChildText("Preis"));
            List<Integer> mietstaffel = new ArrayList<>();
            current.getChild("Mietpreise").getChildren("Miete").forEach(element -> {
                mietstaffel.add(Integer.parseInt(element.getText()));
            });
            bahnhofListe.add(new Bahnhof(index, name, kaufpreis, mietstaffel));
        }
        return bahnhofListe;
    }

    private List<Werk> legeWerkeAn() throws DataConversionException {
        List<Werk> werkListe = new ArrayList<>();
        List<Element> werke = this.nichtStrassenRoot.getChild("Werke").getChildren("Werk");
        for (Element current : werke) {
            int index = current.getAttribute("index").getIntValue();
            String name = current.getChild("Name").getText();
            int kaufpreis = Integer.parseInt(current.getChildText("Preis"));
            List<Integer> faktoren = new ArrayList<>();
            current.getChild("Mietpreise").getChildren("Miete").forEach(element -> {
                faktoren.add(Integer.parseInt(element.getText()));
            });
            werkListe.add(new Werk(index, name, kaufpreis, faktoren));
        }
        return werkListe;
    }

    private List<KartenFeld> legeEreignisfelderAn() throws DataConversionException {
        List<KartenFeld> ereignisFeldListe = new ArrayList<>();
        List<Element> ereignisfelder = this.nichtStrassenRoot.getChild("Ereignisfelder").getChildren("Ereignisfeld");
        for (Element current : ereignisfelder) {
            int index = current.getAttribute("index").getIntValue();
            String name = current.getChildText("Name");
            ereignisFeldListe.add(new EreignisFeld(index, name));
        }
        return ereignisFeldListe;
    }

    private List<KartenFeld> legeGemeinschaftsfelderAn() throws DataConversionException {
        List<KartenFeld> gemeinschaftsFeldListe = new ArrayList<>();
        List<Element> gemeinschaftsfelder = this.nichtStrassenRoot.getChild("Gemeinschaftsfelder").getChildren("Gemeinschaftsfeld");
        for (Element current : gemeinschaftsfelder) {
            int index = current.getAttribute("index").getIntValue();
            String name = current.getChildText("Name");
            gemeinschaftsFeldListe.add(new GemeinschaftsFeld(index, name));
        }
        return gemeinschaftsFeldListe;
    }
}