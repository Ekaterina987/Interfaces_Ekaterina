package ch.makery.address;
	
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ch.makery.address.model.DateUtil;
import ch.makery.address.model.Empleado;
import ch.makery.address.view.*;
import ch.makery.address.view.employee.create.EmployeeCreateController;
import ch.makery.address.view.employees.overview.EmployeesOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class Main extends Application {
private BorderPane rootLayout;	
private MenuController menuController;
private EmployeeCreateController createController;
private EmployeesOverviewController overviewController;
private Map<String, String> fields = new HashMap<>();
private List<String> errores = new ArrayList<>();
private Empleado empleado;


private TableView<Empleado> tablaEmpleados;


ArrayList<String> resp = new ArrayList<>(Arrays.asList("Administraci�n de empresa", "RRHH", "Contabilidad", "Contacto colaboradores"));
ArrayList<String> resp1 = new ArrayList<>(Arrays.asList("Captaci�n y mantenimiento de sponsors", "Relaci�n con usuarios", "Mantenimiento redes sociales"));
ArrayList<String> resp2 = new ArrayList<>(Arrays.asList("Programaci�n", "Dise�o", "Gesti�n bases de datos", "Actualizaciones", "Mantenimiento aplicaci�n"));

	private ObservableList<Empleado> data = FXCollections.observableArrayList(
			new Empleado("Tony", "�vila", "tonyavila@demtr.com", "c0ntra5eniA", "Servicios compartidos", "Director", "Jefe", resp,"06/03/2022", "Madrid" ),
			new Empleado("Diego", "Jaular", "diegojaular@demtr.com", "c0ntra5eniA", "Comercial y publicidad", "Director", "Jefe",resp1, "06/03/2022", "Madrid" ),
			new Empleado("Ekaterina", "Stroevitch", "ekaterinastroevitch@demtr.com", "c0ntra5eniA", "Sistemas y desarrollo", "Director", "Jefe",resp2, "06/03/2022", "Madrid" ));

	
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
			menuController.setMain(this);

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Pr�ctica 2");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void crear() {    	
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MenuController.class.getResource("/ch/makery/address/view/employee/create/EmployeeCreate.fxml"));
			GridPane ventanaCrear = (GridPane) loader.load();
			createController = loader.getController();
			createController.setMain(this);

			rootLayout.setCenter(ventanaCrear);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void editar(Empleado empleado) {    	
    	try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MenuController.class.getResource("/ch/makery/address/view/employee/create/EmployeeCreate.fxml"));
			GridPane ventanaEditar = (GridPane) loader.load();

			createController = loader.getController();
			createController.setMain(this);
			createController.setEmpleado(empleado);
			
			createController.cambiarLabel("Modificar empleado", "Guardar");

			rootLayout.setCenter(ventanaEditar);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void verEmpleados() {    	
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MenuController.class.getResource("/ch/makery/address/view/employees/overview/EmployeesOverview.fxml"));
			SplitPane ventanaEmpleados = (SplitPane) loader.load();
			
			overviewController = loader.getController();
			overviewController.setMain(this);
			tablaEmpleados = overviewController.getTablaEmpleados();
			tablaEmpleados.setItems(data); 
	        overviewController.setDatos(tablaEmpleados);

			rootLayout.setCenter(ventanaEmpleados);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	public void abrirTutorial() {    	
    	try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MenuController.class.getResource("/ch/makery/address/view/help/Help.fxml"));
			AnchorPane tutorial = (AnchorPane) loader.load();
			
			rootLayout.setCenter(tutorial);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	public void cerrarListado() {    	
    	rootLayout.setCenter(menuController.getInicio());	
    }
	
	public void guardarEmpleado(Empleado empleado) {
		data.add(empleado);
		dialogoExitoCrear();
		verEmpleados();
	}
	
	public void dialogoExitoCrear() {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("�xito");
    	infoAlert.setHeaderText("Se ha creado el empleado");

    	infoAlert.showAndWait();
	}
	public void dialogoErrorCrear() {
		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setTitle("Hay campos incorrectos");
    	errorAlert.setHeaderText("Por favor, rellena correctamente los campos");
    	errorAlert.setContentText(String.join("\n", errores));
    	

    	errorAlert.showAndWait();
    	errores.clear();
	}
	
	
	public void validarDatos(String nombre, String apellidos, String correo, String contrasenia, String departamento, String posicion, String puesto, String responsabilidades, String fechaInicio, String ciudad) {
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
    	    	error = "El campo " + clave + " est� vac�o";
    	    	errores.add(error);
    	    }
    	   else if(clave.equals("fecha de inicio")) {
    	    	if (DateUtil.parse(valor) == null) {
    	    		error = "El campo " + clave + " no es v�lido. Usa el formato dd/mm/yyyy";
    	    		errores.add(error);
    	    	}
    	    }
    	    
    	}
    	if(errores.size()>0) {
	    	dialogoErrorCrear();
    	}else {
    		this.empleado = new Empleado(nombre, apellidos, correo, contrasenia, departamento, posicion, puesto, responsabilidades, fechaInicio, ciudad);
    		guardarEmpleado(empleado);
    	}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
