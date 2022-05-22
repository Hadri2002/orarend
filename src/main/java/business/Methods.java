package business;

import business.os.Tantargy;
import fio.Fio;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.util.ArrayList;

import static business.os.Tantargy.felvettFajlnev;
import static business.os.Tantargy.teljesitettFajlnev;

public class Methods {

    public static void felvehetoKiiratas (ArrayList<FelvehetoTantargy> felvehetoTargyak) {
        for(FelvehetoTantargy targy: felvehetoTargyak) {
            boolean tantargyLetezik = false;
            for(Tantargy targy2 : Fio.felvettTantargyak){
                if(targy.getNev().equals(targy2.getNev())){
                    tantargyLetezik = true;
                    break;
                }
                for(TeljesitettTantargy targy3 : Fio.teljesitettTantargyak) {
                    if(targy.getNev().equals(targy3.getNev())){
                        tantargyLetezik = true;
                        break;
                    }
                }
            }
            if(tantargyLetezik == false){
                System.out.println(targy);
            }

        }

    }

    public static void felvettKiiratas (ArrayList<Tantargy> targyak) {
            for(Tantargy targy: targyak) {
                System.out.println(targy);
            }
    }

    public static void teljesitettKiiratas (ArrayList<TeljesitettTantargy> targyak) {
        for(TeljesitettTantargy targy: targyak) {
            System.out.println(targy);
        }
    }

    public static void tantargyFelvetel(String felvenniKivantTargy) {
        boolean tantargyLetezik = false;

        for(FelvehetoTantargy tantargy: Fio.felvehetoTantargyak) {

            if(felvenniKivantTargy.equals(tantargy.getNev())) {
                tantargyLetezik = true;

                if(tantargy.getElofelteteles()) {
                    boolean elofeltetelTeljesitve = false;
                    for(TeljesitettTantargy teljesitett: Fio.teljesitettTantargyak) {
                        if(tantargy.getElofeltetel().equals(teljesitett.getNev())) {

                            elofeltetelTeljesitve = true;
                            break;
                        }
                    }
                    if(!elofeltetelTeljesitve) {
                        System.err.println("A tárgyat nem veheti fel, mivel nem teljesítette annak előfeltételét!");
                        return;
                    }
                }

                Fio.felvettTantargyak.add(tantargy);
                tantargy.mentes();
                System.out.println("Sikeresen felvette a tantárgyat!");
                break;
            }
        }

        if(!tantargyLetezik) {
            System.err.println("Nem létezik a beírt tantárgy!");
        }
    }

    public static void tantargyLeadas(String leadniKivantTargy){
        boolean tantargyLetezik = false;
        for(Tantargy targy : Fio.felvettTantargyak){
            if(targy.getNev().equals(leadniKivantTargy)){
                tantargyLetezik = true;
                try{
                    Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(felvettFajlnev);
                    Element rootElement = document.getDocumentElement();

                    NodeList childNodesList = rootElement.getChildNodes();
                    Node node;

                    for(int i = 0; i < childNodesList.getLength(); i++) {
                        node = childNodesList.item(i);
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            NodeList childNodesOfTantargyTag = node.getChildNodes();

                            String nev = "";
                            for(int j = 0; j < childNodesOfTantargyTag.getLength(); j++) {
                                if(childNodesOfTantargyTag.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                    switch(childNodesOfTantargyTag.item(j).getNodeName()) {
                                        case "nev":
                                            nev = childNodesOfTantargyTag.item(j).getTextContent();
                                            if(nev.equals(leadniKivantTargy)){
                                                rootElement.removeChild(node);
                                                Fio.felvettTantargyak.remove(targy);
                                                System.out.println("Sikeresen leadta a tantárgyat!");
                                                break;
                                            }
                                    }
                                }
                            }
                        }
                    }
                }
                catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }

        //Itt történik az xml fájlban a változtatások (törlés) mentése
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element rootElement = document.createElement("tantargyak");
            document.appendChild(rootElement);


            for (Tantargy targy : Fio.felvettTantargyak) {
                Element targyElement = document.createElement("tantargy");
                rootElement.appendChild(targyElement);

                createChildElement(document, targyElement, "nev", targy.getNev());
                createChildElement(document, targyElement, "kredit",String.valueOf(targy.getKredit()));
            }


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream(felvettFajlnev));

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);


        } catch (Exception e) {
            e.printStackTrace();
        }


        if(!tantargyLetezik) {
            System.err.println("Nem szerepel a beírt tantárgy a felvett tantárgyak között!");
        }
    }

    private static void createChildElement(Document document, Element parent, String tagName, String value) {
        Element element = document.createElement(tagName);
        element.setTextContent(value);
        parent.appendChild(element);
    }

}



