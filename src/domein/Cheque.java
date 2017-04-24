package domein;

public class Cheque extends Kaart{
    public Cheque(int waarde) {
        super(waarde);
        controleerWaarde(waarde);
    }
    
    private void controleerWaarde(int waarde){
        if(waarde < 0 || waarde > 15000){
            throw new IllegalArgumentException("Foutieve waarde van de cheque.");
        }
    }
}

    

