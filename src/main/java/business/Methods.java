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

    public static void tantargyFelvetel(String felvenniKivantTargy) {
        boolean tantargyLetezik = false;

        for(FelvehetoTantargy tantargy: Fio.felvehetoTantargyak) {

            if(felvenniKivantTargy.equals(tantargy.getNev())) {
                tantargyLetezik = true;

                if(tantargy.getElofelteteles() == true) {
                    boolean elofeltetelTeljesitve = false;
                    for(TeljesitettTantargy teljesitett: Fio.teljesitettTantargyak) {
                        if(tantargy.getElofeltetel().equals(teljesitett.getNev())) {

                            elofeltetelTeljesitve = true;
                            break;
                        }
                    }
                    if(elofeltetelTeljesitve == false) {
                        System.err.println("A tárgyat nem veheti fel, mivel nem teljesítette annak előfeltételét!");
                        return;
                    }
                }

                Fio.felvettTantargyak.add(tantargy);
                tantargy.mentes();
                System.out.println("Sikeresen felvette a tantárgyat!");
                break;
            }
        }

        if(tantargyLetezik == false) {
            System.err.println("Nem létezik a beírt tantárgy!");
        }
    }

}



