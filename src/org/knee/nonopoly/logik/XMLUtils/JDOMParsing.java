package org.knee.nonopoly.logik.XMLUtils;

import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.knee.nonopoly.entities.Steuertopf;
import org.knee.nonopoly.felder.*;
import org.knee.nonopoly.felder.immobilien.Bahnhof;
import org.knee.nonopoly.felder.immobilien.Strasse;
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
        felder.addAll(this.legeFreiParkenAn());
        felder.addAll(this.legeGefaengnisAn());
        felder.addAll(this.legeLosAn());
        felder.addAll(this.legePolizistAn());
        felder.sort((feld1, feld2) -> feld1.getIndex() - feld2.getIndex());
        return felder;
    }

    private List<Strasse> legeStrassenAn() throws DataConversionException {
        List<Strasse> strassenListe = new ArrayList<>();
        List<Element> strassen = this.strassenRoot.getChildren("Strasse");
        for (Element current : strassen) {
            int index = (current.getAttribute("index").getIntValue() - 1);
            String name = current.getChildText("Name");
            int kaufpreis = Integer.parseInt(current.getChildText("Preis"));
            List<Integer> mietstaffel = new ArrayList<>();
            current.getChild("Mietpreise").getChildren("Miete").forEach(element -> {
                mietstaffel.add(Integer.parseInt(element.getText()));
            });
            int hauspreis = Integer.parseInt(current.getChildText("Preis1Haus"));
            strassenListe.add(new Strasse(index, name, kaufpreis, mietstaffel, hauspreis));
        }
        return strassenListe;
    }

    private List<Polizist> legePolizistAn() throws DataConversionException {
        List<Polizist> polizisten = new ArrayList<>();
        Element polizist = this.nichtStrassenRoot.getChild("Polizist");
        int index = (polizist.getAttribute("index").getIntValue() - 1);
        String name = polizist.getChildText("Name");
        polizisten.add(new Polizist(index, name));
        return polizisten;
    }

    private List<FreiParken> legeFreiParkenAn() throws DataConversionException {
        List<FreiParken> freiParkens = new ArrayList<>();
        Element freiParken = this.nichtStrassenRoot.getChild("Freiparken");
        int index = (freiParken.getAttribute("index").getIntValue() - 1);
        String name = freiParken.getChildText("Name");
        freiParkens.add(new FreiParken(index, name));
        return freiParkens;
    }

    private List<Gefaengnis> legeGefaengnisAn() throws DataConversionException {
        List<Gefaengnis> gefaengnises = new ArrayList<>();
        Element gefaengnis = this.nichtStrassenRoot.getChild("Gefaengnis");
        int index = (gefaengnis.getAttribute("index").getIntValue() - 1);
        String name = gefaengnis.getChildText("Name");
        gefaengnises.add(new Gefaengnis(index, name));
        return gefaengnises;
    }

    private List<Los> legeLosAn() throws DataConversionException {
        List<Los> loses = new ArrayList<>();
        Element los = this.nichtStrassenRoot.getChild("Los");
        int index = (los.getAttribute("index").getIntValue() - 1);
        String name = los.getChildText("Name");
        int treffer = Integer.parseInt(los.getChildText("Treffer"));
        int ueberschreitung = Integer.parseInt(los.getChildText("Ueberschreitung"));
        loses.add(new Los(index, name, treffer, ueberschreitung));
        return loses;
    }

    private List<SteuerFeld> legeSteuerfelderAn() throws DataConversionException {
        List<SteuerFeld> steuerFeldListe = new ArrayList<>();
        List<Element> steuerfelder = this.nichtStrassenRoot.getChild("Steuerfelder").getChildren("Steuerfeld");
        for (Element current : steuerfelder) {
            int index = (current.getAttribute("index").getIntValue() - 1);
            String name = current.getChildText("Name");
            int steuer = Integer.parseInt(current.getChildText("Preis"));
            steuerFeldListe.add(new SteuerFeld(index, name, new Steuertopf(), steuer));
        }
        return steuerFeldListe;
    }

    private List<Bahnhof> legeBahnhoefeAn() throws DataConversionException {
        List<Bahnhof> bahnhofListe = new ArrayList<>();
        List<Element> bahnhoefe = this.nichtStrassenRoot.getChild("Bahnhoefe").getChildren("Bahnhof");
        for (Element current : bahnhoefe) {
            int index = (current.getAttribute("index").getIntValue() - 1);
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
            int index = (current.getAttribute("index").getIntValue() - 1);
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
            int index = (current.getAttribute("index").getIntValue() - 1);
            String name = current.getChildText("Name");
            ereignisFeldListe.add(new EreignisFeld(index, name));
        }
        return ereignisFeldListe;
    }

    private List<KartenFeld> legeGemeinschaftsfelderAn() throws DataConversionException {
        List<KartenFeld> gemeinschaftsFeldListe = new ArrayList<>();
        List<Element> gemeinschaftsfelder = this.nichtStrassenRoot.getChild("Gemeinschaftsfelder").getChildren("Gemeinschaftsfeld");
        for (Element current : gemeinschaftsfelder) {
            int index = (current.getAttribute("index").getIntValue() - 1);
            String name = current.getChildText("Name");
            gemeinschaftsFeldListe.add(new GemeinschaftsFeld(index, name));
        }
        return gemeinschaftsFeldListe;
    }
}