package business;

import business.os.Tantargy;
import fio.Fio;

import java.util.ArrayList;
import java.util.Scanner;

public class Methods {

    public static void felvehetoKiiratas (ArrayList<FelvehetoTantargy> targyak) {
        for(FelvehetoTantargy targy: targyak) {
            /*for(int i = 0; i < Fio.felvettTantargyak.size(); i++) {
                Fio.felvettTantargyak.get(i).getNev()
            }*/
            //meg kéne nézni hogy fel van-e már véve / teljesítve van-e, ha nem akkor kiiratni!
            System.out.println(targy);
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

    public static void tantargyFelvetel() {

    }


}
