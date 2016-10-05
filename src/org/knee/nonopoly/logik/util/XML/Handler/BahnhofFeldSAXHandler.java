package org.knee.nonopoly.logik.util.XML.Handler;

import jdk.internal.org.xml.sax.Locator;
import org.knee.nonopoly.felder.implementations.immobilien.Bahnhof;
import org.xml.sax.ContentHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nils on 30.09.2016.
 * Achtung, nicht funktionsfaehig: Code-Zeilen auskommentiert, um Fehler zu unterdrücken.
 */
public class BahnhofFeldSAXHandler implements ContentHandler {

    private List<Bahnhof> bahnhoefe = new ArrayList<Bahnhof>();
    private Bahnhof bahnhof;
    private int bahnhofPos;
//    private String currentVal;

    private String name;
    private int kaufpreis;
    private List<Integer> mietstaffel;

    private boolean bName;
    private boolean bKaufpreis;
    private boolean bMietpreise;

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
        for(Bahnhof element : this.bahnhoefe){
            System.out.println(element);
        }
    }

    @Override
    public void startPrefixMapping(String s, String s1)  {

    }

    @Override
    public void endPrefixMapping(String s) {

    }

    @Override
    public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes atts) {
        if(localName.equalsIgnoreCase("Bahnhof")) {
            bahnhofPos = Integer.parseInt(atts.getValue("index"));
        } else if(localName.equalsIgnoreCase("Name")){
            bName = true;
        } else if(localName.equalsIgnoreCase("Preis")){
            bKaufpreis = true;
        } else if(localName.equalsIgnoreCase("Mietpreise")){
            bMietpreise = true;
        }


//        if (localName.equalsIgnoreCase("Bahnhof")) {
//            //bahnhof = null;
//            bahnhofPos = Integer.parseInt(atts.getValue("index"));
//            System.out.println("Bahnhof: " + bahnhofPos );
//        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if(localName.equalsIgnoreCase("Bahnhof")){
//            bahnhof = new Bahnhof(this.name, this.kaufpreis, this.mietstaffel);
//            bahnhoefe.add(bahnhof);
        }


//        if (localName.equalsIgnoreCase("Bahnhof")){
//            String name = "";
//            int preis = 0;
//            int index = 0;
//            int[] mieten = new int[4];
//            if (localName.equalsIgnoreCase("Name")) {
//                name = currentVal;
//                System.out.print("Name: " + name + "\t");
//            }
//
//            if (localName.equalsIgnoreCase("Preis")) {
//                preis = Integer.parseInt(currentVal);
//                System.out.print("Preis: " + name + "\t");
//            }
//
//            if (localName.equalsIgnoreCase("Miete")) {
//                mieten[index] = Integer.parseInt(currentVal);
//                index++;
//                System.out.print("Miete: " + name + "\t");
//            }
//            bahnhoefe.add(new Bahnhof(name, preis, mieten));
//            System.out.println("/ Bahnhof: " + bahnhofPos);
//        }
    }

    @Override
    public void characters(char[] chars, int start, int length)  {
        if(bName){
            this.name = new String(chars, start, length);
            bName = false;
        } else if(bKaufpreis){
            this.kaufpreis = Integer.parseInt(new String(chars, start, length));
            bKaufpreis = false;
        } else if(bMietpreise){
           // TODO: Implement
            String currentval = new String(chars, start, length);
            bMietpreise = false;
        }

//        currentVal = new String(chars, start, length);
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
