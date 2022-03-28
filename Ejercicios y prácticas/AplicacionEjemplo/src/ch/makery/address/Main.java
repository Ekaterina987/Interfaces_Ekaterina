package ch.makery.address;
import java.io.IOException;
import java.util.*;

import ch.makery.address.model.DateUtil;
import ch.makery.address.model.Person;
import ch.makery.address.view.DialogoController;
import ch.makery.address.view.PersonController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

	private static Stage primaryStage;
	private BorderPane rootLayout;


	private static PersonController peC;
	private static DialogoController dc;
	private static List<String> errores;
	// Lista auxiliar para TableView
	private static ObservableList<Person> data;


	@Override
	public void start(Stage primaryStage) {
		Main.primaryStage = primaryStage;
		Main.primaryStage.setTitle("AddressApp");
		errores = new ArrayList<>();
		data = FXCollections.observableArrayList(
				new Person("Hans", "Muster", "Elm", "Bremen", 342146, "21/08/1956"),
				new Person("Ruth", "Mueller", "Birch", "Munich", 379523, "12/05/1972"),
				new Person("Heinz", "Kurz", "Pine", "Berlin", 876532, "03/12/1995"),
				new Person("Cornelia", "Meier", "Willow", "Hamburgo", 534234, "19/04/1967"),
				new Person("Werner", "Meyer", "Oak", "Colonia", 543245, "07/07/2003"),
				new Person("Lydia", "Kunz", "Elder", "Dusseldorf", 146217, "16/11/1984"),
				new Person("Anna", "Best", "Maple", "Dresde", 852542, "05/06/1997"),
				new Person("Stefan", "Meier", "Walnut", "Nuremberg", 557217, "31/10/2006"),
				new Person("Martin", "Mueller", "Spruce", "Leipzig", 956424, "11/01/1934"));
		initRootLayout();
		showPersonOverview();
	}
	/** Inicializa el diseño de la pantalla principal. */
	public void initRootLayout() {
		try {
			// Carga el XML con el diseño principal
			FXMLLoader loader = new FXMLLoader();

			loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			// Se añade el diseño principal a la escena
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/** Muestra el diseño de PersonOverview dentro de la pantalla
	 principal */
	public void showPersonOverview() {
		try {
			// Cargamos el archivo PersonOverview
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/PersonOverview.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			peC = loader.getController();
			peC.setData(data);
			// Se sitúa en el centro del diseño principal
			rootLayout.setCenter(personOverview);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void crearPersona(){
		try {
			// Cargamos el diseño del diálogo desde un XML
			FXMLLoader loader = new FXMLLoader();

			loader.setLocation(Main.class.getResource("/ch/makery/address/view/DialogoPersona.fxml"));

			AnchorPane page = (AnchorPane) loader.load();
			dc= loader.getController();
			// Se crea un nuevo Stage para mostrar el diálogo
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Crear o editar persona");
			// Se bloquean los eventos de la pantalla principal
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(Main.getPrimaryStage());

			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void editarPersona(Person persona){
		if (persona!= null){
			try {
				// Cargamos el diseño del diálogo desde un XML
				FXMLLoader loader = new FXMLLoader();

				loader.setLocation(Main.class.getResource("/ch/makery/address/view/DialogoPersona.fxml"));
				AnchorPane page = (AnchorPane) loader.load();
				dc= loader.getController();
				dc.editar(persona);

				// Se crea un nuevo Stage para mostrar el diálogo
				Stage dialogStage = new Stage();
				dialogStage.setTitle("Crear o editar persona");
				// Se bloquean los eventos de la pantalla principal
				dialogStage.initModality(Modality.WINDOW_MODAL);
				dialogStage.initOwner(Main.getPrimaryStage());

				Scene scene = new Scene(page);
				dialogStage.setScene(scene);
				dialogStage.showAndWait();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			Main.dialogoEditarVacio();
		}

	}
	public static void validar(Person persona, Map<String, TextField> fields){

		for (String clave : fields.keySet()) {
			TextField valor = fields.get(clave);
			String error = "";
			if (valor.getText().equals("")) {
				error = "El campo " + clave + " está vacío";
				errores.add(error);
			}
			if (clave.equals("postal code")) {
				try {
					Integer.parseInt(valor.getText());
				} catch (Exception e) {
					error = "Postal code no válido. Debe ser un número entero";
					errores.add(error);
				}
			} else if (clave.equals("birthday")) {
				if (DateUtil.parse(valor.getText()) == null) {
					error = "El campo " + clave + " no es válido. Usa el formato dd/mm/yyyy";
					errores.add(error);
				}
			}

		}
		if(errores.size()>0) {
			dialogoError();
		}else {
			int index = data.indexOf(persona);
			persona.setFirstName(fields.get("first name").getText());
			persona.setLastName(fields.get("last name").getText());
			persona.setStreet(fields.get("street").getText());
			persona.setCity(fields.get("city").getText());
			persona.setPC(Integer.parseInt(fields.get("postal code").getText()));
			persona.setBirthday(fields.get("birthday").getText());
			aniadirPersona(index, persona);
		}
	}
	public static void aniadirPersona(int index, Person persona) {
		if (index != -1){
			data.set(index, persona);
			peC.generarValores(persona);
			dialogoExitoEditar();
		}else{
			data.add(persona);
			dialogoExitoCrear();
		}
		peC.setData(data);
	}
	public static void borrarPersona(Person persona){
		if (data.contains(persona)){
			dialogoConfirmacion(persona);
		}else{
			dialogoBorrarVacio();
		}
	}
	public static void dialogoExitoCrear(){
		Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
		infoAlert.setTitle("Éxito");
		infoAlert.setHeaderText("Se ha creado la persona");

		infoAlert.showAndWait();
	}
	public static void dialogoExitoEditar(){
		Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
		infoAlert.setTitle("Éxito");
		infoAlert.setHeaderText("Se han modificado los datos de la persona");

		infoAlert.showAndWait();
	}
	public static void dialogoError(){
		Alert errorAlert = new Alert(Alert.AlertType.ERROR);
		errorAlert.setTitle("Hay campos incorrectos");
		errorAlert.setHeaderText("Por favor, rellena correctamente los campos");
		errorAlert.setContentText(String.join("\n", errores));


		errorAlert.showAndWait();
		errores.clear();
	}
	public static void dialogoEditarVacio(){
		Alert errorAlert = new Alert(Alert.AlertType.ERROR);
		errorAlert.setTitle("Error al editar persona");
		errorAlert.setHeaderText("No se ha seleccionado ninguna fila");
		errorAlert.setContentText("Por favor, selecciona una persona en la tabla");
		errorAlert.showAndWait();
	}
	public static void dialogoBorrarVacio(){
		Alert errorAlert = new Alert(Alert.AlertType.ERROR);
		errorAlert.setTitle("Error al eliminar");
		errorAlert.setHeaderText("Se ha producido un error");
		errorAlert.setContentText("No se puede eliminar porque no se ha seleccionado una fila o la tabla está vacía");
		errorAlert.showAndWait();
	}
	public static void dialogoConfirmacion(Person persona){
		Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
		confirmAlert.setTitle("Confirmar borrado persona");
		confirmAlert.setHeaderText("¿Quieres borrar a " + persona.getFirstName() + " " + persona.getLastName() + "?");

		Optional<ButtonType> result = confirmAlert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			data.remove(persona);
			peC.resetValores();
			dialogoExitoBorrar();
		}

	}
	public static void dialogoExitoBorrar(){
		Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
		infoAlert.setTitle("Éxito");
		infoAlert.setHeaderText("Se ha borrado la persona");

		infoAlert.showAndWait();
	}

	/** Returns the main stage. */
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	public static void main(String[] args) {
		launch(args);
	}
}
