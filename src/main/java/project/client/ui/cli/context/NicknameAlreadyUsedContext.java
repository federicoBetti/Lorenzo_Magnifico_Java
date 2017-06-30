package project.client.ui.cli.context;

import project.client.ui.cli.Cli;
import project.client.ui.cli.InputException;

import java.io.IOException;

/**
 * Created by raffaelebongo on 29/06/17.
 */
public class NicknameAlreadyUsedContext extends AbstractContext {
    public NicknameAlreadyUsedContext(Cli cli) {
        super(cli);
        printHelp();
    }

    @Override
    public void printHelp() {
        pRed.println("Nickanme already used. Please type another nickname.");
    }

    @Override
    public void checkValidInput(String input) throws InputException {

    }

    @Override
    protected void mainContextMethod(String nickname) throws InputException, IOException {
        cli.newNickname(nickname);
    }
}
