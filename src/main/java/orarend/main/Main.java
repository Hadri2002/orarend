package orarend.main;

import orarend.business.FelvehetoTantargy;
import orarend.business.OsztalyzatEnum;
import orarend.business.TeljesitettTantargy;
import orarend.fio.Fio;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TeljesitettTantargy targy = new TeljesitettTantargy("programozás", 6, OsztalyzatEnum.ELEGSEGES);
        System.out.println(targy.getOsztalyzat());
        System.out.println(targy.getKredit());
        System.out.println(targy.getNev());

        /*ArrayList<FelvehetoTantargy> = beolvasasFelveheto();
        FelvehetoTantargy targy2 = new FelvehetoTantargy("Név", 4, Boolean.TRUE, "ndsa");

        targy2.beolvasas();*/
        FelvehetoTantargy feltargy = new FelvehetoTantargy("progmod", 69, false, "");
        //feltargy.mentes();
        //targy.mentes();

        ArrayList<FelvehetoTantargy> fel = new ArrayList<FelvehetoTantargy>();
        fel = Fio.beolvasasFelveheto();

        System.out.println(fel);

        Scanner scn = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {
            switch (choice) {
                //case 1 -> Listazas();
                //case 2 -> tantargyFelvetel();
                //case 3 -> targyleadas();
                //case 4 -> felevLepes();
            }
            System.out.println("Menüpontok majd");
            try {
                choice = scn.nextInt();
                scn.nextLine();
                if (choice < 0 || choice > 4) {
                    System.out.println("Not valid option.");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
}
