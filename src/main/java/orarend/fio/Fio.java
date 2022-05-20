package orarend.fio;

import orarend.business.FelvehetoTantargy;
import orarend.business.GetterFunctionName;
import orarend.business.os.Tantargy;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import static orarend.business.os.Tantargy.felvehetoFajlnev;

public class Fio <T>{

     /*public void beolvasas(T entity) {
         Class clazz = entity.getClass();
         Class superclazz = clazz.getSuperclass();
         Field[] tulajdonsagok = clazz.getDeclaredFields();
         Field[] tulajdonsagok2 = superclazz.getDeclaredFields();

         ArrayList<T> tantargyak = new ArrayList<>();

         try{
             Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fajlnev);
             Element rootElement = document.getDocumentElement();

             NodeList childNodesList = rootElement.getChildNodes();
             Node node;

             for(int i = 0; i < childNodesList.getLength(); i++) {
                 node = childNodesList.item(i);
                 if (node.getNodeType() == Node.ELEMENT_NODE) {
                     NodeList childNodesOfTantargyTag = node.getChildNodes();

                     for(int j = 0; j < childNodesOfTantargyTag.getLength(); j++) {
                        if(childNodesOfTantargyTag.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            //itt kéne megnézni hogy melyik tulajdonságot nézem éppen
                            for(Field tulajdonsag: tulajdonsagok2) {
                                if(childNodesOfTantargyTag.item(j).getNodeName().equals(tulajdonsag.getName())) {

                                }
                            }
                            for(Field tulajdonsag: tulajdonsagok) {

                                //megnézem hogy egyezik e a node neve a field nevével?
                                //ha igen akkor lementem a tulajonságot?
                                //amiből utána később létrehozom az objektumot
                            }
                        }
                     }
                 }
             }


         }
         catch (ParserConfigurationException e) {
             e.printStackTrace();
         }
         catch (Exception e) {
             e.printStackTrace();
         }

     }
    */

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

     public void mentes(T entity) {

         Class clazz = entity.getClass();
         Class superclazz = clazz.getSuperclass();

         try {
             File f = new File(Tantargy.felvehetoFajlnev);
             DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
             DocumentBuilder db = dbf.newDocumentBuilder();
             Document xml = db.parse(f);
             Element tantargy = xml.createElement("tantargy");

             Field[] tulajdonsagok2 = superclazz.getDeclaredFields();
             for (Field tul : tulajdonsagok2) {
                 if(tul.getAnnotation(GetterFunctionName.class) != null){
                     String gfn = tul.getAnnotation(GetterFunctionName.class).name();
                     //előállítani a getter metódust:
                     Method gm = clazz.getMethod(gfn);
                     //Meghívni az entityre a method-ot:
                     String ertek = gm.invoke(entity).toString();
                     String valtozoNev = tul.getName();
                     Element elem = xml.createElement(valtozoNev);
                     elem.setTextContent(ertek);
                     tantargy.appendChild(elem);
                 }
             }

             Field[] tulajdonsagok = clazz.getDeclaredFields();
             for(Field tul: tulajdonsagok) {
                 if(tul.getAnnotation(GetterFunctionName.class) != null){
                     String gfn = tul.getAnnotation(GetterFunctionName.class).name();
                     //előállítani a getter metódust:
                     Method gm = clazz.getMethod(gfn);
                     //Meghívni az entityre a method-ot:
                     String ertek = gm.invoke(entity).toString();
                     String valtozoNev = tul.getName();
                     Element elem = xml.createElement(valtozoNev);
                     elem.setTextContent(ertek);
                     tantargy.appendChild(elem);
                 }
             }

             //tantargy.setAttribute("class", clazz.getSimpleName());
             xml.getFirstChild().appendChild(tantargy);
             TransformerFactory tf = TransformerFactory.newInstance();
             Transformer t = tf.newTransformer();
             DOMSource s = new DOMSource(xml);
             StreamResult r = new StreamResult(f);
             t.transform(s, r);

         }
         catch(Exception ex){
             System.out.println("Hiba: " + ex.toString());
             System.out.println(Arrays.toString(ex.getStackTrace()));
         }
     }
}
