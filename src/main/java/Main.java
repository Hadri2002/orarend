import business.FelvehetoTantargy;
import business.Methods;
import business.TeljesitettTantargy;
import business.os.Tantargy;
import fio.Fio;
import java.util.InputMismatchException;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {



        /*TeljesitettTantargy targy = new TeljesitettTantargy("programozás", 6, OsztalyzatEnum.ELEGSEGES);
        System.out.println(targy.getOsztalyzat());
        System.out.println(targy.getKredit());
        System.out.println(targy.getNev());*/

        /*ArrayList<FelvehetoTantargy> = beolvasasFelveheto();
        FelvehetoTantargy targy2 = new FelvehetoTantargy("Név", 4, Boolean.TRUE, "ndsa");

        targy2.beolvasas();*/
        //FelvehetoTantargy feltargy = new FelvehetoTantargy("progmod", 69, false, "");
        //feltargy.mentes();
        //targy.mentes();

        /*ArrayList<FelvehetoTantargy> fel = new ArrayList<FelvehetoTantargy>();
        fel = Fio.beolvasasFelveheto();*/

        //System.out.println(fel);

        Scanner scn = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {
            switch (choice) {
                case 1: Methods.listazas(); break;
                //case 2 -> tantargyFelvetel();
                //case 3 -> targyleadas();
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
            }catch (InputMismatchException ex) {
                System.err.println("A menüpontok 0 és 5 között vannak!");
                scn.nextLine();
            }
        }

    }
}
