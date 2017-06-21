package project.messages;

import java.io.Serializable;

public class LeaderEffectsUsefull {
	
    private boolean canPlaceInOccupiedPosition = false;

    private boolean haveToPayWhenTowerIsOccupied = true;

    private boolean oneFamilyMemberIs6 = false;

    private boolean fiveVicotryPointsForVaticanReport;

    private boolean dontNeedToSatisfyMilitaryPointsForTerritory = false;

    private boolean doubleResourceFromImmediateEffect = true;

    private boolean discountOf3OnDevelepomentCard = false;

    public boolean isCanPlaceInOccupiedPosition() {
        return canPlaceInOccupiedPosition;
    }

    public void setCanPlaceInOccupiedPosition(boolean canPlaceInOccupiedPosition) {
        this.canPlaceInOccupiedPosition = canPlaceInOccupiedPosition;
    }
    public boolean isHaveToPayWhenTowerIsOccupied() {
        return haveToPayWhenTowerIsOccupied;
    }

    public void setHaveToPayWhenTowerIsOccupied(boolean haveToPayWhenTowerIsOccupied) {
        this.haveToPayWhenTowerIsOccupied = haveToPayWhenTowerIsOccupied;
    }

    public boolean isOneFamilyMemberIs6() {
        return oneFamilyMemberIs6;
    }

    public void setOneFamilyMemberIs6(boolean oneFamilyMemberIs6) {
        this.oneFamilyMemberIs6 = oneFamilyMemberIs6;
    }

    public boolean isFiveVicotryPointsForVaticanReport() {
        return fiveVicotryPointsForVaticanReport;
    }

    public void setFiveVicotryPointsForVaticanReport(boolean fiveVicotryPointsForVaticanReport) {
        this.fiveVicotryPointsForVaticanReport = fiveVicotryPointsForVaticanReport;
    }

    public boolean isDontNeedToSatisfyMilitaryPointsForTerritory() {
        return dontNeedToSatisfyMilitaryPointsForTerritory;
    }

    public void setDontNeedToSatisfyMilitaryPointsForTerritory(boolean dontNeedToSatisfyMilitaryPointsForTerritory) {
        this.dontNeedToSatisfyMilitaryPointsForTerritory = dontNeedToSatisfyMilitaryPointsForTerritory;
    }

    public boolean isDoubleResourceFromImmediateEffect() {
        return doubleResourceFromImmediateEffect;
    }

    public void setDoubleResourceFromImmediateEffect(boolean doubleResourceFromImmediateEffect) {
        this.doubleResourceFromImmediateEffect = doubleResourceFromImmediateEffect;
    }

    public boolean isDiscountOf3OnDevelepomentCard() {
        return discountOf3OnDevelepomentCard;
    }

    public void setDiscountOf3OnDevelepomentCard(boolean discountOf3OnDevelepomentCard) {
        this.discountOf3OnDevelepomentCard = discountOf3OnDevelepomentCard;
    }
}
