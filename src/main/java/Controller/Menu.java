package Controller;

import business.Methods;
import fio.Fio;
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
                            Fio.felev = 1;
                            Fio.felevMentes(Fio.felev);
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

            System.out.println("1 - Tantárgyak listázása\r\n2 - Új tantárgy felvétele\r\n3 - Tantárgy leadása\r\n4 - " +
                    "Félév teljesítése\r\n5 - Átlag/KKI számítás\r\n");
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
                        System.err.println("A menüpontok 0 és 5 között vannak!\r\n");
                        break;
                }
            } catch (InputMismatchException ex) {
                System.err.println("Csak számokat adhat meg!\r\n");
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

            System.out.println("\r\n1 - Felvehető tantárgyak listázása\r\n2 - Felvett tantárgyak listázása\r\n3 - " +
                    "Teljesített tantárgyak listázása\r\n");
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
        if(Fio.felvettTantargyak.isEmpty()){
            System.err.println("Önnek nincsen még felvett tantárgya!");
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


        int felev = -1;
        while (felev < 1 || felev >= Fio.felevBeolvasas()) {
            System.out.println("Megkívánt félév: ");
            try {
                felev = scn.nextInt();
                scn.nextLine();

                if (felev < 1 || felev >= Fio.felevBeolvasas()){
                    System.out.println("Hibás félév!");
                }
                else {
                    Methods.kiszamito(felev);
                }

            } catch (InputMismatchException ex) {
                System.err.println("Csak számokat adhat meg!\r\n");
                scn.nextLine();
            }
        }


    }

}
