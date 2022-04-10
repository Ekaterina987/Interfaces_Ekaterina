package ch.makery.address;

import java.io.IOException;
import java.util.*;

import ch.makery.address.model.DateUtil;
import ch.makery.address.model.Empleado;
import ch.makery.address.view.*;
import ch.makery.address.view.employee.create.EmployeeCreateController;
import ch.makery.address.view.employees.overview.EmployeesOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
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
	private static Map<String, String> fields;
	private static List<String> errores;
	private static Stage stage;

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		Main.stage = stage;
	}

	private static ObservableList<Empleado> data;

	public static ObservableList<Empleado> getData() {
		return data;
	}

	public static void setData(ObservableList<Empleado> data) {
		Main.data = data;
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
			primaryStage.setScene(scene);
			primaryStage.setTitle("Práctica 2");
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
	public static void cerrarListado() {
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
		infoAlert.setTitle("Éxito");
		infoAlert.setHeaderText(accion);

		infoAlert.showAndWait();
	}
	public static void dialogoErrorCrearMod() {
		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setTitle("Hay campos incorrectos");
		errorAlert.setHeaderText("Por favor, rellena correctamente los campos");
		errorAlert.setContentText(String.join("\n", errores));


		errorAlert.showAndWait();
		errores.clear();
	}
	public static void dialogoConfirmacionEditar(int i, Empleado empleado,String nombre,String apellidos){
		Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
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

	public static ObservableList<Empleado> getEmpleados(){
		return data;
	}


	public static void main(String[] args) {
		ArrayList<String> resp = new ArrayList<>(Arrays.asList("Administración de empresa", "RRHH", "Contabilidad", "Contacto colaboradores"));
		ArrayList<String> resp1 = new ArrayList<>(Arrays.asList("Captación y mantenimiento de sponsors", "Relación con usuarios", "Mantenimiento redes sociales"));
		ArrayList<String> resp2 = new ArrayList<>(Arrays.asList("Programación", "Diseño", "Gestión bases de datos", "Actualizaciones", "Mantenimiento aplicación"));

		data = FXCollections.observableArrayList(
				new Empleado("Tony", "Ávila", "tonyavila@demtr.com", "c0ntra5eniA",
						"Servicios compartidos", "Director", "Jefe", resp,"06/03/2022", "Madrid" ),
				new Empleado("Diego", "Jaular", "diegojaular@demtr.com", "c0ntra5eniA",
						"Comercial y publicidad", "Director", "Jefe", resp1, "06/03/2022", "Madrid" ),
				new Empleado("Ekaterina", "Stroevitch", "ekaterinastroevitch@demtr.com", "c0ntra5eniA",
						"Sistemas y desarrollo", "Director", "Jefe", resp2, "06/03/2022", "Madrid" ));
		errores = new ArrayList<>();
		fields = new HashMap<>();


		launch(args);
	}

}
