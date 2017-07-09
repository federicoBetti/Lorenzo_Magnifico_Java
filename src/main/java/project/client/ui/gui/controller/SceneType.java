package project.client.ui.gui.controller;

import javafx.scene.layout.AnchorPane;

/**
 * enum of scene types. useful to switch between scenes. for each scene is saved it's controller and it's anchor pae
 */
public enum SceneType {
    MAIN,
    TOWERS,
    MARKET, HARVESTER, PERSONAL_BOARD, PRODUCTION, COUNCIL, LEADER;

    private AbstractController controller;
    private AnchorPane scene;

    /**
     * getter
     * @return scene's controller
     */
    public AbstractController getController() {
        return controller;
    }

    /**
     * setter
     * @param controller scene's controller
     */
    void setController(AbstractController controller) {
        this.controller = controller;
    }

    /**
     * getter
     * @return scene's anchor pane
     */
    public AnchorPane getScene() {
        return scene;
    }

    /**
     * setter
     * @param scene scene's anchor pane
     */
    void setScene(AnchorPane scene) {
        this.scene = scene;
    }
}
