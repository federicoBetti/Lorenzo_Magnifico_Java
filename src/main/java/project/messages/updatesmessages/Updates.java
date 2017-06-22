package project.messages.updatesmessages;

import com.diogonunes.jcdp.color.api.Ansi;
import project.PrinterClass.UnixColoredPrinter;
import project.messages.BonusInteraction;
import project.model.Board;
import project.model.FamilyMember;
import project.model.PersonalBoard;
import project.model.Score;

/**
 * this represent the abstract class of updates. its extenctions will inform every client of some moves
 */
public abstract class Updates extends BonusInteraction {

    UnixColoredPrinter pBlue;
    UnixColoredPrinter pRed;

    public Updates() {
        UnixColoredPrinter.Builder builder = new UnixColoredPrinter.Builder(0, false);
        builder.attribute(Ansi.Attribute.BOLD);
        builder.foreground(Ansi.FColor.BLUE);
        pBlue = new UnixColoredPrinter(builder);

        UnixColoredPrinter.Builder builder1 = new UnixColoredPrinter.Builder(0, false);
        builder.attribute(Ansi.Attribute.BOLD);
        builder.foreground(Ansi.FColor.RED);
        pRed = new UnixColoredPrinter(builder1);
    }

    public void doUpdate(Board board){}
    public void doUpdate( PersonalBoard personalBoard ){}
    public void doUpdate(Score score){}
    public void doUpdate(FamilyMember[] familyMembersUi){}
    public abstract String toScreen();
}
