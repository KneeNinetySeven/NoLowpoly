package org.knee.nonopoly.logik.util.XML.Handler;

import jdk.internal.org.xml.sax.Locator;
import org.knee.nonopoly.felder.implementations.immobilien.Bahnhof;
import org.xml.sax.ContentHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nils on 30.09.2016.
 */
public class BahnhofFeldSAXHandler implements ContentHandler {

    private List<Bahnhof> bahnhoefe = new ArrayList<Bahnhof>();
    private Bahnhof bahnhof;
    private int bahnhofPos;
    private String currentVal;

    public BahnhofFeldSAXHandler() {
    }


    public void setDocumentLocator(Locator locator) {

    }

    @Override
    public void setDocumentLocator(org.xml.sax.Locator locator) {

    }

    @Override
    public void startDocument() {

    }

    @Override
    public void endDocument() {

    }

    @Override
    public void startPrefixMapping(String s, String s1)  {

    }

    @Override
    public void endPrefixMapping(String s) {

    }

    @Override
    public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes atts) {
        if (localName.equalsIgnoreCase("Bahnhof")) {
            //bahnhof = null;
            bahnhofPos = Integer.parseInt(atts.getValue("index"));
            System.out.println("Bahnhof: " + bahnhofPos );
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (localName.equalsIgnoreCase("Bahnhof")){
            String name = "";
            int preis = 0;
            int index = 0;
            int[] mieten = new int[4];
            if (localName.equalsIgnoreCase("Name")) {
                name = currentVal;
                System.out.print("Name: " + name + "\t");
            }

            if (localName.equalsIgnoreCase("Preis")) {
                preis = Integer.parseInt(currentVal);
                System.out.print("Preis: " + name + "\t");
            }

            if (localName.equalsIgnoreCase("Miete")) {
                mieten[index] = Integer.parseInt(currentVal);
                index++;
                System.out.print("Miete: " + name + "\t");
            }
            bahnhoefe.add(new Bahnhof(name, preis, mieten));
            System.out.println("/ Bahnhof: " + bahnhofPos);
        }
    }

    @Override
    public void characters(char[] chars, int start, int length)  {
        currentVal = new String(chars, start, length);
    }

    @Override
    public void ignorableWhitespace(char[] chars, int i, int i1)  {

    }

    @Override
    public void processingInstruction(String s, String s1)  {

    }

    @Override
    public void skippedEntity(String s)  {

    }

}
