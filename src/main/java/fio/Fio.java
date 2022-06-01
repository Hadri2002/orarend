package fio;

import business.FelvehetoTantargy;
import business.GetterFunctionName;
import business.OsztalyzatEnum;
import business.TeljesitettTantargy;
import business.os.Tantargy;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import static business.os.Tantargy.*;

public class Fio <T>{

    public static Integer felev = Fio.felevBeolvasas();
    public static ArrayList<FelvehetoTantargy> felvehetoTantargyak = Fio.beolvasasFelveheto();
    public static ArrayList<TeljesitettTantargy> teljesitettTantargyak = Fio.beolvasasTeljesitett();
    public static ArrayList<Tantargy> felvettTantargyak = Fio.beolvasFelvett();

    public static ArrayList<FelvehetoTantargy> beolvasasFelveheto() {

        ArrayList<FelvehetoTantargy> tantargyak = new ArrayList<>();

        try{
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(felvehetoFajlnev);
            Element rootElement = document.getDocumentElement();

            NodeList childNodesList = rootElement.getChildNodes();
            Node node;

            for(int i = 0; i < childNodesList.getLength(); i++) {
                node = childNodesList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList childNodesOfTantargyTag = node.getChildNodes();

                    String nev = "", kredit = "", elofelteteles = "", elofeltetel = "";
                    for(int j = 0; j < childNodesOfTantargyTag.getLength(); j++) {
                        if(childNodesOfTantargyTag.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            switch(childNodesOfTantargyTag.item(j).getNodeName()) {
                                case "nev": nev = childNodesOfTantargyTag.item(j).getTextContent(); break;
                                case "kredit": kredit = childNodesOfTantargyTag.item(j).getTextContent(); break;
                                case "elofelteteles": elofelteteles = childNodesOfTantargyTag.item(j).getTextContent(); break;
                                case "elofeltetel": elofeltetel = childNodesOfTantargyTag.item(j).getTextContent(); break;
                            }

                        }
                    }

                    FelvehetoTantargy tantargy = new FelvehetoTantargy(nev, Integer.parseInt(kredit), Boolean.valueOf(elofelteteles), elofeltetel);
                    tantargyak.add(tantargy);

                    }
                }

        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return tantargyak;
    }

    public static ArrayList<TeljesitettTantargy> beolvasasTeljesitett() {

        ArrayList<TeljesitettTantargy> tantargyak = new ArrayList<>();

        try{
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(teljesitettFajlnev);
            Element rootElement = document.getDocumentElement();

            NodeList childNodesList = rootElement.getChildNodes();
            Node node;

            for(int i = 0; i < childNodesList.getLength(); i++) {
                node = childNodesList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList childNodesOfTantargyTag = node.getChildNodes();

                    String nev = "", kredit = "", osztalyzat = "", felev = "";
                    for(int j = 0; j < childNodesOfTantargyTag.getLength(); j++) {
                        if(childNodesOfTantargyTag.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            switch(childNodesOfTantargyTag.item(j).getNodeName()) {
                                case "nev": nev = childNodesOfTantargyTag.item(j).getTextContent(); break;
                                case "kredit": kredit = childNodesOfTantargyTag.item(j).getTextContent(); break;
                                case "osztalyzat": osztalyzat = childNodesOfTantargyTag.item(j).getTextContent(); break;
                                case "felev": felev = childNodesOfTantargyTag.item(j).getTextContent(); break;
                            }
                        }
                    }

                    TeljesitettTantargy tantargy = new TeljesitettTantargy(nev, Integer.parseInt(kredit),
                            OsztalyzatEnum.valueOf(osztalyzat), Integer.parseInt(felev));
                    tantargyak.add(tantargy);

                }
            }

        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return tantargyak;
    }

    public static ArrayList<Tantargy> beolvasFelvett() {
        ArrayList<Tantargy> tantargyak = new ArrayList<>();

        try{
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(felvettFajlnev);
            Element rootElement = document.getDocumentElement();

            NodeList childNodesList = rootElement.getChildNodes();
            Node node;

            for(int i = 0; i < childNodesList.getLength(); i++) {
                node = childNodesList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList childNodesOfTantargyTag = node.getChildNodes();

                    String nev = "", kredit = "";
                    for(int j = 0; j < childNodesOfTantargyTag.getLength(); j++) {
                        if(childNodesOfTantargyTag.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            switch(childNodesOfTantargyTag.item(j).getNodeName()) {
                                case "nev": nev = childNodesOfTantargyTag.item(j).getTextContent(); break;
                                case "kredit": kredit = childNodesOfTantargyTag.item(j).getTextContent(); break;
                            }


                        }
                    }

                    Tantargy tantargy = new Tantargy(nev, Integer.parseInt(kredit));
                    tantargyak.add(tantargy);

                }
            }

        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return tantargyak;
    }

