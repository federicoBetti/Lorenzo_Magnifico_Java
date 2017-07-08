package project.controller.effects.effectsfactory;

import project.controller.Constants;
import project.controller.cardsfactory.LeaderCard;
import project.controller.checkfunctions.DontPayForTerritories;
import project.controller.checkfunctions.DontPayMoneyForTower;
import project.controller.checkfunctions.LudovicoAriostoCheck;
import project.controller.checkfunctions.PicoDellaMirandolaCheck;
import project.controller.effects.realeffects.*;
import project.controller.supportfunctions.*;
import project.messages.*;
import project.model.PersonalBoard;
import project.server.network.PlayerHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * class that apply leader card effects
 */
public class LeaderCardsEffects {



    private  HashMap<String, EffectsBuilder> effects;
    private OkOrNo okOrNo;
    private String leaderNameLorenzoMagnifico;

    /**
     * Default constructor
     */
    public LeaderCardsEffects() {
        okOrNo = new OkOrNo();
        effects = new HashMap<>();
        leaderNameLorenzoMagnifico = null;
        fillHashMapRequirements();
    }

    private interface EffectsBuilder{
        BonusInteraction doEffect(PlayerHandler player);
    }

    public BonusInteraction doEffect(String leaderName, PlayerHandler player){
        return effects.get(leaderName).doEffect(player);
    }

    private void fillHashMapRequirements() {
        effects.put(Constants.FRANCESCO_SFORZA,this::francescoSforza);
        effects.put(Constants.LUDVICO_ARIOSTO,this::ludovicoAriosto);
        effects.put(Constants.FILIPPO_BRUNELLESCHI,this::filippoBrunelleschi);
        effects.put(Constants.SIGISMONDO_MALATESTA,this::sigismondoMalatesta);
        effects.put(Constants.GIROLAMO_SAVONAROLA,this::girolamoSavonarola);
        effects.put(Constants.MICHELANGELO_BUONARROTI,this::michelangeloBuonarroti);
        effects.put(Constants.GIOVANNI_DALLE_BANDE_NERE,this::giovanniDalleBandeNere);
        effects.put(Constants.LEONARDO_DA_VINCI,this::leonardoDaVinci);
        effects.put(Constants.SANDRO_BOTTICELLI,this::sandroBotticelli);
        effects.put(Constants.LUDOVICO_IL_MORO,this::ludovicoIlMoro);
        effects.put(Constants.LUCREZIA_BORGIA,this::lucreziaBorgia);
        effects.put(Constants.FEDERICO_DA_MONTEFELTRO,this::federicoDaMontefeltro);
        effects.put(Constants.LORENZO_DE_MEDICI,this::lorenzoDeMedici);
        effects.put(Constants.SISTO_IV,this::sistoIV);
        effects.put(Constants.CESARE_BORGIA,this::cesareBorgia);
        effects.put(Constants.SANTA_RITA,this::santaRita);
        effects.put(Constants.COSIMO_DE_MEDICI,this::cosimoDeMedici);
        effects.put(Constants.BARTOLOMEO_CORLEONI,this::bartolometoColleoni);
        effects.put(Constants.LUDOVICO_III_GONZAGA,this::ludovicoIIIGonzaga);
        effects.put(Constants.PICO_DELLA_MIRANDOLA,this::picoDellaMirandola);
    }



    private BonusInteraction picoDellaMirandola(PlayerHandler player) {
        player.setCheckFunctions(new PicoDellaMirandolaCheck(player.getCheckFunctions()));
        player.getRoom().setMySupportFunction(new PicoDellaMirandolaSupport(player.getRoom().getMySupportFunction(player)),player);
        return okOrNo;
    }

    private BonusInteraction ludovicoIIIGonzaga(PlayerHandler player) {
        return new TakePrivilegesAction(1);
    }

    private BonusInteraction bartolometoColleoni(PlayerHandler player) {
        Effects effects = new AddVictoryPoints(4);
        effects.doEffect(player);
        return okOrNo;
    }

    private BonusInteraction cosimoDeMedici(PlayerHandler player) {
        Effects effects = new AddVictoryPoints(1);
        effects.doEffect(player);
        effects = new AddServants(3);
        effects.doEffect(player);
        return okOrNo;
    }

    private BonusInteraction santaRita(PlayerHandler player) {
        player.getRoom().setMySupportFunction(new SantaRita(player.getRoom().getMySupportFunction(player)),player);
        return okOrNo;
    }

