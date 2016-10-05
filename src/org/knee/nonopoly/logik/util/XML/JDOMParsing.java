package org.knee.nonopoly.logik.util.XML;

import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.knee.nonopoly.entities.Steuertopf;
import org.knee.nonopoly.felder.abstracts.KartenFeld;
import org.knee.nonopoly.felder.implementations.*;
import org.knee.nonopoly.felder.implementations.immobilien.Bahnhof;
import org.knee.nonopoly.felder.implementations.immobilien.Werk;
import org.knee.nonopoly.felder.implementations.kartenFelder.EreignisFeld;
import org.knee.nonopoly.felder.implementations.kartenFelder.GemeinschaftsFeld;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adrian on 05.10.2016.
 * TODO: XML-Validierung
 * TODO: Exception-Handling
 *
 */
public class JDOMParsing {

    private String filepath;
    private Document doc;
    private Element root;

    public JDOMParsing(String filepath) throws JDOMException, IOException {
        this.filepath = filepath;
        this.doc = new SAXBuilder().build(filepath);
        this.root = doc.getRootElement();
    }

    public void dateiVerarbeiten() {
        try {
            this.legeBahnhoefeAn().forEach(System.out::println);
            this.legeWerkeAn().forEach(System.out::println);
            this.legeEreignisfelderAn().forEach(System.out::println);
            this.legeGemeinschaftsfelderAn().forEach(System.out::println);
            this.legeSteuerfelderAn().forEach(System.out::println);
            System.out.println(this.legeLosAn());
            System.out.println(this.legeGefaengnisAn());
            System.out.println(this.legeFreiParkenAn());
            System.out.println(this.legePolizistAn());
        } catch (DataConversionException e) {
            e.printStackTrace();
        }

    }

    private Polizist legePolizistAn() throws DataConversionException {
        Element polizist = this.root.getChild("Polizist");
        int index = polizist.getAttribute("index").getIntValue();
        String name = polizist.getChildText("Name");
        return new Polizist(index, name);
    }

    private FreiParken legeFreiParkenAn() throws DataConversionException {
        Element freiParken = this.root.getChild("Freiparken");
        int index = freiParken.getAttribute("index").getIntValue();
        String name = freiParken.getChildText("Name");
        return new FreiParken(index, name);
    }

    private Gefängnis legeGefaengnisAn() throws DataConversionException {
        Element gefaengnis = this.root.getChild("Gefaengnis");
        int index = gefaengnis.getAttribute("index").getIntValue();
        String name = gefaengnis.getChildText("Name");
        return new Gefängnis(index, name);
    }

    private Los legeLosAn() throws DataConversionException {
        Element los = this.root.getChild("Los");
        int index = los.getAttribute("index").getIntValue();
        String name = los.getChildText("Name");
        int treffer = Integer.parseInt(los.getChildText("Treffer"));
        int ueberschreitung = Integer.parseInt(los.getChildText("Ueberschreitung"));
        return new Los(index, name, treffer, ueberschreitung);
    }

    private List<SteuerFeld> legeSteuerfelderAn() throws DataConversionException {
        List<SteuerFeld> steuerFeldListe = new ArrayList<>();
        List<Element> steuerfelder = this.root.getChild("Steuerfelder").getChildren("Steuerfeld");
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
        List<Element> bahnhoefe = this.root.getChild("Bahnhoefe").getChildren("Bahnhof");
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
        List<Element> werke = this.root.getChild("Werke").getChildren("Werk");
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
        List<Element> ereignisfelder = this.root.getChild("Ereignisfelder").getChildren("Ereignisfeld");
        for (Element current : ereignisfelder) {
            int index = current.getAttribute("index").getIntValue();
            String name = current.getChildText("Name");
            ereignisFeldListe.add(new EreignisFeld(index, name));
        }
        return ereignisFeldListe;
    }

    private List<KartenFeld> legeGemeinschaftsfelderAn() throws DataConversionException {
        List<KartenFeld> gemeinschaftsFeldListe = new ArrayList<>();
        List<Element> gemeinschaftsfelder = this.root.getChild("Gemeinschaftsfelder").getChildren("Gemeinschaftsfeld");
        for (Element current : gemeinschaftsfelder) {
            int index = current.getAttribute("index").getIntValue();
            String name = current.getChildText("Name");
            gemeinschaftsFeldListe.add(new GemeinschaftsFeld(index, name));
        }
        return gemeinschaftsFeldListe;
    }
}
