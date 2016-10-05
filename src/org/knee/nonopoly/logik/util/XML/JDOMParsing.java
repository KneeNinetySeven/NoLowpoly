package org.knee.nonopoly.logik.util.XML;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.knee.nonopoly.felder.abstracts.KartenFeld;
import org.knee.nonopoly.felder.implementations.immobilien.Bahnhof;
import org.knee.nonopoly.felder.implementations.immobilien.Werk;
import org.knee.nonopoly.felder.implementations.kartenFelder.EreignisFeld;
import org.knee.nonopoly.felder.implementations.kartenFelder.GemeinschaftsFeld;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adrian on 05.10.2016.
 * TODO: Index mit einlesen
 * TODO: XML-Validierung
 * TODO: Exception-Handling
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
        this.bahnhoefeAnlegen();
        this.werkeAnlegen();
        this.ereignisFelderAnlegen();
        this.gemeinschaftsFelderAnlegen();
    }

    private List<Bahnhof> bahnhoefeAnlegen() {
        List<Bahnhof> bahnhofListe = new ArrayList<>();
        List<Element> bahnhoefe = this.root.getChild("Bahnhoefe").getChildren("Bahnhof");
        for (Element current : bahnhoefe) {
            String name = current.getChild("Name").getText();
            int kaufpreis = Integer.parseInt(current.getChildText("Preis"));
            List<Integer> mietstaffel = new ArrayList<>();
            current.getChild("Mietpreise").getChildren("Miete").forEach(element -> {
                mietstaffel.add(Integer.parseInt(element.getText()));
            });
            bahnhofListe.add(new Bahnhof(name, kaufpreis, mietstaffel));
        }
        bahnhofListe.forEach(System.out::println);
        return bahnhofListe;
    }

    private List<Werk> werkeAnlegen() {
        List<Werk> werkListe = new ArrayList<>();
        List<Element> werke = this.root.getChild("Werke").getChildren("Werk");
        for (Element current : werke) {
            String name = current.getChild("Name").getText();
            int kaufpreis = Integer.parseInt(current.getChildText("Preis"));
            List<Integer> faktoren = new ArrayList<>();
            current.getChild("Mietpreise").getChildren("Miete").forEach(element -> {
                faktoren.add(Integer.parseInt(element.getText()));
            });
            werkListe.add(new Werk(name, kaufpreis, faktoren));
        }
        werkListe.forEach(System.out::println);
        return werkListe;
    }

    private List<KartenFeld> ereignisFelderAnlegen() {
        List<KartenFeld> ereignisFeldListe = new ArrayList<>();
        List<Element> ereignisfelder = this.root.getChild("Ereignisfelder").getChildren("Ereignisfeld");
        for (Element current : ereignisfelder) {
            String name = current.getChildText("Name");
            ereignisFeldListe.add(new EreignisFeld(name));
        }
        ereignisFeldListe.forEach(System.out::println);
        return ereignisFeldListe;
    }

    private List<KartenFeld> gemeinschaftsFelderAnlegen() {
        List<KartenFeld> gemeinschaftsFeldListe = new ArrayList<>();
        List<Element> gemeinschaftsfelder = this.root.getChild("Gemeinschaftsfelder").getChildren("Gemeinschaftsfeld");
        for (Element current : gemeinschaftsfelder) {
            String name = current.getChildText("Name");
            gemeinschaftsFeldListe.add(new GemeinschaftsFeld(name));
        }
        gemeinschaftsFeldListe.forEach(System.out::println);
        return gemeinschaftsFeldListe;
    }
}
