package ch.makery.address;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import ch.makery.address.model.DateUtil;
import ch.makery.address.model.Empleado;
import ch.makery.address.view.*;
import ch.makery.address.view.employee.create.EmployeeCreateController;
import ch.makery.address.view.employees.overview.EmployeesOverviewController;
import ch.makery.address.view.help.HelpController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class Main extends Application {
	private static BorderPane rootLayout;
	private static MenuController menuController;
	private static EmployeeCreateController createController;
	private static EmployeesOverviewController overviewController;
	private static HelpController helpController;
	private static Map<String, String> fields;
	private static List<String> errores;
	private static Stage stage;
	private static ObservableList<Empleado> data;
	private static Popup popup;
	private static EventHandler<KeyEvent> manejo;

	public static ObservableList<Empleado> getData() {
		return data;
	}

	public static void setData(ObservableList<Empleado> data) {
		Main.data = data;
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		Main.stage = stage;
	}
	public static ObservableList<Empleado> getEmpleados(){
		return data;
	}

	public static EventHandler<KeyEvent> getManejo() {
		return manejo;
	}

	public static void setManejo(EventHandler<KeyEvent> manejo) {
		Main.manejo = manejo;
	}

	@Override
	public void start(Stage primaryStage) {
		setRootLayout(primaryStage);
	}
	public void setRootLayout(Stage primaryStage) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			menuController = loader.getController();

			Scene scene = new Scene(rootLayout);
			scene.getStylesheets().add(Main.class.getResource("css/global.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Gestión Empleados Demtr");
			FileInputStream fis = null;
			try {
				fis = new FileInputStream("resources/images/employee.png");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			primaryStage.getIcons().add(new Image(fis));
			primaryStage.show();
			Main.setStage(primaryStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void crear() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MenuController.class.getResource("/ch/makery/address/view/employee/create/EmployeeCreate.fxml"));
			GridPane ventanaCrear = (GridPane) loader.load();
			createController = loader.getController();
			createController.setEditar(false);

			rootLayout.setCenter(ventanaCrear);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void editar(Empleado empleado) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MenuController.class.getResource("/ch/makery/address/view/employee/create/EmployeeCreate.fxml"));
			GridPane ventanaEditar = (GridPane) loader.load();


			createController = loader.getController();

			createController.setEditar(true);
			createController.editarEmpleado(empleado);

			rootLayout.setCenter(ventanaEditar);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void verEmpleados() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MenuController.class.getResource("/ch/makery/address/view/employees/overview/EmployeesOverview.fxml"));
			SplitPane ventanaEmpleados = (SplitPane) loader.load();

			overviewController = loader.getController();
			overviewController.setDatos(data);

			rootLayout.setCenter(ventanaEmpleados);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void abrirTutorial() {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MenuController.class.getResource("/ch/makery/address/view/help/Help.fxml"));
			AnchorPane tutorial = (AnchorPane) loader.load();

			helpController = loader.getController();

			rootLayout.setCenter(tutorial);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void editarEmpleado(){
		overviewController.editarEmpleado();
	}
	public static void borrarEmpleado(){
		overviewController.borrarEmpleado();
	}
	public static void volverInicio() {
		rootLayout.setCenter(menuController.getInicio());
	}

	public static void guardarEmpleado(int i, Empleado empleado, String nombre, String apellidos) {
		if (i != -1){
			dialogoConfirmacionEditar(i, empleado, nombre, apellidos);

		}else{
			data.add(empleado);
			dialogoExitoCrearMod("Se ha creado el empleado");
			verEmpleados();
		}

	}

	public static void dialogoExitoCrearMod(String accion) {

		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.getDialogPane().addEventFilter(KeyEvent.KEY_PRESSED, manejo);
		infoAlert.setTitle("Éxito");
		infoAlert.setHeaderText(accion);

		infoAlert.showAndWait();
	}
	public static void dialogoErrorCrearMod() {
		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.getDialogPane().addEventFilter(KeyEvent.KEY_PRESSED, manejo);
		errorAlert.setTitle("Hay campos incorrectos");
		errorAlert.setHeaderText("Por favor, rellena correctamente los campos");
		errorAlert.setContentText(String.join("\n", errores));


		errorAlert.showAndWait();
		errores.clear();
	}
	public static void dialogoConfirmacionEditar(int i, Empleado empleado,String nombre,String apellidos){
		Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
		confirmAlert.getDialogPane().addEventFilter(KeyEvent.KEY_PRESSED, manejo);
		confirmAlert.setTitle("Confirmar modificación empleado");
		confirmAlert.setHeaderText("¿Estás seguro de que quieres modificar los datos de " + nombre + " " + apellidos + "?");

		Optional<ButtonType> result = confirmAlert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			data.set(i, empleado);
			dialogoExitoCrearMod("Se ha modificado el empleado");
			verEmpleados();
		}

	}
	public static void dialogoConfirmacionBorrar(Empleado empleado){
		Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
		confirmAlert.getDialogPane().addEventFilter(KeyEvent.KEY_PRESSED, manejo);
		confirmAlert.setTitle("Confirmar borrado empleado");
		confirmAlert.setHeaderText("¿Estás seguro de que quieres borrar a " + empleado.getNombre() + " " + empleado.getApellidos() + "?");

		Optional<ButtonType> result = confirmAlert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			data.remove(empleado);
			dialogoExitoCrearMod("Se ha eliminado a " + empleado.getNombre() + " " + empleado.getApellidos());
		}

	}


	public static void validarDatos(Empleado e, String nombre, String apellidos, String correo, String contrasenia, String departamento, String posicion, String puesto, String responsabilidades, String fechaInicio, String ciudad) {
		fields.put("nombre", nombre);
		fields.put("apellidos", apellidos);
		fields.put("correo", correo);
		fields.put("contrasenia", contrasenia);
		fields.put("departamento", departamento);
		fields.put("posicion", posicion);
		fields.put("puesto", puesto);
		fields.put("responsabilidades", responsabilidades);
		fields.put("fecha de inicio", fechaInicio);
		fields.put("ciudad", ciudad);
		Iterator<String> it = fields.keySet().iterator();

		while(it.hasNext()){
			String clave = it.next();
			String valor = fields.get(clave);
			String error = "";

			if (valor.equals("")) {
				error = "El campo " + clave + " está vacío";
				errores.add(error);
			}else if(clave.equals("contrasenia")){
				String patron = "^(?=.{6,12}$)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).*$";
				if (!valor.matches(patron)){
					error = "La contraseña no es válida";
					errores.add(error);
				}
			}else if(clave.equals("correo")){
				String patron = "[a-z]+@[a-z]+\\.[a-z]+";
				if (!valor.matches(patron)){
					error = "El correo no es válido";
					errores.add(error);
				}
			}
			else if(clave.equals("fecha de inicio")) {
				if (DateUtil.parse(valor) == null) {
					error = "El campo " + clave + " no es válido. Usa el formato dd/mm/yyyy";
					errores.add(error);
				}
			}

		}
		if(errores.size()>0) {
			dialogoErrorCrearMod();
		}else {
			String nom = e.getNombre();
			String ape = e.getApellidos();
			int index = data.indexOf(e);
			e.setNombre(nombre);
			e.setApellidos(apellidos);
			e.setCorreo(correo);
			e.setContrasenia(contrasenia);
			e.setDepartamento(departamento);
			e.setPosicion(posicion);
			e.setPuesto(puesto);
			e.setResponsabilidades(responsabilidades);
			e.setFechaInicio(fechaInicio);
			e.setCiudad(ciudad);
			//this.empleado = new Empleado(nombre, apellidos, correo, contrasenia, departamento, posicion, puesto, responsabilidades, fechaInicio, ciudad);
			guardarEmpleado(index, e, nom, ape);
		}
	}

	public static void inhabilitarMenu(){
		menuController.inhabilitarMenu();
	}
	public static void habilitarMenu(){
		menuController.habilitarMenu();
	}


	public static void mostrarPopup(Point2D point){
		TextFlow tf = new TextFlow(new Text("La contraseña debe tener un largo entre 6 y 12 caracteres y contener al menos una letra mayúscula, una minúscula y un número"));
		popup = new Popup();
		tf.setStyle("-fx-background-color: white; -fx-padding: 15");

		popup.getContent().add(tf);


		popup.setX(stage.getX() );
		popup.setY(stage.getY());
		popup.show(stage);
		popup.setX(stage.getX() + point.getX() + 25);
		popup.setY(stage.getY() + point.getY() + 5);

	}
	public static void ocultarPopup(){
		popup.hide();
	}
	private static void inicializarValores(){
		ArrayList<String> resp = new ArrayList<>(Arrays.asList("Administración de empresa", "RRHH", "Contabilidad", "Contacto colaboradores"));
		ArrayList<String> resp1 = new ArrayList<>(Arrays.asList("Captación y mantenimiento de sponsors", "Relación con usuarios", "Mantenimiento redes sociales"));
		ArrayList<String> resp2 = new ArrayList<>(Arrays.asList("Programación", "Diseño", "Gestión bases de datos", "Actualizaciones", "Mantenimiento aplicación"));
		ArrayList<String> resp3 = new ArrayList<>(Arrays.asList("Captación y mantenimiento de sponsors", "Relación con usuarios"));
		ArrayList<String> resp4 = new ArrayList<>(Arrays.asList("Diseño", "Actualizaciones", "Mantenimiento aplicación"));

		data = FXCollections.observableArrayList(
				new Empleado("Tony", "Ávila", "tonyavila@demtr.com", "c0ntra5eniA",
						"Servicios compartidos", "Director", "Jefe", resp,"06/03/2022", "Madrid" ),
				new Empleado("Diego", "Jaular", "diegojaular@demtr.com", "c0ntra5eniA",
						"Comercial y publicidad", "Director", "Jefe", resp1, "06/03/2022", "Madrid" ),
				new Empleado("Ekaterina", "Stroevitch", "ekaterinastroevitch@demtr.com", "c0ntra5eniA",
						"Sistemas y desarrollo", "Director", "Jefe", resp2, "06/03/2022", "Madrid" ),
				new Empleado("Juan", "Palomo", "juanpalomo@demtr.com", "c0ntra5eniA",
						"Comercial y publicidad", "Empleado", "Comercial", resp3, "16/04/2022", "Granada" ),
				new Empleado("Bruno", "Munari", "brunomunari@demtr.com", "c0ntra5eniA",
						"Sistemas y desarrollo", "Empleado", "Diseñador", resp4, "18/04/2022", "León" ));

		errores = new ArrayList<>();
		fields = new HashMap<>();
		manejo = (KeyEvent event) -> {
			if (event.getCode() == KeyCode.ESCAPE || event.getCode() == KeyCode.ENTER) {
				event.consume();
			}
		};
	}


	public static void ayudaNavegar(){
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MenuController.class.getResource("/ch/makery/address/view/help/Help.fxml"));
			AnchorPane tutorial = (AnchorPane) loader.load();

			helpController = loader.getController();

			helpController.getAcordeon().setExpandedPane(helpController.getPaneNavegar());
			rootLayout.setCenter(tutorial);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void ayudaVer(){
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MenuController.class.getResource("/ch/makery/address/view/help/Help.fxml"));
			AnchorPane tutorial = (AnchorPane) loader.load();

			helpController = loader.getController();

			helpController.getAcordeon().setExpandedPane(helpController.getPaneVer());
			rootLayout.setCenter(tutorial);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void ayudaCrear(){
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MenuController.class.getResource("/ch/makery/address/view/help/Help.fxml"));
			AnchorPane tutorial = (AnchorPane) loader.load();

			helpController = loader.getController();

			helpController.getAcordeon().setExpandedPane(helpController.getPaneCrear());
			rootLayout.setCenter(tutorial);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void ayudaEditar(){
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MenuController.class.getResource("/ch/makery/address/view/help/Help.fxml"));
			AnchorPane tutorial = (AnchorPane) loader.load();

			helpController = loader.getController();

			helpController.getAcordeon().setExpandedPane(helpController.getPaneEditar());
			rootLayout.setCenter(tutorial);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void ayudaBorrar(){
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MenuController.class.getResource("/ch/makery/address/view/help/Help.fxml"));
			AnchorPane tutorial = (AnchorPane) loader.load();

			helpController = loader.getController();

			helpController.getAcordeon().setExpandedPane(helpController.getPaneBorrar());
			rootLayout.setCenter(tutorial);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {

		inicializarValores();
		launch(args);
	}

}
