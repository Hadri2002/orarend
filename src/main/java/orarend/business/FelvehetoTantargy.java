package orarend.business;

import orarend.business.os.Tantargy;
import orarend.fio.Fio;

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
        Fio<FelvehetoTantargy> f = new Fio<FelvehetoTantargy>();
        f.mentes(this);
    }
    /*public void beolvasas(){
        Fio<FelvehetoTantargy> f = new Fio<FelvehetoTantargy>();
        f.beolvasas(this);
    }*/

    @Override
    public String toString() {
        if (this.elofelteteles == true){
            return super.toString() + "\r\nElőfeltétel: " + this.elofeltetel + "\r\n\r\n";
        }
        else{
            return super.toString() + "\r\n\r\n";
        }

    }
}
