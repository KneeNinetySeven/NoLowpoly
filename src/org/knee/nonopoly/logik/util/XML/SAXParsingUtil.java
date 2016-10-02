package org.knee.nonopoly.logik.util.XML;

import org.knee.nonopoly.logik.util.XML.Handler.BahnhofFeldSAXHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Nils on 29.09.2016.
 * <p>
 * <b>Inoperable</b>
 */

public class SAXParsingUtil {

    private String filePath;
    private BahnhofFeldSAXHandler bhfFeldSaxHandler = new BahnhofFeldSAXHandler();

    public SAXParsingUtil(String filePath) {
        this.filePath = filePath;
    }

    public void dateiVerarbeiten() throws ParserConfigurationException, SAXException, IOException {
        XMLReader xmlReader = XMLReaderFactory.createXMLReader();

        FileReader reader = new FileReader(filePath);
        InputSource inputSource = new InputSource(reader);

        xmlReader.setContentHandler(bhfFeldSaxHandler);
        xmlReader.parse(inputSource);



    }
}
