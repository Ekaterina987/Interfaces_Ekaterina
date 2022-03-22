package ch.makery.address.view.employees.overview;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


import ch.makery.address.Main;
import ch.makery.address.model.Empleado;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
	private ImageView plusIcon;


	@FXML
	private TableColumn<Empleado, ListView<String>> colResponsabilidades;

	@FXML
	private TableView<Empleado> tablaEmpleados;

	@FXML
	private TreeView<String> treeDepartamentos;

	
	private Empleado actual;
	
	@FXML
	void initialize() {
		
		crearTree();
 
	}
	@FXML
    private void crearEmpleado(ActionEvent event) {    	
    	Main.crear();
    }
	@FXML
    private void editarEmpleado(ActionEvent event) {
		if (actual != null) {
			Main.editar(actual);
		}
    }
	
	public void crearTree() {
		FileInputStream fis = null;
		FileInputStream fis1 = null;
		FileInputStream fis2 = null;
    	try {
			fis = new FileInputStream("images/empleados.png");
			fis1 = new FileInputStream("images/departamento.png");
			fis2 = new FileInputStream("images/create.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

    	ImageView imageView = new ImageView(new Image(fis)); 
    	imageView.setPreserveRatio(true);
    	imageView.setFitHeight(40);
    	
    	ImageView imageView1 = new ImageView(new Image(fis1)); 
    	imageView1.setPreserveRatio(true);
    	imageView1.setFitHeight(40);

		plusIcon.setImage(new Image(fis2));


    	
		TreeItem<String> rootItem = new TreeItem<String>("Todos", imageView);
		TreeItem<String> departamentosItem = new TreeItem<String>("Departamentos", imageView1);

		rootItem.getChildren().add(departamentosItem);
		departamentosItem.getChildren().add(new TreeItem<String>("Sistemas y desarrollo"));
		departamentosItem.getChildren().add(new TreeItem<String>("Comercial y publicidad"));
		departamentosItem.getChildren().add(new TreeItem<String>("Servicios Compartidos"));

		treeDepartamentos.setCellFactory(TextFieldTreeCell.forTreeView());

		rootItem.setExpanded(true);
		treeDepartamentos.setRoot(rootItem);
	}
	
	public void setDatos(TableView<Empleado> tablaEmpleados) {
		this.tablaEmpleados = tablaEmpleados;
		colNombre.setCellValueFactory(new PropertyValueFactory<Empleado,String>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<Empleado,String>("apellidos"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<Empleado,String>("correo"));
        colDepartamento.setCellValueFactory(new PropertyValueFactory<Empleado,String>("departamento"));
        colPuesto.setCellValueFactory(new PropertyValueFactory<Empleado,String>("puesto"));
        colPosicion.setCellValueFactory(new PropertyValueFactory<Empleado,String>("posicion"));
        colResponsabilidades.setCellValueFactory(new PropertyValueFactory<Empleado,ListView<String>>("responsabilidades"));
        colFecha.setCellValueFactory(new PropertyValueFactory<Empleado,String>("fechaInicio"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<Empleado,String>("ciudad"));

		tablaEmpleados.setRowFactory(tv -> {
            TableRow<Empleado> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                     && event.getClickCount() == 1) {

                    actual = row.getItem();
                    
                }
            });
            return row ;
        });
	}
	
	public TableView<Empleado> getTablaEmpleados(){
		return tablaEmpleados;
	}


}
