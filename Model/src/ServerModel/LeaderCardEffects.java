package ServerModel;


public class LeaderCardEffects {

    public LeaderCardEffects() {
    }

    public void SearchEffect(String NameLeader, PlayerAdvanced player) {
        switch (NameLeader){
            case "Francesco Sforza":{
                // raccolto livello 1
                break;
            }
            case "Lodovico Ariosto":{
               player.getLeaderEffectsUsefull().setCanPlaceInOccupiedPosition(true);
               break;
            }
            case "Filippo Brunelleschi":{
                player.getLeaderEffectsUsefull().setHaveToPayWhenTowerIsOccupied(false);
                break;
            }
            case "Sigismondo Malatesta":{
                player.getPedone()[0].setFixedBonus(3);
                break;
            }
            case "Girolamo Savonarola":{
                TrisIE GirolamoEffect = new TrisIE("takeRos","faithPoints",1);
                ImmediateEffects IE = new ImmediateEffects();
                IE.SearchImmediateEffects(GirolamoEffect,player);
                break;
            }
            case "Michelangelo Buonarroti":{
                TrisIE MichelangeloEffect = new TrisIE("takeRos","coins",3);
                ImmediateEffects IE = new ImmediateEffects();
                IE.SearchImmediateEffects(MichelangeloEffect,player);
                break;
            }
            case "Giovanni dalle Bande Nere":{
                TrisIE GiovanniEffect1 = new TrisIE("takeRos","wood",1);
                TrisIE GiovanniEffect2 = new TrisIE("takeRos","coins",1);
                TrisIE GiovanniEffect3 = new TrisIE("takeRos","stone",1);
                ImmediateEffects IE = new ImmediateEffects();
                IE.SearchImmediateEffects(GiovanniEffect1,player);
                IE.SearchImmediateEffects(GiovanniEffect2,player);
                IE.SearchImmediateEffects(GiovanniEffect3,player);
                break;
            }
            case "Leonardo da Vinci":{
                // produzione livello 0
                break;
            }
            case "Sandro Botticelli":{
                TrisIE SandroEffect1 = new TrisIE("takeRos","militaryPoints",2);
                TrisIE SandroEffect2 = new TrisIE("takeRos","victoryPoints",1);
                ImmediateEffects IE = new ImmediateEffects();
                IE.SearchImmediateEffects(SandroEffect1,player);
                IE.SearchImmediateEffects(SandroEffect2,player);
                break;
            }
            case "Ludovico il Moro":{
                player.getPedone()[1].setFixedValue(5);
                player.getPedone()[2].setFixedValue(5);
                player.getPedone()[3].setFixedValue(5);
                break;
            }
            case "Lucrezia Borgia":{
                player.getPedone()[1].setFixedBonus(3);
                player.getPedone()[2].setFixedBonus(3);
                player.getPedone()[3].setFixedBonus(3);
                break;
            }
            case "Federico da Montefeltro":{
               player.getLeaderEffectsUsefull().setOneFamilyMemberIs6(true);
               break;
            }
            case "Lorenzo de' Medici":{
                //chiedi quale carta si vuole giocare tra quelle con isPlayed == true
                break;
            }
            case "Sisto IV":{
                player.getLeaderEffectsUsefull().setFiveVicotryPointsForVaticanReport(true);
                break;
            }
            case "Cesare Borgia":{
                player.getLeaderEffectsUsefull().setDontNeedToSatisfyMilitaryPointsForTerritory(true);
                break;
            }
            case "Santa Rita":{
                player.getLeaderEffectsUsefull().setDoubleResourceFromImmediateEffect(false);
                break;
            }
            case "Cosimo de' Medici":{
                TrisIE CosimoEffect1 = new TrisIE("takeRos","servants",3);
                TrisIE CosimoEffect2 = new TrisIE("takeRos","victoryPoints",1);
                ImmediateEffects IE = new ImmediateEffects();
                IE.SearchImmediateEffects(CosimoEffect1,player);
                IE.SearchImmediateEffects(CosimoEffect2,player);
                break;
            }
            case "Bartolomeo Colleoni":{
                TrisIE BartolomeoEffect1 = new TrisIE("takeRos","victoryPoints",4);
                ImmediateEffects IE = new ImmediateEffects();
                IE.SearchImmediateEffects(BartolomeoEffect1,player);
                break;
            }
            case "Lodovico III Gonzaga":{
                TrisIE LodovicoEffect1 = new TrisIE("takeRos","privilege",1);
                ImmediateEffects IE = new ImmediateEffects();
                IE.SearchImmediateEffects(LodovicoEffect1,player);
                break;
            }
            case "Pico della Mirandola":{
                player.getLeaderEffectsUsefull().setDiscountOf3OnDevelepomentCard(true);
                break;
            }
        }
    }

}