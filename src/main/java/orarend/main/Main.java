package orarend.main;

import orarend.business.FelvehetoTantargy;
import orarend.business.OsztalyzatEnum;
import orarend.business.TeljesitettTantargy;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        TeljesitettTantargy targy = new TeljesitettTantargy("programozás", 6, OsztalyzatEnum.ELEGSEGES);
        System.out.println(targy.getOsztalyzat());
        System.out.println(targy.getKredit());
        System.out.println(targy.getNev());

        /*ArrayList<FelvehetoTantargy> = beolvasasFelveheto();
        FelvehetoTantargy targy2 = new FelvehetoTantargy("Név", 4, Boolean.TRUE, "ndsa");

        targy2.beolvasas();*/

    }
}
