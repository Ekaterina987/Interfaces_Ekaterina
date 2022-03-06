package ch.makery.address.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ch.makery.address.model.Empleado;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;

public class UsersOverviewController {

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
	private TableColumn<Empleado, String> colResponsabilidades;

	@FXML
	private TableView<Empleado> tablaEmpleados;

	@FXML
	private TreeView<String> treeDepartamentos;
	
	//responsabilidades.addAll("Programación", "Diseño", "Gestión bases de datos", "Actualizaciones", "Mantenimiento aplicación", "Captación y mantenimiento de sponsors", "Relación con usuarios", "Mantenimiento redes sociales", "Administración de empresa", "RRHH", "Contabilidad", "Contacto colaboradores");
	ArrayList<String> resp = new ArrayList<>(Arrays.asList("Administración de empresa", "RRHH", "Contabilidad", "Contacto colaboradores"));
	ArrayList<String> resp1 = new ArrayList<>(Arrays.asList("Captación y mantenimiento de sponsors", "Relación con usuarios", "Mantenimiento redes sociales"));
	ArrayList<String> resp2 = new ArrayList<>(Arrays.asList("Programación", "Diseño", "Gestión bases de datos", "Actualizaciones", "Mantenimiento aplicación"));
	// Lista auxiliar para TableView
		private ObservableList<Empleado> data = FXCollections.observableArrayList(
				new Empleado("Tony", "Ávila", "tonyavila@demtr.com", "c0ntra5eniA", "Servicios compartidos", "Director", "Jefe", resp,"06/03/2022", "Madrid" ),
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
		// Icono para el TreeView
    	ImageView imageView = new ImageView(new Image(fis)); 
    	imageView.setPreserveRatio(true);
    	imageView.setFitHeight(40);
    	
    	ImageView imageView1 = new ImageView(new Image(fis1)); 
    	imageView1.setPreserveRatio(true);
    	imageView1.setFitHeight(40);
    	
		TreeItem<String> rootItem = new TreeItem<String>("Todos", imageView);
		TreeItem<String> departamentosItem = new TreeItem<String>("Departamentos", imageView1);
		// Ítem de primer nivel
		rootItem.getChildren().add(departamentosItem);
		departamentosItem.getChildren().add(new TreeItem<String>("Sistemas y desarrollo"));
		departamentosItem.getChildren().add(new TreeItem<String>("Comercial y publicidad"));
		departamentosItem.getChildren().add(new TreeItem<String>("Servicios Compartidos"));

		treeDepartamentos.setCellFactory(TextFieldTreeCell.forTreeView());
		// Expadimos por defecto el ítem raíz
		rootItem.setExpanded(true);
		treeDepartamentos.setRoot(rootItem);
		
		colNombre.setCellValueFactory(new PropertyValueFactory<Empleado,String>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<Empleado,String>("apellidos"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<Empleado,String>("correo"));
        colDepartamento.setCellValueFactory(new PropertyValueFactory<Empleado,String>("departamento"));
        colPuesto.setCellValueFactory(new PropertyValueFactory<Empleado,String>("puesto"));
        colPosicion.setCellValueFactory(new PropertyValueFactory<Empleado,String>("posicion"));
        colResponsabilidades.setCellValueFactory(new PropertyValueFactory<Empleado,String>("responsabilidades"));
        colFecha.setCellValueFactory(new PropertyValueFactory<Empleado,String>("fechaInicio"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<Empleado,String>("ciudad"));
        
        // Se rellena la tabla con objetos de la clase Person
        tablaEmpleados.setItems(data); 
        
        tablaEmpleados.setRowFactory(tv -> {
            TableRow<Empleado> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                     && event.getClickCount() == 1) {

                    Empleado clickedRow = row.getItem();
                    generarValores(clickedRow);
                    
                }
            });
            return row ;
        });
	}
	
	private void generarValores(Empleado empleado) {

	}



}
