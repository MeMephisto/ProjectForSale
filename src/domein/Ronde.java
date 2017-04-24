package domein;

import java.util.ArrayList;
import java.util.List;


public class Ronde {
    
    private List<Kaart> kaartenInRonde;
    private List<Kaart> chequesInRonde;
    private int hoogsteBod = 0;
    int indexLowestCard;
    int indexLowestCheque;

    public List<Kaart> getKaartenInRonde() {
        return kaartenInRonde;
    }
    
    public List<Kaart> getChequesInRonde() {
        return chequesInRonde;
    }
    
    public void voegRondeChequeToe(Kaart index) {
        chequesInRonde.add(index);
    }

    public void voegRondeKaartToe(Kaart index){
        kaartenInRonde.add(index);
    }
    
    
    public Ronde(int arrayLength) {
        this.kaartenInRonde = new ArrayList<>(arrayLength);
        this.chequesInRonde = new ArrayList<>(arrayLength);
    }

    public int getHoogsteBod() {
        return hoogsteBod;
    }

    public void setHoogsteBod(int hoogsteBod) {
        this.hoogsteBod = hoogsteBod;
    }

    public void vindLaagsteInRonde(String chequeOfKaart) {
        if (chequeOfKaart=="Kaart") {
            indexLowestCard = 0;
            for(int j = 0; j < getKaartenInRonde().size(); j++){
            if(kaartenInRonde.get(j).getWaarde() < kaartenInRonde.get(indexLowestCard).getWaarde()){
                indexLowestCard = j;
            }
        }
        }else {
            indexLowestCheque = 0;
            for(int j = 0; j < getChequesInRonde().size(); j++){
            if(chequesInRonde.get(j).getWaarde() < chequesInRonde.get(indexLowestCheque).getWaarde()){
                indexLowestCheque = j;
            }
        }
        }

    }
    
    public Kaart geefLaagsteKaartInRonde(){
        return kaartenInRonde.get(indexLowestCard);
    }
    
    public Kaart geefLaagsteChequeInRonde(){
        return chequesInRonde.get(indexLowestCheque);
    }

    public int getIndexLowestCard() {
        return indexLowestCard;
    }
    
    public void verwijderLaagsteKaartInRonde(){
        kaartenInRonde.remove(indexLowestCard);
    }
    
    public void verwijderLaagsteChequeInRonde(){
        chequesInRonde.remove(indexLowestCheque);
    }
    
}
