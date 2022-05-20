package orarend.business.os;

import orarend.business.GetterFunctionName;

public class Tantargy {

    public static final String felvehetoFajlnev = "src/main/resources/felvehetoTargyak.xml";
    public static final String teljesitettFajlnev = "src/main/resources/teljesitettTargyak.xml";

    @GetterFunctionName(name="getNev")
    private String nev;
    @GetterFunctionName(name="getKredit")
    private Integer kredit;

    public Tantargy() {
    }
    public Tantargy(String nev, Integer kredit) {
        this.nev = nev;
        if(kredit >= 0 && kredit <= 6) {
            this.kredit = kredit;
        }
        else {
            this.kredit = 1;
        }

    }

    public String getNev() {
        return nev;
    }

    public Integer getKredit() {
        return kredit;
    }

}
