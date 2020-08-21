package menu;

import modelli.ListaSensori;
import utility.InputDati;
import utility.MyMenu;

public class MenuFruitore {
    final private static String TITOLO = "Menu fruitore";
    final private static String [] VOCIMENU = {"Visualizza dati sensori"};
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
    
        case 1: // Visualizza dati rilevati sensori (previa verifica della presenza di tali)
          if(ListaSensori.getInstance().isEmptyList()){
            System.out.println("Non sono presenti sensori al momento");
          } else{
            //stampa i valori rilvevati (random) dai sensori
            ListaSensori.getInstance().printListValues();
          }
   
          break;
      }
  
      return false;
  
    }
    
}