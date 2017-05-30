package Project.Controller;

/**
 * some constants used in the software
 */
public class Constants {

    public final static String TERRITORY_CARD = "territoryCard"; //0
    public final static String CHARACTER_CARD = "characterCard"; //1
    public final static String BUILDING_CARD = "buildingCard"; //2
    public final static String VENTURE_CARD = "ventureCard"; //3
    public final static int CARD_FOR_EACH_PERIOD = 8;
    public final static int PERIOD_NUMBER = 3;
    public final static int CARD_TYPE_NUMBER = 4;
    public final static int NNUMBER_OF_TOWERS = 4;
    public final static int CARD_FOR_EACH_TOWER = 4;

    public static final int ADD_COINS_IF_TOWER_IS_OCCUPIED = 3;
    public static final String COLOUR_OF_TOWER_WITH_VENTURES_CARD = "purple";
    public static final String COLOUR_OF_TOWER_WITH_BUILDING_CARD = "yellow";
    public static final String COLOUR_OF_TOWER_WITH_TERRITORY_CARD = "green";
    public static final String COLOUR_OF_TOWER_WITH_CHARACTER_CARD = "blue";
    public static final String PRODUCTION = "production";
    public static final String HARVESTER = "harvester";
    public static String FAMILY_MEMBER_COLOUR_NONE = "none";
    public static String FAMILY_MEMBER_COLOUR_BLACK = "black";
    public static String FAMILY_MEMBER_COLOUR_ORANGE = "orange";
    public static String FAMILY_MEMBER_COLOUR_WHITE = "white";

    public final static int SOCKET_PORT = 1337;
    public final static int RMI_PORT = 1338;

    public static final String LOGIN_SUCCEDED = "login-succeded";
    public static final String YOUR_TURN = "your-turn";

    //Network constants
    public static final String LOGIN_REQUEST = "login-request";
    public static final String TAKE_DEV_CARD = "client-Take-Developement-Card";
    public static String CHOOSE_PAYMENT_FOR_VENTURE_CARD = "choose-Payment-Venture-card";
    public static String STOP = "stop";
    public static String GO_TO_MARKET ="go-to-market";
    public static String JUMP_TURN ="jump-turn";
    public static String PLAY_LEADER_CARD ="play-leader-card";
    public static String DISCARD_LEADER_CARD = "discard-leader-card";
    public static String ROLL_DICES = "roll-dices";
    public static String GO_TO_COUNCIL_PALACE ="go-to-council-palace-request";
    public static String TAKE_PRIVILEDGE ="take-priviledge";
    public static String PRAY ="pray";
    public static String DONT_PRAY ="dont-pray";
    public static String ASK_FOR_PRAYING ="do-you-want-to-pray?";
    public static String BOTH_PAYMENT_METHODS_AVAILABLE="both-payment-methods-available";
    public static String LOCAL_ADDRESS = "127.0.0.1";
}
