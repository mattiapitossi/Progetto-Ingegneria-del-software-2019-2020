package view;

import java.util.regex.Pattern;

import model.CategoriaSensori;
import model.ListaCategoriaSensori;
import utility.InputDati;
import utility.MyMenu;

public class MenuManutentore {
    final private static String TITOLO = "Sistema domotico";
    final private static String [] VOCIMENU = {"Crea categoria sensore", "Crea nuova categoria attuatore", "Crea nuovo sensore (richiede la presenza di almeno una categoria)"};
    final private static String MESS_USCITA = "Vuoi veramente uscire ?";
    final private static String ERRORE_FUNZIONE = "La funzione non rientra tra quelle disponibili !";
    final private static String MESS_ALTRA_OPZIONE = "Selezionare un'altra opzione.";
    


    public void esegui(){
      MyMenu menuMain = new MyMenu(TITOLO, VOCIMENU);
      boolean fineProgramma = false;
      do{
        int selezione = menuMain.scegli();
        fineProgramma = eseguiFunzioneScelta(selezione);
	    } while (!fineProgramma);
    }
    

    public boolean eseguiFunzioneScelta(int numFunzione) 
    {
   
      switch (numFunzione) {
        case 0: // Esci
          return InputDati.yesOrNo(MESS_USCITA);
          //break; // ! Superfluo e non solo ... (non compila)
    
        case 1: // Crea nuova categoria sensore
            String nomeCategoriaSensori;
            do{
                nomeCategoriaSensori = InputDati.leggiStringaNonVuota("Inserisci il nome della categoria dei sensori: ");
                if(ListaCategoriaSensori.getInstance().alreadyExists(nomeCategoriaSensori)){
                    System.out.println("Attenzione! Il nome di questa categoria è già presente!");
                }
            } while(ListaCategoriaSensori.getInstance().alreadyExists(nomeCategoriaSensori));
            String descrizioneCategoriaSensori = InputDati.leggiStringa("Inserisci una descrizione (facoltativa): ");
            //per questa versione una singola variabile fisica
            String variabileFisicaLetta = InputDati.leggiStringaNonVuota("Inserisci la variabile fisica che verrà rilevata: ");
            CategoriaSensori categoriaCreata = new CategoriaSensori(nomeCategoriaSensori, descrizioneCategoriaSensori, variabileFisicaLetta);
            ListaCategoriaSensori.getInstance().addToList(nomeCategoriaSensori, categoriaCreata);
            
            break;
    
        case 2: // Crea nuova categoria attuatore
          break;

        case 3: 
        /*
         Crea nuovo sensore (solo se esiste almeno una categoria di sensore 
         e può essere associato solo a stanze che non abbiano già il medesimo sensore)
        */
            //espressione regolare per il formato richiesto
            final Pattern pattern = Pattern.compile("[A-Za-z]+_[A-Za-z]+");
            String nomeSensore;
            do{
                nomeSensore = InputDati.leggiStringaNonVuota("Inserisci nome sensore (formato: nome_categoriadelsensore): ");
                if (!pattern.matcher(nomeSensore).matches()) {
                    System.out.println("Il nome del sensore non è nel formato corretto!");
                }
            } while(!pattern.matcher(nomeSensore).matches());
            break;

        case 4: // Crea nuovo attuatore (solo se esiste almeno una categoria di attuatore)
          break;
    
        default: // Se i controlli nella classe Menu sono corretti, questo non viene mai eseguito !
          System.out.println(ERRORE_FUNZIONE);
          System.out.println(MESS_ALTRA_OPZIONE);
          break;
      }
  
      return false;
  
    }
    
}