    private BonusInteraction cesareBorgia(PlayerHandler player) {
        player.setCheckFunctions(new DontPayForTerritories(player.getCheckFunctions()));
        return okOrNo;
    }

    private BonusInteraction sistoIV(PlayerHandler player) {
        player.getRoom().setMySupportFunction(new FivePointsMoreForPray(player.getRoom().getMySupportFunction(player)),player);
        return okOrNo;
    }

    private BonusInteraction lorenzoDeMedici(PlayerHandler player) {
        List<LeaderCard> leaderPlayed = new ArrayList<>();
        for (PlayerHandler p: player.getRoom().getListOfPlayers()){
            for (LeaderCard l: p.getPersonalBoardReference().getMyLeaderCard())
                if (l.isRequirementsSatisfied())
                    leaderPlayed.add(l);
        }
        leaderNameLorenzoMagnifico = player.leaderCardChosen(leaderPlayed);
        if (leaderNameLorenzoMagnifico.equals(-1))
            return null;

        LeaderCard newLeader = null;
        for (LeaderCard l: leaderPlayed)
            if (l.getName().equals(leaderNameLorenzoMagnifico))
                newLeader = l;

        LeaderCard lorenzo = null;
        for (LeaderCard l: player.getPersonalBoardReference().getMyLeaderCard())
            if (l.getName().equals(Constants.LORENZO_DE_MEDICI))
                lorenzo = l;

        player.getPersonalBoardReference().getMyLeaderCard().remove(lorenzo);
        player.getPersonalBoardReference().getMyLeaderCard().add(newLeader);


        return doEffect(leaderNameLorenzoMagnifico,player);
    }

    private BonusInteraction federicoDaMontefeltro(PlayerHandler player) {
        player.getRoom().setMySupportFunction(new FedericoDaMontefeltroSupport(player.getRoom().getMySupportFunction(player)),player);
        return okOrNo;
    }

    private BonusInteraction lucreziaBorgia(PlayerHandler player) {
        player.getAllFamilyMembers()[1].setFixedBonus(2);
        player.getAllFamilyMembers()[2].setFixedBonus(2);
        player.getAllFamilyMembers()[3].setFixedBonus(2);
        return okOrNo;
    }

    private BonusInteraction ludovicoIlMoro(PlayerHandler player) {
        player.getAllFamilyMembers()[1].setFixedValue(5);
        player.getAllFamilyMembers()[2].setFixedValue(5);
        player.getAllFamilyMembers()[3].setFixedValue(5);
        return okOrNo;
    }

    private BonusInteraction sandroBotticelli(PlayerHandler player) {
        Effects effects = new AddVictoryPoints(1);
        effects.doEffect(player);
        effects = new AddMilitaryPoints(2);
        effects.doEffect(player);
        return okOrNo;
    }

    private BonusInteraction leonardoDaVinci(PlayerHandler player) {
        return new BonusProductionOrHarvesterAction(Constants.PRODUCTION,0);
    }

    private BonusInteraction giovanniDalleBandeNere(PlayerHandler player) {
        player.getPersonalBoardReference().addWood(1);
        player.getPersonalBoardReference().addStone(1);
        player.getPersonalBoardReference().addCoins(1);
        return okOrNo;
    }

    private BonusInteraction michelangeloBuonarroti(PlayerHandler player) {
        player.getPersonalBoardReference().addCoins(3);
        return okOrNo;
    }

    private BonusInteraction girolamoSavonarola(PlayerHandler player) {
        Effects effects = new AddFaithPoints(1);
        effects.doEffect(player);
        return okOrNo;
    }

    private BonusInteraction sigismondoMalatesta(PlayerHandler player) {
        player.getAllFamilyMembers()[0].setFixedBonus(3);
        return okOrNo;
    }

    private BonusInteraction filippoBrunelleschi(PlayerHandler player) {
        player.setCheckFunctions(new DontPayMoneyForTower(player.getCheckFunctions()));
        return okOrNo;
    }

    private BonusInteraction francescoSforza(PlayerHandler player) {
        return new BonusProductionOrHarvesterAction(Constants.HARVESTER,1);
    }



    private BonusInteraction ludovicoAriosto(PlayerHandler player){
        player.setCheckFunctions(new LudovicoAriostoCheck(player.getCheckFunctions()));
        player.getRoom().setMySupportFunction(new LudovicoAriostoSupport(player.getRoom().getMySupportFunction(player)),player);
        return okOrNo;
    }


    private PersonalBoard personalBoard(PlayerHandler player){
        return player.getPersonalBoardReference();
    }
}
