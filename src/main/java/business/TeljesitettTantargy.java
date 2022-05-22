package business;

import business.os.Tantargy;
import fio.Fio;

public class TeljesitettTantargy extends Tantargy {

    @GetterFunctionName(name="getOsztalyzat")
    private OsztalyzatEnum osztalyzat;

    public TeljesitettTantargy(String nev, Integer kredit, OsztalyzatEnum osztalyzat) {
        super(nev, kredit);
        this.osztalyzat = osztalyzat;
    }

    public OsztalyzatEnum getOsztalyzat() {
        return osztalyzat;
    }

    public void mentes(){
        Fio<TeljesitettTantargy> t = new Fio<TeljesitettTantargy>();
        t.mentes(this, Tantargy.teljesitettFajlnev);
    }

    @Override
    public String toString() {
        return super.toString() + "\r\nOsztályzat: " + this.osztalyzat + "\r\n";
    }
}
