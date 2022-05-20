package business;

import business.os.Tantargy;
import fio.Fio;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Methods {
    public static ArrayList<FelvehetoTantargy> felvehetoTantargyak = Fio.beolvasasFelveheto();
    public static ArrayList<TeljesitettTantargy> teljesitettTantargyak = Fio.beolvasasTeljesitett();
    public static ArrayList<Tantargy> felvettTantargyak = Fio.beolvasFelvett();

    private static final Scanner scn = new Scanner(System.in);

    public static void listazas() {
        int choice = -1;
        while(choice != 0) {
            switch(choice) {
                case 1: felvehetoKiiratas(felvehetoTantargyak); break;
                case 2: felvettKiiratas(felvettTantargyak); break;
                case 3: teljesitettKiiratas(teljesitettTantargyak); break;
            }

            System.out.println("\r\n1 - Felvehető tantárgyak listázása\r\n2 - Felvett tantárgyak listázása\r\n3 - Teljesített tantárgyak listázása\r\n");
            System.out.println("0 - Vissza");

            try{
                choice = scn.nextInt();
                scn.nextLine();
                if(choice < 0 || choice > 3) {
                    System.err.println("A menüpontok 0 és 3 között vannak!");
                }
            }
            catch (InputMismatchException ex) {
                System.err.println("A menüpontok 0 és 3 között vannak!");
                scn.nextLine();
            }

        }

    }

    private static void felvehetoKiiratas (ArrayList<FelvehetoTantargy> targyak) {
        for(FelvehetoTantargy targy: targyak) {
            System.out.println(targy);
        }
    }

    private static void felvettKiiratas (ArrayList<Tantargy> targyak) {
            for(Tantargy targy: targyak) {
                System.out.println(targy);
            }
    }

    private static void teljesitettKiiratas (ArrayList<TeljesitettTantargy> targyak) {
        for(TeljesitettTantargy targy: targyak) {
            System.out.println(targy);
        }
    }



}
