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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static business.os.Tantargy.felvettFajlnev;

public class Methods {

    private static Scanner scn = new Scanner(System.in);
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static void felvehetoKiiratas (ArrayList<FelvehetoTantargy> felvehetoTargyak) {
        for(FelvehetoTantargy targy: felvehetoTargyak) {
            boolean tantargyLetezik = false; //false, ha ki kéne írni!
            for(Tantargy targy2 : Fio.felvettTantargyak){
                if(targy.getNev().equals(targy2.getNev())) {
                    tantargyLetezik = true;
                    break;
                }
            }
            for(TeljesitettTantargy targy3 : Fio.teljesitettTantargyak) { //akkor legyen true, ha benne van a teljesítettek között és nem egyest kapott?
                if(targy.getNev().equals(targy3.getNev()) && targy3.getOsztalyzat().getErtek() != 1){
                    tantargyLetezik = true;
                    break;
                }
            }
            if(!tantargyLetezik){
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

            if(felvenniKivantTargy.equals(tantargy.getNev())) { //tehát létezik a tantárgy a listázottak között
                tantargyLetezik = true;

                //meg kell nézni, hogy teljesítettük-e már!

                for(TeljesitettTantargy teljesitett: Fio.teljesitettTantargyak) {
                    if(teljesitett.getNev().equals(felvenniKivantTargy) && teljesitett.getOsztalyzat().getErtek() != 1) { //azaz ha már teljesítettük és nem buktunk meg belőle
                        System.err.println("\r\nA tantárgyat már teljesítette, így nem veheti fel mégegyszer!\r\n");
                        return;
                    }
                }

                //és ha nem, akkor felvettük-e már?

                for(Tantargy felvett: Fio.felvettTantargyak) {
                    if(felvett.getNev().equals(felvenniKivantTargy)) {
                        System.err.println("\r\nA tárgyat már felvette, így nem veheti fel mégegyszer!\r\n");
                        return;
                    }
                }

                if(tantargy.getElofelteteles()) {
                    boolean elofeltetelTeljesitve = false;
                    for(TeljesitettTantargy teljesitett: Fio.teljesitettTantargyak) {
                        if(tantargy.getElofeltetel().equals(teljesitett.getNev())) {

                            elofeltetelTeljesitve = true;
                            break;
                        }
                    }
                    if(!elofeltetelTeljesitve) {
                        System.err.println("\r\nA tárgyat nem veheti fel, mivel nem teljesítette annak előfeltételét!\r\n");
                        return;
                    }
                }

                Tantargy felvettTantargy = new Tantargy(tantargy.getNev(), tantargy.getKredit());
                Fio.felvettTantargyak.add(felvettTantargy);
                felvettTantargy.mentes();
                System.out.println("\r\nSikeresen felvette a tantárgyat!\r\n");
                break;
            }
        }

        if(!tantargyLetezik) {
            System.err.println("\r\nNem létezik a beírt tantárgy!\r\n");
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
                    Fio.felvettTantargyak.remove(targy);

                    System.out.println("\r\nSikeresen leadta a tantárgyat!\r\n");

                    //változtatások mentése az xml fileba
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
            System.err.println("\r\nNem szerepel a beírt tantárgy a felvett tantárgyak között!\r\n");
        }
    }

    public static void felevLepes() {

        //végig kéne menni a felvett tantárgyakon
        for(Tantargy targy : Fio.felvettTantargyak){
            //while-ba kéne! osztalyzat < 1 || osztalyzat > 5
            boolean helyesOsztalyzat = false;

            while(helyesOsztalyzat == false) {
                System.out.println("Adja meg hányast kapott az adott tárgyból!");
                System.out.println(targy.getNev() + "");
                try {
                    System.out.println("Osztályzat: ");
                    int osztalyzatErtek = 0;
                    //kéne ide egy try, breakelünk ha nem számot ad
                    //akkor is kéne egy break ha nem 1-5-ig van a szám!
                    osztalyzatErtek = scn.nextInt();
                    scn.nextLine();

                    if(osztalyzatErtek < 1 || osztalyzatErtek > 5) {
                        System.err.println("\r\nAz érdemjegyek 1 és 5 közötti értékek!\r\n");
                    }
                    else {
                        OsztalyzatEnum osztalyzat = OsztalyzatEnum.ELEGTELEN;

                        for (OsztalyzatEnum erdemjegy : OsztalyzatEnum.values()) {
                            if (osztalyzatErtek == erdemjegy.getErtek()) {
                                osztalyzat = erdemjegy;
                            }
                        }

                        TeljesitettTantargy tantargy = new TeljesitettTantargy(targy.getNev(), targy.getKredit(), osztalyzat, Fio.felev);
                        tantargy.mentes();
                        helyesOsztalyzat = true;
                    }

                }
                catch(InputMismatchException ex){
                    System.err.println("\r\nSzámot adjon meg osztályzatként!\r\n");
                    scn.nextLine();
                }

            }
        }
        Fio.xmlTorles(felvettFajlnev);
        Fio.felvettTantargyak.clear();
        Fio.teljesitettTantargyak = Fio.beolvasasTeljesitett();
        Fio.felev++;
        Fio.felevMentes(Fio.felev);

    }

    public static void kiszamito(Integer felev) {
        //végig kéne menni for ciklussal a teljesített tárgyakon hogy megkeressük az adott félévi tantárgyakat
        //súlyozott? átlag kiszámoláshoz sum teljesitett kredit * erdemjegy (atlagosszeg) / osszes teljesitett kredit
        //KKI: kreditindex * osszes teljesitett kredit / osszes vallalt kredit
        //kreditindex: sum teljesitett kredit * erdemjegy / 30

        Double osszesTeljesitettKredit = 0.0, osszesVallaltKredit = 0.0, atlagOsszeg = 0.0;

        for(TeljesitettTantargy tantargy: Fio.teljesitettTantargyak) {
            if(tantargy.getFelev() == felev) {

                osszesVallaltKredit += tantargy.getKredit();


                if(tantargy.getOsztalyzat().getErtek() != 1) {
                    atlagOsszeg += (tantargy.getKredit() * tantargy.getOsztalyzat().getErtek());
                    osszesTeljesitettKredit += tantargy.getKredit();

                }
            }
        }

        Double sulyozottAtlag = atlagOsszeg / osszesTeljesitettKredit;
        Double kki = atlagOsszeg / 30.0 * osszesTeljesitettKredit / osszesVallaltKredit;

        System.out.println("\r\nSúlyozott átlag: " + df.format(sulyozottAtlag) + "\r\nKKI: " + df.format(kki) + "\r\n");

    }

}



