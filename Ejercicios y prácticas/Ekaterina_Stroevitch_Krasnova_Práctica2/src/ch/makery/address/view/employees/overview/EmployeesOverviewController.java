package ch.makery.address.view.employees.overview;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import ch.makery.address.Main;
import ch.makery.address.model.Empleado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EmployeesOverviewController {

	@FXML
	private TableColumn<Empleado, String> colApellidos;

	@FXML
	private TableColumn<Empleado, String> colCiudad;

	@FXML
	private TableColumn<Empleado, String> colCorreo;

	@FXML
	private TableColumn<Empleado, String> colFecha;

	@FXML
	private TableColumn<Empleado, String> colNombre;

	@FXML
	private TableColumn<Empleado, String> colPosicion;

	@FXML
	private TableColumn<Empleado, String> colPuesto;
	
	@FXML
	private TableColumn<Empleado, String> colDepartamento;
	
	@FXML
    private Button crearButton;


	@FXML
	private TableColumn<Empleado, ListView<String>> colResponsabilidades;

	@FXML
	private TableView<Empleado> tablaEmpleados;

	@FXML
	private TreeView<String> treeDepartamentos;
	
	
	private Main main;
	
	ArrayList<String> resp = new ArrayList<>(Arrays.asList("Administraci�n de empresa", "RRHH", "Contabilidad", "Contacto colaboradores"));
	ArrayList<String> resp1 = new ArrayList<>(Arrays.asList("Captaci�n y mantenimiento de sponsors", "Relaci�n con usuarios", "Mantenimiento redes sociales"));
	ArrayList<String> resp2 = new ArrayList<>(Arrays.asList("Programaci�n", "Dise�o", "Gesti�n bases de datos", "Actualizaciones", "Mantenimiento aplicaci�n"));

		private ObservableList<Empleado> data = FXCollections.observableArrayList(
				new Empleado("Tony", "�vila", "tonyavila@demtr.com", "c0ntra5eniA", "Servicios compartidos", "Director", "Jefe", resp,"06/03/2022", "Madrid" ),
				new Empleado("Diego", "Jaular", "diegojaular@demtr.com", "c0ntra5eniA", "Comercial y publicidad", "Director", "Jefe",resp1, "06/03/2022", "Madrid" ),
				new Empleado("Ekaterina", "Stroevitch", "ekaterinastroevitch@demtr.com", "c0ntra5eniA", "Sistemas y desarrollo", "Director", "Jefe",resp2, "06/03/2022", "Madrid" ));

	@FXML
	void initialize() {
		FileInputStream fis = null;
		FileInputStream fis1 = null;
    	try {
			fis = new FileInputStream("images/empleados.png");
			fis1 = new FileInputStream("images/departamento.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

    	ImageView imageView = new ImageView(new Image(fis)); 
    	imageView.setPreserveRatio(true);
    	imageView.setFitHeight(40);
    	
    	ImageView imageView1 = new ImageView(new Image(fis1)); 
    	imageView1.setPreserveRatio(true);
    	imageView1.setFitHeight(40);
    	
		TreeItem<String> rootItem = new TreeItem<String>("Todos", imageView);
		TreeItem<String> departamentosItem = new TreeItem<String>("Departamentos", imageView1);

		rootItem.getChildren().add(departamentosItem);
		departamentosItem.getChildren().add(new TreeItem<String>("Sistemas y desarrollo"));
		departamentosItem.getChildren().add(new TreeItem<String>("Comercial y publicidad"));
		departamentosItem.getChildren().add(new TreeItem<String>("Servicios Compartidos"));

		treeDepartamentos.setCellFactory(TextFieldTreeCell.forTreeView());

		rootItem.setExpanded(true);
		treeDepartamentos.setRoot(rootItem);
		
		
		
		colNombre.setCellValueFactory(new PropertyValueFactory<Empleado,String>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<Empleado,String>("apellidos"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<Empleado,String>("correo"));
        colDepartamento.setCellValueFactory(new PropertyValueFactory<Empleado,String>("departamento"));
        colPuesto.setCellValueFactory(new PropertyValueFactory<Empleado,String>("puesto"));
        colPosicion.setCellValueFactory(new PropertyValueFactory<Empleado,String>("posicion"));
        colResponsabilidades.setCellValueFactory(new PropertyValueFactory<Empleado,ListView<String>>("responsabilidades"));
        colFecha.setCellValueFactory(new PropertyValueFactory<Empleado,String>("fechaInicio"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<Empleado,String>("ciudad"));
        

        tablaEmpleados.setItems(data); 
        


	}
	@FXML
    private void crearEmpleado(ActionEvent event) {    	
    	main.crear();
    }
	@FXML
    private void editarEmpleado(ActionEvent event) {    	
    	main.editar();
    }
	
	public void setMain(Main main) {
		this.main = main;
	}


}
