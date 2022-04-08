package ch.makery.address.view.employees.overview;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


import ch.makery.address.Main;
import ch.makery.address.model.Empleado;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
	private Button editarButton;
	@FXML
	private Button borrarButton;

	@FXML
	private ImageView plusIcon;


	@FXML
	private TableColumn<Empleado, ListView<String>> colResponsabilidades;

	@FXML
	private TableView<Empleado> tablaEmpleados;

	@FXML
	private TreeView<String> treeDepartamentos;

	
	private Empleado actual;

	public TreeView<String> getTreeDepartamentos() {
		return treeDepartamentos;
	}

	public void setTreeDepartamentos(TreeView<String> treeDepartamentos) {
		this.treeDepartamentos = treeDepartamentos;
	}

	@FXML
	void initialize() {
		colNombre.setCellValueFactory(new PropertyValueFactory<Empleado,String>("nombre"));
		colApellidos.setCellValueFactory(new PropertyValueFactory<Empleado,String>("apellidos"));
		colCorreo.setCellValueFactory(new PropertyValueFactory<Empleado,String>("correo"));
		colDepartamento.setCellValueFactory(new PropertyValueFactory<Empleado,String>("departamento"));
		colPuesto.setCellValueFactory(new PropertyValueFactory<Empleado,String>("puesto"));
		colPosicion.setCellValueFactory(new PropertyValueFactory<Empleado,String>("posicion"));
		colResponsabilidades.setCellValueFactory(new PropertyValueFactory<Empleado,ListView<String>>("responsabilidades"));
		colFecha.setCellValueFactory(new PropertyValueFactory<Empleado,String>("fechaInicio"));
		colCiudad.setCellValueFactory(new PropertyValueFactory<Empleado,String>("ciudad"));
		crearTree();
		inhabilitarBotones();
		Main.inhabilitarMenu();
 
	}
	@FXML
    private void crearEmpleado(ActionEvent event) {    	
    	Main.crear();
    }
	@FXML
    private void editarEmpleado(ActionEvent event) {
		editarEmpleado();
    }
	@FXML
	private void borrarEmpleado(ActionEvent event) {
		borrarEmpleado();
	}
	public void inhabilitarBotones(){
		editarButton.setDisable(true);
		borrarButton.setDisable(true);
	}
	public void habilitarBotones(){
		editarButton.setDisable(false);
		borrarButton.setDisable(false);
	}
	public void editarEmpleado(){
		if (actual != null) {
			Main.editar(actual);
		}
	}
	public void borrarEmpleado(){
		if (actual != null) {
			Main.dialogoConfirmacionBorrar(actual);
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
		departamentosItem.getChildren().add(new TreeItem<String>("Servicios compartidos"));

		treeDepartamentos.setCellFactory(TextFieldTreeCell.forTreeView());

		rootItem.setExpanded(true);
		treeDepartamentos.setRoot(rootItem);
	}
	
	public void setDatos(ObservableList<Empleado> data) {



		FilteredList<Empleado> filteredData = new FilteredList<>(data, p -> true);
		treeDepartamentos.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
			filteredData.setPredicate(empleado -> {

				if (newValue == null) {
					return true;
				}
				if (empleado.getDepartamento().equals(newValue.getValue())) {
					return true;
				}else if(newValue.getValue().equals("Departamentos") || newValue.getValue().equals("Todos") ){
					return true;
				}
				return false; // Does not match.
			});

		});

		// 3. Wrap the FilteredList in a SortedList.
		SortedList<Empleado> sortedData = new SortedList<>(filteredData);

		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(tablaEmpleados.comparatorProperty());
		tablaEmpleados.setItems(sortedData);


		tablaEmpleados.setRowFactory(tv -> {
            TableRow<Empleado> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                     && event.getClickCount() == 1) {

                    actual = row.getItem();
					habilitarBotones();
					Main.habilitarMenu();
                }
            });
            return row ;
        });
	}
	
	public TableView<Empleado> getTablaEmpleados(){
		return tablaEmpleados;
	}


}
