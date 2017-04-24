package ui;


import domein.Spel;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ForSale {

    public void StartUp() {
        Spel spel = new Spel();
        Scanner s = new Scanner(System.in);
        boolean completed = false;
        do{
        //Het aantal spelers instellen
        try{
        System.out.println("Hoeveel personen gaan er spelen? ");
        spel.setAantalSpelers(s.nextInt());
        int aantalMunten = (84 / spel.getAantalSpelers());
        completed = true;
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }catch(InputMismatchException e){
            s.next();
            System.out.println("Gelieve een geheel getal in te geven.");
        }
        
        } while(completed == false);
        //De spelers aanmaken met naam, munten en spelerNr
        for (int i = 0; i < spel.getAantalSpelers(); i++) {
            completed = false;
            do{
                try{                
                    System.out.printf("Geef naam in voor speler %d: ", i+1);
                    String naam = s.next();
                    spel.maakSpelers(naam, i+1);
                    completed = true;
                } catch(IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            } while(completed == false);
        }
        //Het deck met 30 huiskaarten aanmaken met een oplopdende waarde van 0 tot 30
        spel.maakDeck();
        // Het deck met 30 cheques maken
        spel.maakCheques();
        //Het deck en de cheques shufflen
        spel.shuffleDeck();
        //2 kaarten verwijderen als er spelers zijn 
        spel.verwijderKaarten4Spelers();


        //Afhankelijk van het aantal spelers de rondes toekennen. Dit wordt elke ronde herhaald.
        for (int i = 0; i < (30 / spel.getAantalSpelers()); i++) {
            System.out.printf("Ronde %d:%n", i + 1);
            //De huiskaarten uit het deck in de ronde gebruiken. (Afhankelijk van het aantal spelers)
            System.out.printf("Huiskaarten in deze ronde:%n");
            //De huiskaarten die in de ronde gebruikt worden komen in een nieuwe array en worden verwijdert uit het deck
            spel.voegRondeKaartenToe();
            for (int j = 0; j < spel.toonRondeKaarten().size(); j++) {
                System.out.printf("Huiskaart %d: %d%n", j+1, spel.toonRondeKaarten().get(j).getWaarde());    
            }
            //Het kiezen van de speler die de ronde zal beginnen
            spel.kiesBeginnendeSpeler(i);
            //In het begin van de ronde worden de waardes gebodenMunten, heeftBeurtOvergeslaan en heeftRondeGewonnen gereset
            spel.spelerReset();
            spel.aantalOverGeslagenReset();
            //Beginnen van de biedfase
            do {
                String keuze = "nee";
                for (int j = 0; j < spel.getAantalSpelers(); j++) {
                    System.out.print(spel.geefSpelerString(j));
                    if(!spel.geefSpeler(j).getHeeftBeurtOvergeslaan()){
                        completed = false;
                        do{
                            try{
                                if(spel.geefSpeler(j).getMunten() == 0 || spel.geefSpeler(j).getMunten() <= spel.geefHoogsteBod()){
                                    System.out.println("Je hebt niet genoeg munten dus wordt er automatisch gepast!");                                    
                                    spel.setAantalBeurtenOvergeslaan(spel.getAantalBeurtenOvergeslaan() + 1 );
                                    spel.vindLaagsteKaartInRonde();
                                    spel.geefSpeler(j).getSpelerKaarten().add(spel.toonRondeKaarten().get(spel.geefLaagsteRondeKaart()));
                                    System.out.printf("Je krijgt de kaart met waarde: %d%n",spel.toonRondeKaarten().get(spel.geefLaagsteRondeKaart()).getWaarde());
                                    spel.toonRondeKaarten().remove(spel.geefLaagsteRondeKaart());
                                    if(!spel.toonRondeKaarten().isEmpty()){
                                        spel.geefSpeler(j).setHeeftBeurtOvergeslaan(true);
                                    }
                                    completed = true;
                                } else {
                                    System.out.printf("%s, wil je je beurt overslaan? (ja of nee)%n", spel.geefSpeler(j).getNaam());
                                    keuze = s.next();
                                    spel.keuzePassenBieden(keuze, j);
                                    if (keuze.equals("ja")){
                                        spel.setAantalBeurtenOvergeslaan(spel.getAantalBeurtenOvergeslaan() + 1 );
                                        System.out.printf("Je krijgt de kaart met waarde: %d%n",spel.toonRondeKaarten().get(spel.geefLaagsteRondeKaart()).getWaarde());
                                        spel.toonRondeKaarten().remove(spel.geefLaagsteRondeKaart());
                                        completed = true;
                                    } else if (keuze.equals("nee")) {                                                      
                                        System.out.printf("Hoeveel munten wil je bieden? (je hebt al %d geboden en het hoogste bod is %d)%n", spel.geefSpeler(j).getGebodenMunten(), spel.geefHoogsteBod());
                                        int bod = s.nextInt() + spel.geefSpeler(j).getGebodenMunten();
                                        spel.bieden(bod, j);
                                        completed = true;
                                    }
                                }
                            }catch(IllegalArgumentException e){
                                System.out.println(e.getMessage());
                            }catch(InputMismatchException e){
                                s.next();
                                System.out.println("Gelieve een getal in te geven.");
                            }     
                        }while (completed == false);
                    }
                    if (!(spel.getAantalBeurtenOvergeslaan() < (spel.getAantalSpelers() - 1 ))) {
                        break;
                    }                                    
            }
            }while ( spel.getAantalSpelers()-1 > spel.getAantalBeurtenOvergeslaan() );
            
            spel.spelersUpdateEindeRonde();
            for (int j = 0; j < spel.getAantalSpelers(); j++) {
                if(spel.geefSpeler(j).getHeeftBeurtOvergeslaan()){
                    System.out.printf("%s heeft nog %d munten%n", spel.geefSpeler(j).getNaam(), spel.geefSpeler(j).getMunten());
                } else if (spel.geefSpeler(j).getHeeftRondeGewonnen()){
                    System.out.printf("%s heeft de kaart met de hoogste waarde gewonnen: %d en heeft nog %d munten %n", spel.geefSpeler(j).getNaam(), spel.toonRondeKaarten().get(0).getWaarde(),spel.geefSpelers().get(j).getMunten());
                    spel.toonRondeKaarten().remove(0);
                }
            }
            spel.resetHoogsteBod();
        }
        System.out.println("Fase 1 van het spel is gedaan. We gaan nu starten aan fase 2");
        
        for (int i = 0; i < (30 / spel.getAantalSpelers()); i++) {
            System.out.printf("Ronde %d:%n", i + 1);
            System.out.printf("Cheques in deze ronde:%n");
            
            spel.voegRondeChequesToe();
            for (int j = 0; j < spel.toonRondeCheques().size(); j++) {
                System.out.printf("Cheque %d: %d%n", j+1, spel.toonRondeCheques().get(j).getWaarde());  
            }
            for (int j = 0; j < spel.getAantalSpelers(); j++) {
                System.out.printf("Speler %s heeft deze huiskaarten: %n", spel.geefSpeler(j).getNaam());
                for (int k = 0; k < spel.geefSpeler(j).getSpelerKaarten().size(); k++){
                    System.out.printf("Huiskaart %d: %d%n", k+1, spel.geefSpeler(j).getSpelerKaarten().get(k).getWaarde());
                }
                completed = false;
                do{
                    int keuze;
                    try{
                        System.out.println("Welke kaart wil je inzetten?(geheel getal van de huiskaart!)");
                        keuze = s.nextInt();
                        spel.kiesKaartInzet(keuze - 1, j);
                        spel.geefSpeler(j).setWaardeGekozenKaart(spel.geefSpeler(j).getGekozenKaart().getWaarde());
                        spel.geefSpeler(j).getSpelerKaarten().remove(keuze - 1);
                        completed = true;
                    }catch( IndexOutOfBoundsException e){
                        System.out.println("Kies een geheel getal van de huiskaarten die je hebt!"); 
                    } catch(InputMismatchException e){
                        System.out.println("Kies een geheel getal van de huiskaarten die je hebt!");
                        s.next();
                    }
                }while(completed == false);
            }
            //voegt de kaart die elke speler gekozen heeft toe aan RondeKaarten
            spel.voegGekozenKaartenToeAanRonde();
            // geeft de laagste cheque aan de speler die de laagste kaart heeft ingezet
            spel.verdeelCheques();
            
            for (int j = 0; j < spel.getAantalSpelers(); j++) {
                System.out.printf("Speler %s krijgt de cheque met waarde %d.%n", spel.geefSpeler(j).getNaam(), spel.geefSpeler(j).getSpelerCheques().get(i).getWaarde());       
            }
        }
        System.out.println("");
        for (int i = 0; i < spel.getAantalSpelers(); i++) {
            spel.geefSpeler(i).telScore();
            System.out.printf("Speler %s heeft %d score.%n", spel.geefSpeler(i).getNaam(), spel.geefSpeler(i).getScore());   
        }
        
        System.out.printf("De speler met de hoogste score en dus ook de winnaar is %s%n", spel.vindWinnaar().getNaam());
               
    }
}


            //
           

        
    

