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
    public static final int NUMBER_OF_TOWERS = 4;
    public static final int CARD_FOR_EACH_TOWER = 4;
    public static final int NUMBER_OF_FLOORS = 4;

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

    public static final String LOGIN_SUCCEDED = "fileXML.login-succeded";
    public static final String YOUR_TURN = "your-turn";

    //network constants
    public static final String LOGIN_REQUEST = "login-request";
    public static final String TAKE_DEV_CARD = "take-dev-card";
    public static final String CHOOSE_PAYMENT_FOR_VENTURE_CARD = "choose-Payment-Venture-card";
    public static final String STOP = "stop";
    public static final String GO_TO_MARKET ="go-to-market";
    public static final String JUMP_TURN ="jump-turn";
    public static final String PLAY_LEADER_CARD ="play-leader-card";
    public static final String DISCARD_LEADER_CARD = "discard-leader-card";
    public static final String ROLL_DICES = "roll-dices";
    public static final String GO_TO_COUNCIL_PALACE ="go-to-council";
    public static final String TAKE_PRIVILEDGE ="take-priviledge";
    public static final String PRAY ="pray";
    public static final String DONT_PRAY ="dont-pray";
    public static final String ASK_FOR_PRAYING ="do-you-want-to-pray?";
    public static final String BOTH_PAYMENT_METHODS_AVAILABLE="both-payment-methods-available";
    public static final String LOCAL_ADDRESS = "127.0.0.1";

    public static final String FRANCESCO_SFORZA = "francescoSforza";
    public static final String LUDVICO_ARIOSTO = "ludovicoAriosto";
    public static final String FILIPPO_BRUNELLESCHI = "filippoBrunelleschi";
    public static final String SIGISMONDO_MALATESTA = "sigismondoMalatesta";
    public static final String GIROLAMO_SAVONAROLA = "girolamoSavonarola";
    public static final String MICHELANGELO_BUONARROTI = "michelangeloBuonarroti";
    public static final String GIOVANNI_DALLE_BANDE_NERE = "giovanniDalleBandeNere";
    public static final String LEONARDO_DA_VINCI = "leonardoDaVinci";
    public static final String SANDRO_BOTTICELLI = "sandroBotticelli";
    public static final String LUDOVICO_IL_MORO = "ludovicoIlMoro";
    public static final String LUCREZIA_BORGIA = "lucreziaBorgia";
    public static final String FEDERICO_DA_MONTEFELTRO = "federicoDaMontefeltro";
    public static final String LORENZO_DE_MEDICI = "lorenzoDeMedici";
    public static final String SISTO_IV = "sistoIV";
    public static final String CESARE_BORGIA = "cesareBorgia";
    public static final String SANTA_RITA = "santaRita";
    public static final String COSIMO_DE_MEDICI = "cosimoDeMedici";
    public static final String BARTOLOMEO_CORLEONI = "bartolomeoCorleoni";
    public static final String LUDOVICO_III_GONZAGA = "ludovicoIIIGonzaga";
    public static final String PICO_DELLA_MIRANDOLA = "picoDellaMirandola";

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
    public static final String BONUS_PRODUCTION = "bonus-production";
    public static final String BONUS_HARVESTER = "bonus-harvester" ;
    public static final String SKIP_TURN = "skip-turn" ;
    public static final String PLAY_LEADER_CARD_AMA = "play-leader-card-ama";
    public static final String DISCARD_LEADER_CARD_AMA = "discard-leader-card-ama";
    public static final String THIRD_FLOOR = "3";
    public static final String SECOND_FLOOR = "2";
    public static final String FIRST_FLOOR = "1";
    public static final String GROUND_FLOOR = "0";
    public static final String ALL_COLOURS = "all-colours";
    public static final String NICKNAME_USED = "nickname-already-used";
    public static final int PRIVILEDGE_NUMBER = 5;
    public static final int FAITH_TRACK = 15;
    public static final int FOUR_PLAYERS = 4;
    public static final int TWO_PLAYERS = 2;
    public static final String WOOD = "wood";
    public static final String COIN = "coin";
    public static final String STONE = "stone";
    public static final String TIMER_TURN_DELAYED = "timer-turn-delayed";
    public static final String GREEN = "green";
    public static final int NUMBER_OF_FAMILIAR = 4;
    public static final String TO_EVERYONE = "everyone";
    public static final String UPDATE = "update";
    public static final int LEADER_CARD_NUMBER_PER_PLAYER = 4;
    public static final String MATCH_STARTED = "match-started";
    public static final String TILE_DRAFT = "tile-draft";
    public static final String LEADER_DRAFT = "leader-draft";
    public static final Object BONUS_CARD_CHOOSEN = "bonus-card-choosen";
    public static final String PRAYED = "prayed";
    public static final String EXCOMMUNICATION_TAKEN_UPDATE = "excommunication-taken";
    public static final String PRAYING_REQUEST_RECEIVED = "praying-request-received";
    public static final String ASK_FOR_PRAYING_LAST_PLAYER = "ask-for-praying-last-player";
    public static final String ACTION_DONE_ON_TIME = "action-done-on-time";
    public static final String RECONNECT = "reconnect";
    public static final String WAITING_FOR_YOUR_TURN = "waiting-for-your-turn";
    public static final String AFTER_GAME = "after-game";
    public static final String SHOW_STATISTICS = "show-statistics";
    public static final String FILENAME = "E:\\test\\PlayerFile.json";
    public static String TOWER_ACTION_YELLOW = "tower-action-yellow";
    public static final int CANT_USE_ANY_PAYMENT = 0;
    public static final int CAN_USE_BOTH_PAYMENT_METHOD = 3;
    public static final int MALUS_PROD_HARV = 3;
    public static final String MARKET = "market";
    public static int LEADER_CARD_NUMBER = 20;
    public static int EXCOMMUNICATION_CARD_NUMBER_PER_PERIOD = 7;
    public static int NUMBER_OF_TILES = 4;
    public static String RED = "red";
    public static String YELLOW ="yellow";
    public static String BLUE ="blue";
    public static String SHOW_RANKING  =  "show-ranking";


    private Constants(){}
}
