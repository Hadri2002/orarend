package business;

import business.os.Tantargy;
import fio.Fio;

public class FelvehetoTantargy extends Tantargy {

    @GetterFunctionName(name="getElofelteteles")
    private final Boolean elofelteteles;
    @GetterFunctionName(name="getElofeltetel")
    private final String elofeltetel;

    public Boolean getElofelteteles() {
        return elofelteteles;
    }

    public String getElofeltetel() {
        return elofeltetel;
    }

    public FelvehetoTantargy(String nev, Integer kredit, Boolean elofelteteles, String elofeltetel) {
        super(nev, kredit);
        this.elofelteteles = elofelteteles;

        if(this.elofelteteles) {
            this.elofeltetel = elofeltetel;
        }
        else {
            this.elofeltetel = "NINCS";
        }

    }

    public void mentes(){
        Fio<FelvehetoTantargy> f = new Fio<FelvehetoTantargy>();
        f.mentes(this, Tantargy.felvehetoFajlnev);
    }


    @Override
    public String toString() {
        if (this.elofelteteles){
            return super.toString() + "Előfeltétel: " + this.elofeltetel + "\r\n";
        }
        else{
            return super.toString();
        }

    }
}
