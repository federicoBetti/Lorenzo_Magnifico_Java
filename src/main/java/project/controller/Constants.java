package project.controller;

/**
 * some constants used in the software
 */
public class Constants {

    public static final String TERRITORY_CARD = "territoryCard"; //0
    public static final String CHARACTER_CARD = "characterCard"; //1
    public static final String BUILDING_CARD = "buildingCard"; //2
    public static final String VENTURE_CARD = "ventureCard"; //3
    public static final int CARD_FOR_EACH_PERIOD = 8;
    public static final int PERIOD_NUMBER = 3;
    public static final int CARD_TYPE_NUMBER = 4;
    public static final int NNUMBER_OF_TOWERS = 4;
    public static final int CARD_FOR_EACH_TOWER = 4;

    public static final int ADD_COINS_IF_TOWER_IS_OCCUPIED = 3;
    public static final String COLOUR_OF_TOWER_WITH_VENTURES_CARD = "purple";
    public static final String COLOUR_OF_TOWER_WITH_BUILDING_CARD = "yellow";
    public static final String COLOUR_OF_TOWER_WITH_TERRITORY_CARD = "green";
    public static final String COLOUR_OF_TOWER_WITH_CHARACTER_CARD = "blue";
    public static final String PRODUCTION = "production";
    public static final String HARVESTER = "harvester";
    public static final String FAMILY_MEMBER_COLOUR_NEUTRAL = "neutral";
    public static final String FAMILY_MEMBER_COLOUR_BLACK = "black";
    public static final String FAMILY_MEMBER_COLOUR_ORANGE = "orange";
    public static final String FAMILY_MEMBER_COLOUR_WHITE = "white";

    public static final int SOCKET_PORT = 1337;
    public static final int RMI_PORT = 1338;

    public static final String LOGIN_SUCCEDED = "fileXML.controller-succeded";
    public static final String YOUR_TURN = "your-turn";

    //network constants
    public static final String LOGIN_REQUEST = "fileXML.controller-request";
    public static final String TAKE_DEV_CARD = "client-Take-Developement-Card";
    public static final String CHOOSE_PAYMENT_FOR_VENTURE_CARD = "choose-Payment-Venture-card";
    public static final String STOP = "stop";
    public static final String GO_TO_MARKET ="go-to-market";
    public static final String JUMP_TURN ="jump-turn";
    public static final String PLAY_LEADER_CARD ="play-leader-card";
    public static final String DISCARD_LEADER_CARD = "discard-leader-card";
    public static final String ROLL_DICES = "roll-dices";
    public static final String GO_TO_COUNCIL_PALACE ="go-to-council-palace-request";
    public static final String TAKE_PRIVILEDGE ="take-priviledge";
    public static final String PRAY ="pray";
    public static final String DONT_PRAY ="dont-pray";
    public static final String ASK_FOR_PRAYING ="do-you-want-to-pray?";
    public static final String BOTH_PAYMENT_METHODS_AVAILABLE="both-payment-methods-available";
    public static final String LOCAL_ADDRESS = "127.0.0.1";

    public static final String FRANCESCO_SFORZA = "francesco-sforza";
    public static final String LUDVICO_ARIOSTO = "ludovico-ariosto";
    public static final String FILIPPO_BRUNELLESCHI = "filippo-brunelleschi";
    public static final String SIGISMONDO_MALATESTA = "sigismondo-malatesta";
    public static final String GIROLAMO_SAVONAROLA = "girolamo-savonarola";
    public static final String MICHELANGELO_BUONARROTI = "michelangelo-buonarroti";
    public static final String GIOVANNI_DALLE_BANDE_NERE = "giovanni-dalle-bande-nere";
    public static final String LEONARDO_DA_VINCI = "leonardo-da-vinci";
    public static final String SANDRO_BOTTICELLI = "sandro-botticelli";
    public static final String LUDOVICO_IL_MORO = "ludovico-il-moro";
    public static final String LUCREZIA_BORGIA = "lucrezia-borgia";
    public static final String FEDERICO_DA_MONTEFELTRO = "federico-da-montefeltro";
    public static final String LORENZO_DE_MEDICI = "lorenzo-de-medici";
    public static final String SISTO_IV = "sisto-iv";
    public static final String CESARE_BORGIA = "cesare-borgia";
    public static final String SANTA_RITA = "santa-rita";
    public static final String COSIMO_DE_MEDICI = "cosimo-de-medici";
    public static final String BARTOLOMEO_CORLEONI = "bartolomeo-corleoni";
    public static final String LUDOVICO_III_GONZAGA = "ludovico-iii-gonzaga";
    public static final String PICO_DELLA_MIRANDOLA = "pico-della-mirandola";



    public static final String TAKE_PRIVILEGE_ACTION = "take-privilege-action";
    public static final String TOWER_ACTION = "tower-action";
    public static final String BONUS_PRODUCTION_HARVESTER_ACTION = "bonus-production-harvester-action";
    public static final String OK_OR_NO = "ok-or-no";
    public static final String CHOICE_PE = "choice-pe";
    public static final String NOTIFY = "notify" ;
    public static final String FAMILY_MEMBER_UPDATE = "family-member-update";
    public static final String PERSONAL_BOARD_UPDATE = "personal-board-update";
    public static final String SCORE_UPDATE = "score-update";
    public static final String BOARD_UPDATE = "board-update";
    public static final String CANT_DO_ACTION = "cant-do-action";
    public static final String EXIT = "exit";
    public static final String NOT_ENOUGH_RESOURCES = "not-enough-resources";
    public static final int CANT_USE_ANY_PAYMENT = 0;
    public static final int CAN_USE_BOTH_PAYMENT_METHOD = 3;
    public static final int MALUS_PROD_HARV = 3;
    public static final String MARKET = "market";


    private Constants(){}
}
