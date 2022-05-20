package orarend.business;

import orarend.business.os.Tantargy;
import orarend.fio.Fio;

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


}
