package domein;

public abstract class Kaart extends Object{
    private int waarde;

    public Kaart(int waarde) {
        this.waarde = waarde;
    }

    public int getWaarde() {
        return waarde;
    }

    public void setWaarde(int waarde) {
        this.waarde = waarde;
    }
}
