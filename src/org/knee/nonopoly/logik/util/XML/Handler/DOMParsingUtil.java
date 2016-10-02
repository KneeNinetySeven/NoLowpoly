package org.knee.nonopoly.logik.util.XML.Handler;

import org.knee.nonopoly.felder.implementations.immobilien.Bahnhof;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nils on 29.09.2016.
 * <p>
 * <b>Inoperable</b>
 */

public class DOMParsingUtil {

    private String filePath;
    private List<Bahnhof> bahnhofListe;


    public DOMParsingUtil(String filePath) {
        this.filePath = filePath;
        this.bahnhofListe = new ArrayList<>();

        try {
            this.dateiVerarbeiten();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dateiVerarbeiten() throws ParserConfigurationException, SAXException, IOException {
        String name = "";
        int kaufpreis = 0;
        int[] mietstaffel = new int[4];

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(new File(filePath));

        Element root = doc.getDocumentElement();
        final NodeList bahnhofNodeList = root.getElementsByTagName("Bahnhof");

        for (int i = 0; i < bahnhofNodeList.getLength(); i++) {
            Node bahnhofNode = bahnhofNodeList.item(i);
            if (bahnhofNode.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) bahnhofNode;
                name = e.getElementsByTagName("Name").item(0).getTextContent();
                kaufpreis = Integer.parseInt(e.getElementsByTagName("Preis").item(0).getTextContent());

                final NodeList mietpreisNodeList = e.getElementsByTagName("Mietpreise");
                for (int j = 0; j < mietpreisNodeList.getLength(); j++) {
                    Node mietpreisNode = mietpreisNodeList.item(j);
                    if (mietpreisNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element mieteElement = (Element) mietpreisNode;
                         for (int k = 0; k < mieteElement.getElementsByTagName("Miete").getLength(); k++) {
                            mietstaffel[k] = Integer.parseInt(mieteElement.getElementsByTagName("Miete").item(k).getTextContent());
                        }
                    }
                }
            }
            bahnhofListe.add(new Bahnhof(name, kaufpreis, mietstaffel));
            mietstaffel = new int[4]; //Array leeren, ohne funktioniert es nicht... (warum eigentlich nicht?)
        }
        bahnhofListe.forEach(System.out::println);

    }
}
