package business;

import business.os.Tantargy;
import fio.Fio;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

import static business.os.Tantargy.felvettFajlnev;

public class Methods {

    private static final Scanner scn = new Scanner(System.in);
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static void felvehetoKiiratas (ArrayList<FelvehetoTantargy> felvehetoTargyak) {
        for(FelvehetoTantargy felvehetoTargy: felvehetoTargyak) {
            boolean tantargyMarLetezik = false; //false, ha ki kell még írni a felvehetőek közt, true ha nem

            for(Tantargy felvettTantargy : Fio.felvettTantargyak){
                if(felvehetoTargy.getNev().equals(felvettTantargy.getNev())) {
                    tantargyMarLetezik = true;
                    break;
                }
            }

            for(TeljesitettTantargy teljesitettTantargy : Fio.teljesitettTantargyak) {
                if(felvehetoTargy.getNev().equals(teljesitettTantargy.getNev()) && teljesitettTantargy.getOsztalyzat().getErtek() != 1){ //teljesítették, minimum 2-essel
                    tantargyMarLetezik = true;
                    break;
                }
            }
            if(!tantargyMarLetezik){
                System.out.println(felvehetoTargy);
            }
        }
    }

    public static void felvettKiiratas (ArrayList<Tantargy> felvettTantargyak) {
        if(felvettTantargyak.isEmpty()){
            System.out.println("Nincsenek felvett tantárgyak!");
        }
        else {
            for(Tantargy felvettTantargy: felvettTantargyak) {
                System.out.println(felvettTantargy);
            }
        }
    }

    public static void teljesitettKiiratas (ArrayList<TeljesitettTantargy> teljesitettTantargyak) {
        if (teljesitettTantargyak.isEmpty()){
            System.out.println("Nincsenek teljesített tantárgyak!");
        }
        else{
           for(TeljesitettTantargy teljesitettTantargy: teljesitettTantargyak) {
               System.out.println(teljesitettTantargy);
           }
        }
    }

    public static void tantargyFelvetel(String felvenniKivantTargy) {
        boolean tantargyLetezik = false;

        for(FelvehetoTantargy felvehetoTantargy: Fio.felvehetoTantargyak) {

            if(felvenniKivantTargy.equals(felvehetoTantargy.getNev())) { //tehát létezik a tantárgy a listázottak között
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

                if(felvehetoTantargy.getElofelteteles()) {
                    boolean elofeltetelTeljesitve = false;
                    for(TeljesitettTantargy teljesitett: Fio.teljesitettTantargyak) {
                        if(felvehetoTantargy.getElofeltetel().equals(teljesitett.getNev())) {

                            elofeltetelTeljesitve = true;
                            break;
                        }
                    }
                    if(!elofeltetelTeljesitve) {
                        System.err.println("\r\nA tárgyat nem veheti fel, mivel nem teljesítette annak előfeltételét!\r\n");
                        return;
                    }
                }
                Tantargy felvettTantargy = new Tantargy(felvehetoTantargy.getNev(), felvehetoTantargy.getKredit());
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

                    System.out.println("Sikeresen leadta a tantárgyat!");

                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    Result output = new StreamResult(new File(felvettFajlnev));
                    Source input = new DOMSource(document);

                    transformer.transform(input, output);
                    break;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if(!tantargyLetezik) {
            System.err.println("Nem szerepel a beírt tantárgy a felvett tantárgyak között!");
        }
    }

    public static void felevLepes() {

        if(Fio.felvettTantargyak.size() > 0) {
            for(Tantargy targy : Fio.felvettTantargyak){
                boolean helyesOsztalyzat = false;  //false amíg megfelelő osztályzatot nem ad meg a felhasználó

                while(!helyesOsztalyzat) {
                    System.out.println("Adja meg hányast kapott az adott tárgyból!");
                    System.out.println(targy.getNev() + "");
                    try {
                        System.out.println("Osztályzat: ");
                        int osztalyzatErtek;
                        osztalyzatErtek = scn.nextInt();
                        scn.nextLine();

                        if(osztalyzatErtek < 1 || osztalyzatErtek > 5) {
                            System.err.println("Az érdemjegyek 1 és 5 közötti értékek!");
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
                        System.err.println("Számot adjon meg osztályzatként!");
                        scn.nextLine();
                    }

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

            double osszesTeljesitettKredit = 0.0, osszesVallaltKredit = 0.0, atlagOsszeg = 0.0;

            for(TeljesitettTantargy tantargy: Fio.teljesitettTantargyak) {
                if(Objects.equals(tantargy.getFelev(), felev)) {

                    osszesVallaltKredit += tantargy.getKredit();

                    if(tantargy.getOsztalyzat().getErtek() != 1) {
                        atlagOsszeg += (tantargy.getKredit() * tantargy.getOsztalyzat().getErtek());
                        osszesTeljesitettKredit += tantargy.getKredit();

                    }
                }
            }
            Double sulyozottAtlag = 0.0;
            Double kki = 0.0;
            try{
                sulyozottAtlag = atlagOsszeg / osszesTeljesitettKredit;
            }
            catch(Exception e) {
                System.out.println("A teljesített kreditek száma 0, amivel nem tud valójában számolni a rendszer!");
            }

            try{
                kki = atlagOsszeg / 30.0 * osszesTeljesitettKredit / osszesVallaltKredit;
            }
            catch(Exception e) {
                System.out.println("A vállalt kreditek száma 0, amivel nem tud valójában számolni a rendszer!");
            }

            if(osszesTeljesitettKredit == 0.0) {
                sulyozottAtlag = 0.0;
            }
            if(osszesVallaltKredit == 0.0) {
                kki = 0.0;
            }

            System.out.println("\r\nSúlyozott átlag: " + df.format(sulyozottAtlag) + "\r\nKKI: " + df.format(kki) + "\r\n");
        }


}



