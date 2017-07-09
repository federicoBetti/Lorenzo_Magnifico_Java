package project.client.ui.gui.controller;

import javafx.scene.layout.AnchorPane;

/**
 * enum of scene types
 */
public enum SceneType {
    MAIN,
    TOWERS,
    MARKET, HARVESTER, PERSONAL_BOARD, PRODUCTION, COUNCIL, LEADER;

    private AbstractController controller;
    private AnchorPane scene;

    public AbstractController getController() {
        return controller;
    }

    public void setController(AbstractController controller) {
        this.controller = controller;
    }

    public AnchorPane getScene() {
        return scene;
    }

    public void setScene(AnchorPane scene) {
        this.scene = scene;
    }
}
