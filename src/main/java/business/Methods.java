package business;

import business.os.Tantargy;
import fio.Fio;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Methods {

    private static final Scanner scn = new Scanner(System.in);


    public static void felvehetoKiiratas (ArrayList<FelvehetoTantargy> targyak) {
        for(FelvehetoTantargy targy: targyak) {
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



}
