package Controller;

import business.FelvehetoTantargy;
import business.Methods;
import business.TeljesitettTantargy;
import fio.Fio;

import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

import static business.Methods.tantargyFelvetel;

public class Menu {
    private static Scanner scn = new Scanner(System.in);

    public static void run() {
        int choice = -1;
        while (choice != 0) {
            switch (choice) {
                case 1:
                    listazas();
                    break;
                case 2:
                    tantargyFelvetel();
                    break;
                //case 3 -> tantargyleadas();
                //case 4 -> felevLepes();
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

}
