package ch.makery.address.view.eventosValidar;


import ch.makery.address.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class EventosController {
	private Main main;

    @FXML
    private TextField texto;

    @FXML
    void mostrarDialogo(ActionEvent event) {
    	main.mostrarDialogo(texto);
    }
    public void setMain(Main main) {
    	this.main = main;
    }

    @FXML
    void initialize() {
		texto.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				texto.setCursor(Cursor.HAND);
			}

		});

    }
    public TextField getTextInput() {
    	return texto;
    }
    public void setTextInput(TextField textInput) {
    	this.texto = textInput;
    }

}
