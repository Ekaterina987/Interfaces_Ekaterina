package ch.makery.address.view.eventosValidar;


import ch.makery.address.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

public class EventosController {
	private Main main;

    @FXML
    private TextField label;

    @FXML
    void mostrarDialogo(ActionEvent event) {
    	main.mostrarDialogo(label);
    }
    public void setMain(Main main) {
    	this.main = main;
    }

    @FXML
    void initialize() {


    }

}
