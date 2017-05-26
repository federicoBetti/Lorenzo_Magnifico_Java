package Project.Controller.SupportFunctions;


import Project.Server.Network.PlayerHandler;

public interface SupportFunctionsDecorator extends AllSupportFunctions {
    int extraLostOfPoints(PlayerHandler playerHandler);
}
