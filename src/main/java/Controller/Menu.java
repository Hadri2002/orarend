package Controller;

import business.Methods;
import fio.Fio;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

import static business.os.Tantargy.felvettFajlnev;
import static business.os.Tantargy.teljesitettFajlnev;


public class Menu {
    private static final Scanner scn = new Scanner(System.in);

    public static void startup() {
        if(Fio.felvettTantargyak.size() > 0 || Fio.teljesitettTantargyak.size() > 0) {
            System.out.println("Üdvözöllek a tantárgyfelvételi programban!\r\nFolytatni kívánod az elmentett adatokkal?" +
                    "\r\n0 - nem\r\n1 - igen");

            int choice = -1;
            try{
                while(choice != 0 || choice != 1) {
                    choice = scn.nextInt();
                    scn.nextLine();
                    if(choice == 0) {
                        //vissza kell állítani a félévet 1-re!
                        //törölni kell az xml-ből mindent is!
                        //a törlés működik, rakjuk külön metódusba?
                       try{
                           Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(felvettFajlnev);
                           Element rootElement = document.getDocumentElement();
                           document.removeChild(rootElement);
                           Node node = document.createElement("tantargyak");
                           document.appendChild(node);

                           Transformer transformer = TransformerFactory.newInstance().newTransformer();
                           Result output = new StreamResult(new File(felvettFajlnev));
                           Source input = new DOMSource(document);

                           transformer.transform(input, output);
                       }
                       catch (ParserConfigurationException ex) {
                           ex.printStackTrace();
                       }
                       catch(Exception ex){
                           ex.printStackTrace();
                       }

                        try{
                            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(teljesitettFajlnev);
                            Element rootElement = document.getDocumentElement();
                            document.removeChild(rootElement);
                            Node node = document.createElement("tantargyak");
                            document.appendChild(node);

                            Transformer transformer = TransformerFactory.newInstance().newTransformer();
                            Result output = new StreamResult(new File(teljesitettFajlnev));
                            Source input = new DOMSource(document);

                            transformer.transform(input, output);
                        }
                        catch (ParserConfigurationException ex) {
                            ex.printStackTrace();
                        }
                        catch(Exception ex){
                            ex.printStackTrace();
                        }


                        Fio.felvettTantargyak.clear();
                        Fio.teljesitettTantargyak.clear();
                        break;
                    }
                    else if(choice == 1) {
                        break;
                    }
                    else System.err.println("Csak 0-t vagy 1-et adhat meg!");
                }
            }
            catch(InputMismatchException e){
                System.err.println("Csak számokat adhat meg!");
                e.printStackTrace();
            }


        }
        mainMenu();
    }

    public static void mainMenu() {
        int choice = -1;
        while (choice != 0) {
            switch (choice) {
                case 1:
                    listazas();
                    break;
                case 2:
                    tantargyFelvetel();
                    break;
                case 3:
                    tantargyLeadas();
                    break;

                //case 4 -> felevLepes(); Tárgyteljesítés is itt lesz?
                //case 5- > szamito();
            }
            System.out.println("1 - Tantárgyak listázása\r\n2 - Új tantárgy felvétele\r\n3 - Tantárgy leadása\r\n4 - Félév teljesítése\r\n5 - Átlag/KKI számítás\r\n");
            System.out.println("0 - Kilépés");
            try {
                choice = scn.nextInt();
                scn.nextLine();
                if (choice < 0 || choice > 5) {
                    System.err.println("A menüpontok 0 és 5 között vannak!");
                }
            } catch (InputMismatchException ex) {
                System.err.println("A menüpontok 0 és 5 között vannak!");
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
                    System.err.println("A menüpontok 0 és 3 között vannak!");
                }
            } catch (InputMismatchException ex) {
                System.err.println("A menüpontok 0 és 3 között vannak!");
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
            System.err.println("Önnek nincsen még felvett tantárgya!");
            return;
        }
        System.out.println("A fenti felsorolt, felvett tantárgyai közül válassza ki a leadni kívánt tantárgyat a tárgy " +
                "nevének beírásával!");
        String leadniKivantTargy = scn.nextLine();
        Methods.tantargyLeadas(leadniKivantTargy);
    }

}
