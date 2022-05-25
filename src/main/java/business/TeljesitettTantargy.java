package business;

import business.os.Tantargy;
import fio.Fio;

public class TeljesitettTantargy extends Tantargy {

    @GetterFunctionName(name="getOsztalyzat")
    private OsztalyzatEnum osztalyzat;
    @GetterFunctionName(name="getFelev")
    private Integer felev;

    public TeljesitettTantargy(String nev, Integer kredit, OsztalyzatEnum osztalyzat, Integer felev) {
        super(nev, kredit);
        this.osztalyzat = osztalyzat;
        this.felev = felev;
    }

    public OsztalyzatEnum getOsztalyzat() {
        return osztalyzat;
    }

    public Integer getFelev(){return felev;}

    public void mentes(){
        Fio<TeljesitettTantargy> t = new Fio<TeljesitettTantargy>();
        t.mentes(this, Tantargy.teljesitettFajlnev);
    }

    @Override
    public String toString() {
        return super.toString() + "Osztályzat: " + this.osztalyzat + "\r\n";
    }
}
