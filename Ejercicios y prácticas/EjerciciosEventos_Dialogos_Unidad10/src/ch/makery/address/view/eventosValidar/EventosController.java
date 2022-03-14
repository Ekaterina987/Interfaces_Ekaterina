package ch.makery.address.view.eventosValidar;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

public class EventosController {


    @FXML
    private TextField label;

    @FXML
    void mostrarDialogo(ActionEvent event) {
    	TextInputDialog textDialog = new TextInputDialog("Pon un texto...");
    	textDialog.setTitle("Ejemplo de di�logo");
    	textDialog.setHeaderText("Di�logo para introducir un texto");
    	textDialog.showAndWait().ifPresent(response -> {
        	label.setText(response);
        });
    }

    @FXML
    void initialize() {


    }

}
