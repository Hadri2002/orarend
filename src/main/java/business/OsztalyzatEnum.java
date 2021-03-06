package business;

public enum OsztalyzatEnum {

    ELEGTELEN(1), ELEGSEGES(2), KOZEPES(3), JO(4), JELES(5);

    private final Integer osztalyzat;

    private OsztalyzatEnum(Integer osztalyzat) {
        this.osztalyzat = osztalyzat;
    }

    public Integer getErtek() {
        return this.osztalyzat;
    }

}
