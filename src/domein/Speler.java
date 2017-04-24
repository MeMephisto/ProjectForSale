package domein;

import java.util.ArrayList;
import java.util.List;

public class Speler {
    private int munten;
    private int gebodenMunten;
    private boolean heeftBeurtOvergeslaan;
    private boolean heeftRondeGewonnen;
    private final int spelerNr;
    private String naam;
    private int score;
    private int waardeGekozenKaart;
    private Kaart gekozenKaart;
    
    private List<Kaart> spelerKaarten = new ArrayList<>();
    private List<Kaart> spelerCheques = new ArrayList<>();

    public List<Kaart> getSpelerKaarten() {
        return spelerKaarten;
    }
    
    public List<Kaart> getSpelerCheques() {
        return spelerCheques;
    }
    
    public Speler(){
        spelerNr = 0;
    }
    
    public Speler(String naam, int munten, int spelerNr) {
        controleerNaam(naam);
        this.munten = munten;
        this.spelerNr = spelerNr;
        heeftBeurtOvergeslaan = false;
        this.naam = naam;
    }
    
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMunten() {
        return munten;
    }

    public void setMunten(int munten) {
        this.munten = munten;
    }

    public boolean getHeeftBeurtOvergeslaan() {
        return heeftBeurtOvergeslaan;
    }

    public void setHeeftBeurtOvergeslaan(boolean heeftBeurtOvergeslaan) {
        this.heeftBeurtOvergeslaan = heeftBeurtOvergeslaan;
    }

    public boolean getHeeftRondeGewonnen() {
        return heeftRondeGewonnen;
    }

    public void setHeeftRondeGewonnen(boolean heeftRondeGewonnen) {
        this.heeftRondeGewonnen = heeftRondeGewonnen;
    }

    public int getGebodenMunten() {
        return gebodenMunten;
    }

    public void setGebodenMunten(int gebodenMunten) {
        this.gebodenMunten = gebodenMunten;
    }

    public int getSpelerNr() {
        return spelerNr;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        controleerNaam(naam);
        this.naam = naam;
    }
    
    public void controleerNaam(String naam){
        if(naam == null || "".equals(naam) || naam == " "){
            throw new IllegalArgumentException("Ongeldige naam.");
        }
    }
    
    public void reset(){
        gebodenMunten = 0;
        heeftBeurtOvergeslaan = false;
        heeftRondeGewonnen = false;
    }

    public Kaart getGekozenKaart() {
        return gekozenKaart;
    }

    public void setGekozenKaart(Kaart gekozenKaart) {
        this.gekozenKaart = gekozenKaart;
    }

    public int getWaardeGekozenKaart() {
        return waardeGekozenKaart;
    }

    public void setWaardeGekozenKaart(int waardeGekozenKaart) {
        this.waardeGekozenKaart = waardeGekozenKaart;
    }
    
    
    public void telScore(){
        for (Kaart kaart : spelerCheques) {
            score += kaart.getWaarde();
        }
        score += (munten * 1000);
        
    }
    
    @Override
    public String toString() {
        String gewonnen;
        String overslaan;
        if (getHeeftBeurtOvergeslaan() == true) {
            overslaan = "Ja";
        }else {
            overslaan = "Nee";
        }
        if (getHeeftRondeGewonnen() == true) {
            gewonnen = "Ja";
        }else {
            gewonnen = "Nee";
        }
        return String.format("Speler: %s, Munten: %d, heeftBeurtOvergeslaan: %s, gebodenMunten: %d, heeftVorigeRondeGewonnen: %s%n", getNaam(), getMunten(), overslaan, getGebodenMunten(), gewonnen);
    }
    
    public void resetGekozenKaart(){
        waardeGekozenKaart = 0;
        
    }
    
    public void voegSpelerChequeToe(Kaart cheque){
        spelerCheques.add(cheque);
    }
    
    
    

    
}
