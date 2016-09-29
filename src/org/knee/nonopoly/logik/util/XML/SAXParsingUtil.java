package org.knee.nonopoly.logik.util.XML;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by Nils on 29.09.2016.
 * <p>
 * <b>Inoperable</b>
 */

public class SAXParsingUtil {

    private String filePath;

    private class SpielFeldHandler extends DefaultHandler {
        private SpielFeldHandler() throws ParserConfigurationException, SAXException {
        }

        @Override
        public void startDocument() {
            System.out.println("Document starts.");
        }

        @Override
        public void endDocument() {
            System.out.println("Document ends.");
        }

        @Override
        public void startElement(String namespaceURI, String localName,
                                 String qName, Attributes atts) {
            System.out.println("namespaceURI: " + namespaceURI);
            System.out.println("localName: " + localName);
            System.out.println("qName: " + qName);
            for (int i = 0; i < atts.getLength(); i++)
                System.out.printf("Attribut no. %d: %s = %s%n", i,
                        atts.getQName(i), atts.getValue(i));
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            System.out.println("Characters:");

            for (int i = start; i < (start + length); i++)
                System.out.printf("%1$c (%1$x) ", (int) ch[i]);

            System.out.println();
        }
    }

    public SAXParsingUtil(String filePath) {
        this.filePath = filePath;
    }

    public void dateiVerarbeiten() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        DefaultHandler handler = new SpielFeldHandler();
        saxParser.parse(new File(this.filePath), handler);
    }
}
