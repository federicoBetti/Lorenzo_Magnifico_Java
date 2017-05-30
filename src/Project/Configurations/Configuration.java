package Project.Configurations;

import Project.Controller.CardsFactory.*;
import Project.Controller.Constants;
import Project.Controller.ConstructorHandler;
import Project.Controller.CardsFactory.LoadCards;
import Project.Controller.RequestHandler;

/**
 * Created by raffaelebongo on 18/05/17.
 */
public class Configuration {

    LoadCards loadCards;
    Constants constants;
    BuildingCard buildingCard;
    CharacterCard characterCard;
    TerritoryCard territoryCard;
    VenturesCard venturesCard;
    /**
     * prende crea un parser che prende in ingresso il file Json e lo parsa creando le carte chiamando la Factory
     * di immediate Effect nel controller. Possiamo usare anche tanti file per le diverse cose o creare metodi diversi
     * per le varie cose da caricare
     */
    public void loadConfiguration(){
        loadCards = new LoadCards();
        constants = new Constants();


    }
}
