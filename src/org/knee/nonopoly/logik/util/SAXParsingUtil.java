package org.knee.nonopoly.logik.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Nils on 29.09.2016.
 *
 * <b>BROKEN</b>
 *
 */
@Deprecated
public class SAXParsingUtil {

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
        public void characters( char[] ch, int start, int length )
        {
            System.out.println( "Characters:" );

            for ( int i = start; i < (start + length); i++ )
                System.out.printf( "%1$c (%1$x) ", (int) ch[i] );

            System.out.println();
        }
    }

    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser saxParser = factory.newSAXParser();
    DefaultHandler handler = new SpielFeldHandler();
    saxParser.parse( new File("party.xml"), handler )

}
