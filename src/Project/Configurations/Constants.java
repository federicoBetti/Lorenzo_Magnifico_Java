package Project.Configurations;


public enum  Constants {


    BUILDING_CARD ("buildingCard"),
    CHARACTER_CARD ("characterCard"),
    VENTURE_CARD ("ventureCard"),
    TERRITORY_CARD("territoryCard");

    String name;

    private Constants(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }
}
