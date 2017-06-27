package project.controller.effects.effectsfactory;

import project.controller.Constants;
import project.controller.checkfunctions.DontPayForTerritories;
import project.controller.checkfunctions.DontPayMoneyForTower;
import project.controller.checkfunctions.LudovicoAriostoCheck;
import project.controller.checkfunctions.PicoDellaMirandolaCheck;
import project.controller.effects.realeffects.*;
import project.controller.supportfunctions.FivePointsMoreForPray;
import project.controller.supportfunctions.LudovicoAriostoSupport;
import project.controller.supportfunctions.PicoDellaMirandolaSupport;
import project.controller.supportfunctions.SantaRita;
import project.messages.*;
import project.model.PersonalBoard;
import project.server.network.PlayerHandler;

import java.util.HashMap;


/**
 * Created by federico on 17/06/17.
 */
public class LeaderCardsEffects {



    private  HashMap<String, EffectsBuilder> effects;
    private OkOrNo okOrNo;
    /**
     * Default constructor
     */
    public LeaderCardsEffects() {
        okOrNo = new OkOrNo();
        effects = new HashMap<>();
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
        Effects effects = new AddVicotryPoints(4);
        effects.doEffect(player);
        return okOrNo;
    }

    private BonusInteraction cosimoDeMedici(PlayerHandler player) {
        Effects effects = new AddVicotryPoints(1);
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
        return new LorenzoMagnifico();
    }

    private BonusInteraction federicoDaMontefeltro(PlayerHandler player) {
        //todo
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
        Effects effects = new AddVicotryPoints(1);
        effects.doEffect(player);
        effects = new AddMilitaryPoints(2);
        effects.doEffect(player);
        return okOrNo;
    }

    private BonusInteraction leonardoDaVinci(PlayerHandler player) {
        return new BonusProductionOrHarvesterAction(Constants.PRODUCTION,0);
    }

    private BonusInteraction giovanniDalleBandeNere(PlayerHandler player) {
        Effects effects = new AddCoin(1);
        effects.doEffect(player);
        effects = new AddWood(1);
        effects.doEffect(player);
        effects = new AddStone(1);
        effects.doEffect(player);
        return okOrNo;
    }

    private BonusInteraction michelangeloBuonarroti(PlayerHandler player) {
        Effects effects = new AddCoin(3);
        effects.doEffect(player);
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
