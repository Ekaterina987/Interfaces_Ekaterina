package ch.makery.address.flowpanecss;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
public class FlowpaneController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
