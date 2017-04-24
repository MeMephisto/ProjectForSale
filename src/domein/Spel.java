//spel
package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Spel {
    private int aantalSpelers = 0;
    private List<Kaart> deck;
    private List<Kaart> cheques;
    private List<Speler> spelers;
    private Ronde ronde;
    private int rotator;
    private Scanner s = new Scanner(System.in);
    private int aantalBeurtenOvergeslaan = 0;
    private String keuze;

    public int getAantalSpelers() {
        return aantalSpelers;
    }

    public void setAantalSpelers(int aantalSpelers) {
        controleerAantalSpelers(aantalSpelers);
        this.aantalSpelers = aantalSpelers;
    }
    
    private void controleerAantalSpelers(int aantalSpelers){
        if(aantalSpelers > 6 || aantalSpelers < 3){
            throw new IllegalArgumentException("Er moeten minimaal 3 spelers zijn en er mogen maximaal 6 spelers zijn.");
        } 
    }

    public Spel() {
        this.deck = new ArrayList<>();
        this.spelers = new ArrayList<>();
        this.ronde = new Ronde(aantalSpelers);
        this.cheques = new ArrayList<>();       
    }
    
    public void maakSpelers(String naam, int spelerNr){       
            spelers.add(new Speler(naam, (int) Math.floor(84/aantalSpelers), spelerNr));
        
    }
    
    public List<Speler> geefSpelers(){
        return spelers;
    }
    
    public Speler geefSpeler(int index){
        return geefSpelers().get(index);
    }
    
    public void maakDeck(){
        for (int i = 1; i <= 30; i++) {
            deck.add(new Huiskaart(i));
        }
    }
    
    public void maakCheques(){
        int waarde = 0;
            for (int i = 1; i <= 15; i++){
                for (int j = 0; j <= 1; j++) {
                    cheques.add(new Cheque(waarde));
                }
                if(waarde == 0){
                waarde += 2000;
                } else { waarde += 1000;}  
            }
    }
    
    public void verwijderKaarten4Spelers(){
        if (getAantalSpelers() == 4) {
            for (int i = 0; i <= 1; i++) {
                deck.remove(i);
                cheques.remove(i);
            }
        }
    }
    
    public void shuffleDeck(){
        Collections.shuffle(deck);
        Collections.shuffle(cheques);
    }
    
    public void voegRondeKaartenToe(){
        for (int j = 0; j < getAantalSpelers(); j++) {
                //De huiskaarten die in de ronde gebruikt worden, worden in een aparte ArrayList geplaatst en verwijderdt uit het deck
                ronde.voegRondeKaartToe(deck.get(0));
                deck.remove(0);
            }    
    }
    
    public void voegRondeChequesToe() {
        for (int j = 0; j < getAantalSpelers(); j++) {   
            ronde.voegRondeChequeToe(cheques.get(0));
            cheques.remove(0);
        }
    }
    
    public List<Kaart> toonRondeKaarten(){
        return ronde.getKaartenInRonde();
    }
    
    public List<Kaart> toonRondeCheques(){
        return ronde.getChequesInRonde();
    }
    
    public void kiesBeginnendeSpeler(int i){
        if (i > 0) {
            for (Speler speler : spelers) {
                if (speler.getHeeftRondeGewonnen()) 
                    rotator = -(speler.getSpelerNr()- 1);
                    }
                }
                Collections.rotate(spelers, rotator);
            }
    
    public void spelerReset(){
        for (Speler speler : spelers) {
            speler.setGebodenMunten(0);
            speler.setHeeftBeurtOvergeslaan(false);
            speler.setHeeftRondeGewonnen(false);
        }
    }
    
    public void aantalOverGeslagenReset(){
        aantalBeurtenOvergeslaan = 0;
    }
    public String geefSpelerString(int i){
        return spelers.get(i).toString();
    }
    
    public void vindLaagsteKaartInRonde(){
        ronde.vindLaagsteInRonde("Kaart");
    }
    public int geefLaagsteRondeKaart(){
        return ronde.getIndexLowestCard();
    }

    public void setAantalBeurtenOvergeslaan(int aantalBeurtenOvergeslaan) {
        this.aantalBeurtenOvergeslaan = aantalBeurtenOvergeslaan;
    }

    public int getAantalBeurtenOvergeslaan() {
        return aantalBeurtenOvergeslaan;
    }
    public int geefHoogsteBod(){
        return ronde.getHoogsteBod();
    }
    public void resetHoogsteBod(){
        ronde.setHoogsteBod(0);
    }
     
    public void keuzePassenBieden(String keuze, int j){
        //Als de speler zijn/haar beurt wilt overslaan krijgt hij/zij automatich de huiskaart met de laagste waarde en wordt deze uit de ronde verwijderdt
        String chequeOfKaart = "Kaart";
        if (keuze.equals("ja")) {
            ronde.vindLaagsteInRonde(chequeOfKaart);
            geefSpeler(j).getSpelerKaarten().add(ronde.geefLaagsteKaartInRonde());                          
            geefSpeler(j).setHeeftBeurtOvergeslaan(true);
        }                                                         
    }
    

    
    public void bieden(int bod, int j){
                                    //De speler moet genoeg munten bezitten of het bod gaat niet door
        if (bod > this.geefSpeler(j).getMunten()) {
            throw new IllegalArgumentException("Niet genoeg munten");
        } 
                            //De speler moet meer bieden dan het huidige hoogste bod
        else if (bod <= ronde.getHoogsteBod()) {
            throw new IllegalArgumentException("Je moet meer bieden dan het huidige hoogste bod");
        } 
                            //Het aantal munten dat de speler heeft geboden wordt van zijn aantal afgetrokken en wordt bijgehouden in de variabele gebodenMunten
        else {
            geefSpeler(j).setGebodenMunten(bod);
            ronde.setHoogsteBod(bod);
        }
    } 
                        //Als de speler iets ingeeft dat geen geldig getal is gaat het bod afgewezen worden

    public void spelersUpdateEindeRonde(){
        for (Speler speler : spelers) {
            if (speler.getHeeftBeurtOvergeslaan()) {
                int kost = (int) Math.floor(speler.getGebodenMunten()/ 2);
                speler.setMunten(speler.getMunten() - kost);
            } else if (!speler.getHeeftBeurtOvergeslaan()) {
                    speler.setMunten(speler.getMunten()- speler.getGebodenMunten());
                    speler.setHeeftRondeGewonnen(true);
                    speler.getSpelerKaarten().add(ronde.getKaartenInRonde().get(0));                    
            }   
        }      
    }
            
    public void kiesKaartInzet(int keuze, int spelerIndex){
        geefSpeler(spelerIndex).setGekozenKaart(geefSpeler(spelerIndex).getSpelerKaarten().get(keuze));
    }
    
    public void voegGekozenKaartenToeAanRonde(){
            for (int i = 0; i < aantalSpelers; i++) {
            ronde.getKaartenInRonde().add(geefSpeler(i).getGekozenKaart());
        }
    }
    
    public String geefKaartString(int kaartWaarde){
        
        return String.format("images/" + kaartWaarde+ ".jpg");
    }
    
    public void verdeelCheques(){
        for(int i = 0; i < aantalSpelers; i++){
            ronde.vindLaagsteInRonde("Cheque");
            ronde.vindLaagsteInRonde("Kaart");
            for (Speler speler : spelers) {
                if(ronde.geefLaagsteKaartInRonde().getWaarde() == speler.getWaardeGekozenKaart()){
                    speler.voegSpelerChequeToe(ronde.geefLaagsteChequeInRonde());
                    ronde.verwijderLaagsteChequeInRonde();
                    ronde.verwijderLaagsteKaartInRonde();
                    break;
                }   
            }
        }
    }
    
    public Speler vindWinnaar(){
        int hoogsteScore = 0;
        Speler winnaar = null;
        for (Speler speler : spelers) {
            if(speler.getScore() > hoogsteScore){
                hoogsteScore = speler.getScore();
                winnaar = speler;
            }                        
        }
        return winnaar;
    }
}
    
    

    
    
 

