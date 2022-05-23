package business;

import business.os.Tantargy;
import fio.Fio;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
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
                    NodeList childElementsList = rootElement.getElementsByTagName("tantargy");
                    Node node = childElementsList.item(Fio.felvettTantargyak.indexOf(targy));
                    rootElement.removeChild(node);
                    //felmerül a kérdés, hogy ilyenkor a felvetttargyak arraylistből is távolítsuk-e el
                    System.out.println("Sikeresen leadta a tantárgyat!");

                    //változtatások mentése az xml fileba (elvileg szükséges, meglátjuk elhagyható-e) ->
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    Result output = new StreamResult(new File(felvettFajlnev));
                    Source input = new DOMSource(document);

                    transformer.transform(input, output);
                    break;

                }
                catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }

        if(!tantargyLetezik) {
            System.err.println("Nem szerepel a beírt tantárgy a felvett tantárgyak között!");
        }
    }
}



