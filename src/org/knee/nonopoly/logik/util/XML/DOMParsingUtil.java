package org.knee.nonopoly.logik.util.XML;

/**
 * Created by Nils on 29.09.2016.
 * <p>
 * Deprecated, JDOMParsing stattdessen verwenden
 * Komplett auskommentiert, da nicht benoetigt und um keine Fehler zu erzeugen.
 * TODO: Refactoring...viel zu viel doppelter Code
 *
 */
@Deprecated
public class DOMParsingUtil {

//    private String filePath;
//
//    private final DocumentBuilderFactory factory;
//    private final DocumentBuilder builder;
//    private Document doc;
//    private Element rootElement;
//
//    private List<Bahnhof> bahnhofListe;
//    private List<Werk> werkListe;
//    private List<GemeinschaftsFeld> gfListe;
//    private List<EreignisFeld> efListe;
//
//
//    public DOMParsingUtil(String filePath) throws IOException, SAXException, ParserConfigurationException {
//        this.filePath = filePath;
//        this.factory = DocumentBuilderFactory.newInstance();
//        this.builder = factory.newDocumentBuilder();
//        this.doc = builder.parse(new File(filePath));
//        this.rootElement = doc.getDocumentElement();
//
//        this.bahnhofListe = new ArrayList<>();
//        this.werkListe = new ArrayList<>();
//        this.gfListe = new ArrayList<>();
//        this.efListe = new ArrayList<>();
//
//    }
//
//    /**
//     * @throws ParserConfigurationException
//     * @throws SAXException
//     * @throws IOException
//     */
//    public void dateiVerarbeiten() throws ParserConfigurationException, SAXException, IOException {
////        this.bahnhoefeAnlegen();
////        this.werkeAnlegen();
//        this.gemeinschaftsfelderAnlegen();
//        this.ereignisfelderAnlegen();
//    }
//
////    private void bahnhoefeAnlegen() throws ParserConfigurationException, SAXException, IOException {
////        String name = "invalid";
////        int kaufpreis = 0;
////        List<Integer> mietstaffel = new int[4];
////
////
////        final NodeList bahnhofNodeList = rootElement.getElementsByTagName("Bahnhof");
////
////        for (int i = 0; i < bahnhofNodeList.getLength(); i++) {
////            Node bahnhofNode = bahnhofNodeList.item(i);
////
////            if (bahnhofNode.getNodeType() == Node.ELEMENT_NODE) {
////                Element bahnhofElement = (Element) bahnhofNode;
////                name = bahnhofElement.getElementsByTagName("Name").item(0).getTextContent();
////                kaufpreis = Integer.parseInt(bahnhofElement.getElementsByTagName("Preis").item(0).getTextContent());
////                final NodeList mietpreisNodeList = bahnhofElement.getElementsByTagName("Mietpreise");
////
////                for (int j = 0; j < mietpreisNodeList.getLength(); j++) {
////                    Node mietpreisNode = mietpreisNodeList.item(j);
////
////                    if (mietpreisNode.getNodeType() == Node.ELEMENT_NODE) {
////                        Element mieteElement = (Element) mietpreisNode;
////
////                        for (int k = 0; k < mieteElement.getElementsByTagName("Miete").getLength(); k++) {
////                            mietstaffel.set(k, Integer.parseInt(mieteElement.getElementsByTagName("Miete").item(k).getTextContent()));
////                        }
////                    }
////                }
////            }
////            this.bahnhofListe.add(new Bahnhof(name, kaufpreis, mietstaffel));
////            mietstaffel = new int[4]; //Array leeren, ohne funktioniert es nicht... (warum eigentlich nicht?)
////        }
////        bahnhofListe.forEach(System.out::println);
////    }
//
////    private void werkeAnlegen() throws ParserConfigurationException, SAXException, IOException {
////        String name = "invalid";
////        int kaufpreis = 0;
////        int[] faktoren = new int[2];
////
////        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
////        DocumentBuilder builder = factory.newDocumentBuilder();
////        Document doc = builder.parse(new File(filePath));
////        Element rootElement = doc.getDocumentElement();
////
////        final NodeList werkNodeList = rootElement.getElementsByTagName("Werk");
////
////        for (int i = 0; i < werkNodeList.getLength(); i++) {
////            Node werkNode = werkNodeList.item(i);
////
////            if (werkNode.getNodeType() == Node.ELEMENT_NODE) {
////                Element werkElement = (Element) werkNode;
////                name = werkElement.getElementsByTagName("Name").item(0).getTextContent();
////                kaufpreis = Integer.parseInt(werkElement.getElementsByTagName("Preis").item(0).getTextContent());
////                final NodeList mietpreisNodeList = werkElement.getElementsByTagName("Mietpreise");
////
////                for (int j = 0; j < mietpreisNodeList.getLength(); j++) {
////                    Node mietpreisNode = mietpreisNodeList.item(j);
////
////                    if (mietpreisNode.getNodeType() == Node.ELEMENT_NODE) {
////                        Element mieteElement = (Element) mietpreisNode;
////
////                        for (int k = 0; k < mieteElement.getElementsByTagName("Miete").getLength(); k++) {
////                            faktoren[k] = Integer.parseInt(mieteElement.getElementsByTagName("Miete").item(k).getTextContent());
////                        }
////                    }
////                }
////            }
////            this.werkListe.add(new Werk(name, kaufpreis, faktoren));
////            faktoren = new int[2]; //Array leeren, ohne funktioniert es nicht... (warum eigentlich nicht?)
////        }
////        werkListe.forEach(System.out::println);
////    }
//
//    private void gemeinschaftsfelderAnlegen() {
//        String name = "invalid";
//
//        final NodeList gfNodeList = rootElement.getElementsByTagName("Gemeinschaftsfeld");
//
//        for (int i = 0; i < gfNodeList.getLength(); i++) {
//            Node gfNode = gfNodeList.item(i);
//            if (gfNode.getNodeType() == Node.ELEMENT_NODE) {
//                Element werkElement = (Element) gfNode;
//                name = werkElement.getElementsByTagName("Name").item(0).getTextContent();
//                this.gfListe.add(new GemeinschaftsFeld(name));
//            }
//            gfListe.forEach(System.out::println);
//        }
//
//    }
//
//    private void ereignisfelderAnlegen() {
//        String name = "invalid";
//
//        final NodeList gfNodeList = rootElement.getElementsByTagName("Ereignisfeld");
//
//        for (int i = 0; i < gfNodeList.getLength(); i++) {
//            Node gfNode = gfNodeList.item(i);
//            if (gfNode.getNodeType() == Node.ELEMENT_NODE) {
//                Element werkElement = (Element) gfNode;
//                name = werkElement.getElementsByTagName("Name").item(0).getTextContent();
//                this.efListe.add(new EreignisFeld(name));
//            }
//            efListe.forEach(System.out::println);
//        }
//    }
}
