package ServerModel;


public class ExcommunicationCardsEffects {
    public ExcommunicationCardsEffects() {
    }


    public Effects SearchImmediateEffects(String type, String parameter, int quantity) {

        switch (type) {
            case "reduceValueOfAction":
                return new ReduceValueOnAction(parameter, quantity);

            case "setExcommunicationCarduseful":
                return new SetExcommunicationCardUseful(parameter);
        }


        return null;
    }
}