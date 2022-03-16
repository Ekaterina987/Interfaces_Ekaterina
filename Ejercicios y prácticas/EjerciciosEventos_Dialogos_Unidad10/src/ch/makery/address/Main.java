package ch.makery.address;

import java.io.IOException;

import ch.makery.address.view.eventosValidar.EventosController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private AnchorPane rootLayout;
	private TextInputDialog textDialog;
	EventHandler<KeyEvent> manejo = (KeyEvent event) -> {
		if (event.getCode() == KeyCode.ESCAPE || event.getCode() == KeyCode.ENTER) {
			event.consume();
		}
	};
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Di�logos");
		initRootLayout();

	}

	/** Inicializa el dise�o de la pantalla principal. */
	public void initRootLayout() {
		showEventos();
	}

	/** Returns the main stage. */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	private void showEventos() {
		try {
			// Carga el XML con el dise�o principal
			FXMLLoader loader = new FXMLLoader();

			loader.setLocation(Main.class.getResource("view/eventosValidar/Eventos.fxml"));
			rootLayout = (AnchorPane) loader.load();
			EventosController eventController = loader.getController();
			eventController.setMain(this);
			// Se a�ade el dise�o principal a la escena
			Scene scene = new Scene(rootLayout);
			scene.addEventFilter(KeyEvent.KEY_TYPED, e -> {

				if (Character.isLowerCase(e.getCharacter().charAt(0)) || e.getCode() == KeyCode.ESCAPE
						|| e.getCode() == KeyCode.ENTER) {
					e.consume();
				}

			});
			
			scene.addEventFilter(KeyEvent.KEY_PRESSED, manejo);
			

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mostrarDialogo(TextField label) {
		textDialog = new TextInputDialog("Pon un texto...");
		textDialog.setTitle("Ejemplo de di�logo");
		textDialog.setHeaderText("Di�logo para introducir un texto");
		textDialog.showAndWait().ifPresent(response -> {
			label.setText(response);
		});
		textDialog.getDialogPane().addEventFilter(KeyEvent.KEY_PRESSED, manejo);
	}

	public static void main(String[] args) {
		launch(args);
	}

}