     public void mentes(T entity, String fajlnev) {

         Class clazz = entity.getClass();
         Class superclazz = clazz.getSuperclass();

         try {
             File f = new File(fajlnev);
             DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
             DocumentBuilder db = dbf.newDocumentBuilder();
             Document xml = db.parse(f);
             xml.setXmlStandalone(true);
             Element tantargy = xml.createElement("tantargy");

             Field[] osTulajdonsagok = superclazz.getDeclaredFields();
             for (Field tulajdonsag : osTulajdonsagok) {
                 if(tulajdonsag.getAnnotation(GetterFunctionName.class) != null){
                     String gfn = tulajdonsag.getAnnotation(GetterFunctionName.class).name();
                     Method gm = clazz.getMethod(gfn);
                     String ertek = gm.invoke(entity).toString();
                     String valtozoNev = tulajdonsag.getName();

                     Element elem = xml.createElement(valtozoNev);
                     elem.setTextContent(ertek);
                     tantargy.appendChild(elem);
                 }
             }

             Field[] tulajdonsagok = clazz.getDeclaredFields();
             for(Field tulajdonsag: tulajdonsagok) {
                 if(tulajdonsag.getAnnotation(GetterFunctionName.class) != null){
                     String gfn = tulajdonsag.getAnnotation(GetterFunctionName.class).name();
                     Method gm = clazz.getMethod(gfn);
                     String ertek = gm.invoke(entity).toString();
                     String valtozoNev = tulajdonsag.getName();

                     Element elem = xml.createElement(valtozoNev);
                     elem.setTextContent(ertek);
                     tantargy.appendChild(elem);
                 }
             }

             xml.getFirstChild().appendChild(tantargy);
             TransformerFactory tf = TransformerFactory.newInstance();
             Transformer t = tf.newTransformer();
             t.setOutputProperty(OutputKeys.STANDALONE, "yes");
             t.setOutputProperty(OutputKeys.INDENT, "yes");
             t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");


             DOMSource s = new DOMSource(xml);
             StreamResult r = new StreamResult(f);
             t.transform(s, r);

         }
         catch(Exception ex){
             System.out.println("Hiba: " + ex);
             System.out.println(Arrays.toString(ex.getStackTrace()));
         }
     }

     public static Integer felevBeolvasas() {
        Integer felev = -1;

         try {
             Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(felevFajlnev);
             Element rootElement = document.getDocumentElement();

             return Integer.parseInt(rootElement.getTextContent());

         }
         catch (ParserConfigurationException e) {
             e.printStackTrace();
         }
         catch (Exception e) {
             e.printStackTrace();
         }

        return felev;
     }

     public static void felevMentes(Integer felev) {
         try {
             Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(felevFajlnev);
             document.setXmlStandalone(true);
             Element rootElement = document.getDocumentElement();
             rootElement.setTextContent(felev.toString());

             Transformer transformer = TransformerFactory.newInstance().newTransformer();
             transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
             transformer.setOutputProperty(OutputKeys.INDENT, "yes");
             transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

             Result output = new StreamResult(new File(felevFajlnev));
             Source input = new DOMSource(document);

             transformer.transform(input, output);

         } catch (ParserConfigurationException e) {
             e.printStackTrace();
         } catch (Exception e) {
             e.printStackTrace();
         }
     }

     public static void xmlTorles(String fajlnev){
         try{
             Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fajlnev);
             Element rootElement = document.getDocumentElement();
             document.removeChild(rootElement);
             Node node = document.createElement("tantargyak");
             document.appendChild(node);

             Transformer transformer = TransformerFactory.newInstance().newTransformer();
             Result output = new StreamResult(new File(fajlnev));
             Source input = new DOMSource(document);

             transformer.transform(input, output);
         }
         catch (ParserConfigurationException ex) {
             ex.printStackTrace();
         }
         catch(Exception ex){
             ex.printStackTrace();
         }
     }

}
