package Controller;

import business.Methods;
import fio.Fio;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

import static business.os.Tantargy.felvettFajlnev;
import static business.os.Tantargy.teljesitettFajlnev;


public class Menu {
    private static final Scanner scn = new Scanner(System.in);


    public static void startup() {
        System.out.println("Üdvözöllek a tantárgyfelvételi programban!\r\n");
        System.out.println("Ön jelenleg a "+Fio.felev+". félévben van.\r\n");

        if(Fio.felvettTantargyak.size() > 0 || Fio.teljesitettTantargyak.size() > 0) {
            int choice = -1;
            while(choice!=0 && choice!=1){
                System.out.println("Folytatni kívánod az elmentett adatokkal?\r\n0 - Nem\r\n1 - Igen");
                try{
                    choice = scn.nextInt();
                    scn.nextLine();

                    switch (choice){
                        case 0:
                            //vissza kell állítani a félévet 1-re!
                            Fio.felev = 1;
                            Fio.felevMentes(Fio.felev);
                            //törölni kell az xml-ből mindent is!
                            Fio.xmlTorles(felvettFajlnev);
                            Fio.xmlTorles(teljesitettFajlnev);
                            Fio.felvettTantargyak.clear();
                            Fio.teljesitettTantargyak.clear();
                            break;
                        case 1:
                            break;
                        default:
                            System.err.println("\r\nA menüpontok 0 és 1 között vannak!\r\n");
                            break;
                    }

                }catch (InputMismatchException ex) {
                    System.err.println("\r\nCsak számokat adhat meg!\r\n");
                    scn.nextLine();
                }

            }
        }
        mainMenu();
    }

    public static void mainMenu() {
        int choice = -1;
        while (choice != 0) {

            System.out.println("\r\n1 - Tantárgyak listázása\r\n2 - Új tantárgy felvétele\r\n3 - Tantárgy leadása\r\n4 - Félév teljesítése\r\n5 - Átlag/KKI számítás\r\n");
            System.out.println("0 - Kilépés\n");
            try {
                choice = scn.nextInt();
                scn.nextLine();

                switch (choice) {
                    case 0:
                        break;
                    case 1:
                        listazas();
                        break;
                    case 2:
                        tantargyFelvetel();
                        break;
                    case 3:
                        tantargyLeadas();
                        break;
                    case 4: felevLepes();
                        break;
                    case 5: kiszamito();
                        break;
                    default:
                        System.err.println("\r\nA menüpontok 0 és 5 között vannak!\r\n");
                        break;
                }
            } catch (InputMismatchException ex) {
                System.err.println("\r\nCsak számokat adhat meg!\r\n");
                scn.nextLine();
            }
        }
    }

    public static void listazas() {
        int choice = -1;
        while (choice != 0) {
            switch (choice) {
                case 1:
                    Methods.felvehetoKiiratas(Fio.felvehetoTantargyak);
                    break;
                case 2:
                    Methods.felvettKiiratas(Fio.felvettTantargyak);
                    break;
                case 3:
                    Methods.teljesitettKiiratas(Fio.teljesitettTantargyak);
                    break;
            }

            System.out.println("\r\n1 - Felvehető tantárgyak listázása\r\n2 - Felvett tantárgyak listázása\r\n3 - Teljesített tantárgyak listázása\r\n");
            System.out.println("0 - Vissza");

            try {
                choice = scn.nextInt();
                scn.nextLine();
                if (choice < 0 || choice > 3) {
                    System.err.println("\r\nA menüpontok 0 és 3 között vannak!\r\n");
                }
            } catch (InputMismatchException ex) {
                System.err.println("\r\nA menüpontok 0 és 3 között vannak!\r\n");
                scn.nextLine();
            }

        }

    }

    public static void tantargyFelvetel() {
        Methods.felvehetoKiiratas(Fio.felvehetoTantargyak);
        System.out.println("A fent felsorolt tantárgyak közül válassza ki a felvenni kívánt tantárgyat a tárgy nevének " +
                "beírásával!");
        String felvenniKivantTargy = scn.nextLine();
        Methods.tantargyFelvetel(felvenniKivantTargy);
    }

    public static void tantargyLeadas(){
        Methods.felvettKiiratas(Fio.felvettTantargyak);
        //ellenőrzés, hogy egyeltalán van-e leadható tárgya, viszont szerintem csúnyán írja ki szóval ez talán megoldandó
        if(Fio.felvettTantargyak.isEmpty()){
            System.err.println("\r\nÖnnek nincsen még felvett tantárgya!\r\n");
            return;
        }
        System.out.println("A fenti felsorolt, felvett tantárgyai közül válassza ki a leadni kívánt tantárgyat a tárgy " +
                "nevének beírásával!");
        String leadniKivantTargy = scn.nextLine();
        Methods.tantargyLeadas(leadniKivantTargy);
    }

    public static void felevLepes(){
        System.out.println("Teljesítette a(z) " + Fio.felev + ". félévet!\r\nKérem adja meg az összes tantárgy esetében az " +
                "elért érdemjegyét:\r\n");
        Methods.felevLepes();
    }

    public static void kiszamito() {
        System.out.println("Adja meg, hogy melyik félévének átlagát és KKI-ját kívánja megtekinteni: ");

        //try catch integerrel adja meg a kívánt félévet
        //csekkolni hogy létezik-e az a félév? while ciklussal elsőtől mostaniig
        //meghívni Methods-ból a logikáját ahol kiszámolja

        int felev = -1;
        while (felev < 1 || felev >= Fio.felevBeolvasas()) {
            System.out.println("Megkívánt félév: ");
            try {
                felev = scn.nextInt();
                scn.nextLine();

                if (felev < 1 || felev >= Fio.felevBeolvasas()){
                    System.err.println("\r\nHibás félév!\r\n");
                }
                else {
                    Methods.kiszamito(felev);
                }

            } catch (InputMismatchException ex) {
                System.err.println("\r\nCsak számokat adhat meg!\r\n");
                scn.nextLine();
            }
        }


    }

}
