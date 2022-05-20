package orarend.business;

import orarend.business.os.Tantargy;

public class FelvehetoTantargy extends Tantargy {

    @GetterFunctionName(name="getElofelteteles")
    private Boolean elofelteteles;
    @GetterFunctionName(name="getElofeltetel")
    private String elofeltetel;

    public Boolean getElofelteteles() {
        return elofelteteles;
    }

    public String getElofeltetel() {
        return elofeltetel;
    }

    public FelvehetoTantargy(String nev, Integer kredit, Boolean elofelteteles, String elofeltetel) {
        super(nev, kredit);
        this.elofelteteles = elofelteteles;

        if(this.elofelteteles == true) {
            this.elofeltetel = elofeltetel;
        }
        else {
            this.elofeltetel = "NINCS";
        }

    }

    public  void mentes(){
        Fio<>
    }
    /*public void beolvasas(){
        Fio<FelvehetoTantargy> f = new Fio<FelvehetoTantargy>();
        f.beolvasas(this);
    }*/

}
