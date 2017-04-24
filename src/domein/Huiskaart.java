package domein;

public class Huiskaart extends Kaart{
    public Huiskaart(int waarde) {
        super(waarde);
        this.controleerWaarde(waarde);
    }
    
    private void controleerWaarde(int waarde){
        if(waarde > 30 || waarde < 0){
            throw new IllegalArgumentException("De waarde van een huiskaart ligt niet binnen de grenzen (0-30)");
        }
    }  
}
