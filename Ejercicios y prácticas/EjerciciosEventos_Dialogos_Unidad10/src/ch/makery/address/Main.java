package ch.makery.address;

import java.io.IOException;

import ch.makery.address.view.choiceEjercicio.ChoiceController;
import ch.makery.address.view.draggableFiles.DraggableViewController;
import ch.makery.address.view.eventosValidar.EventosController;
import ch.makery.address.view.pagination.FXMLDocumentController;
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
	private TextField textInput;
	private Scene rootScene;
	private EventosController eventController;
	private DraggableViewController draggableController;
	private FXMLDocumentController fxmlDocumentController;
	private ChoiceController choiceController;
	
	EventHandler<KeyEvent> manejo = (KeyEvent event) -> {
		if (event.getCode() == KeyCode.ESCAPE || event.getCode() == KeyCode.ENTER) {
			event.consume();
		}
	};
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Diálogos");
		initRootLayout();

	}

	/** Inicializa el diseño de la pantalla principal. */
	public void initRootLayout() {
		//showEventos();
		//showDraggable();
		//showChoice();
		showPagination();
	}

	/** Returns the main stage. */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	private void showEventos() {
		try {
			// Carga el XML con el diseño principal
			FXMLLoader loader = new FXMLLoader();

			loader.setLocation(Main.class.getResource("view/eventosValidar/Eventos.fxml"));
			rootLayout = (AnchorPane) loader.load();
			eventController = loader.getController();
			eventController.setMain(this);
			textInput = eventController.getTextInput();
			
			
			// Se añade el diseño principal a la escena
			rootScene = new Scene(rootLayout);
			
			
			rootScene.addEventFilter(KeyEvent.KEY_PRESSED, manejo);
			primaryStage.setScene(rootScene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void showDraggable(){
		try {
			// Carga el XML con el diseño principal
			FXMLLoader loader = new FXMLLoader();

			loader.setLocation(Main.class.getResource("view/draggableFiles/DraggableView.fxml"));
			rootLayout = (AnchorPane) loader.load();
			draggableController = loader.getController();
			draggableController.setMain(this);

			// Se añade el diseño principal a la escena
			rootScene = new Scene(rootLayout);

			primaryStage.setScene(rootScene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void showChoice(){
		try {
			// Carga el XML con el diseño principal
			FXMLLoader loader = new FXMLLoader();

			loader.setLocation(Main.class.getResource("view/choiceEjercicio/ChoiceView.fxml"));
			rootLayout = (AnchorPane) loader.load();
			choiceController = loader.getController();

			// Se añade el diseño principal a la escena
			rootScene = new Scene(rootLayout);

			primaryStage.setScene(rootScene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void showPagination(){
		try {
			// Carga el XML con el diseño principal
			FXMLLoader loader = new FXMLLoader();

			loader.setLocation(Main.class.getResource("view/pagination/FXMLDocument.fxml"));
			rootLayout = (AnchorPane) loader.load();
			fxmlDocumentController = loader.getController();

			// Se añade el diseño principal a la escena
			rootScene = new Scene(rootLayout);
			primaryStage.setScene(rootScene);

			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mostrarDialogo(TextField label) {
		textDialog = new TextInputDialog("Pon un texto...");
		textDialog.getDialogPane().addEventFilter(KeyEvent.KEY_TYPED, e -> {

			if (Character.isLowerCase(e.getCharacter().charAt(0))) {
				e.consume();
			}

			});
		textDialog.getDialogPane().addEventFilter(KeyEvent.KEY_PRESSED, manejo);
		textDialog.setTitle("Ejemplo de diálogo");
		textDialog.setHeaderText("Diálogo para introducir un texto");
		textDialog.setOnCloseRequest(event -> textInput.requestFocus());
		textDialog.showAndWait().ifPresent(response -> {
			label.setText(response);
		});
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}