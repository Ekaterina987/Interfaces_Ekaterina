package ch.makery.address.view.employees.overview;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


import ch.makery.address.Main;
import ch.makery.address.model.Empleado;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class EmployeesOverviewController {
	@FXML
	private Button crearButton;
	@FXML
	private Button editarButton;
	@FXML
	private Button borrarButton;
	@FXML
	private TreeView<String> treeDepartamentos;
	@FXML
	private Pagination pagination;
	@FXML
	private ImageView plusIcon;

	private TableView<Empleado> tablaEmpleados;

	private FilteredList<Empleado> filteredData;

	private Empleado actual;


	@FXML
	void initialize() {

		crearTree();
		inhabilitarBotones();
		Main.inhabilitarMenu();
		inicializarTabla();
		inicializarPaginacion();

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
	@FXML
	void irAyuda(ActionEvent event) {
		Main.ayudaVer();
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
	
	private void crearTree() {
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
	private void inicializarTabla(){
		tablaEmpleados = new TableView<>();
		tablaEmpleados.setTableMenuButtonVisible(true);
		TableColumn<Empleado, String> colNombre = new TableColumn<>("Nombre");
		TableColumn<Empleado, String> colApellidos = new TableColumn<>("Apellidos");
		TableColumn<Empleado, String> colCorreo = new TableColumn<>("Correo");
		TableColumn<Empleado, String> colDepartamento = new TableColumn<>("Departamento");
		TableColumn<Empleado, String> colPuesto = new TableColumn<>("Puesto");
		TableColumn<Empleado, String> colPosicion = new TableColumn<>("Posicion");
		TableColumn<Empleado, ListView<String>> colResponsabilidades = new TableColumn<>("Responsabilidades");
		TableColumn<Empleado, String> colFecha = new TableColumn<>("Fecha");
		TableColumn<Empleado, String> colCiudad = new TableColumn<>("Ciudad");
		colNombre.setCellValueFactory(new PropertyValueFactory<Empleado,String>("nombre"));
		colApellidos.setCellValueFactory(new PropertyValueFactory<Empleado,String>("apellidos"));
		colCorreo.setCellValueFactory(new PropertyValueFactory<Empleado,String>("correo"));
		colDepartamento.setCellValueFactory(new PropertyValueFactory<Empleado,String>("departamento"));
		colPuesto.setCellValueFactory(new PropertyValueFactory<Empleado,String>("puesto"));
		colPosicion.setCellValueFactory(new PropertyValueFactory<Empleado,String>("posicion"));
		colResponsabilidades.setCellValueFactory(new PropertyValueFactory<Empleado,ListView<String>>("responsabilidades"));
		colFecha.setCellValueFactory(new PropertyValueFactory<Empleado,String>("fechaInicio"));
		colCiudad.setCellValueFactory(new PropertyValueFactory<Empleado,String>("ciudad"));
		tablaEmpleados.getColumns().addAll(colNombre, colApellidos, colCorreo, colDepartamento, colPuesto, colPosicion, colResponsabilidades, colFecha, colCiudad);
	}
	private void inicializarPaginacion(){
		pagination.setPageCount((Main.getData().size() % 3) == 0 ?  Main.getData().size() / 3 : (Main.getData().size() / 3) + 1);
		pagination.setPageFactory(new Callback<Integer, Node>() {
			@Override
			public Node call(Integer pageIndex) {
				return crearPagina(pageIndex, Main.getData());
			}
		});
	}
	
	public void setDatos(ObservableList<Empleado> data) {
		filteredData = new FilteredList<>(data, p -> true);
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
				return false;
			});
			pagination.setPageCount((filteredData.size() % 3) == 0 ?  filteredData.size() / 3 : (filteredData.size() / 3) + 1);
			pagination.setPageFactory(new Callback<Integer, Node>() {
				@Override
				public Node call(Integer pageIndex) {
					return crearPagina(pageIndex, filteredData);
				}
			});

		});


		SortedList<Empleado> sortedData = new SortedList<>(filteredData);

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
	private Pane crearPagina(int pageIndex, ObservableList<Empleado> data) {
		Pane contenedor = new Pane();
		int deIndex = pageIndex * 3;
		int aIndex = Math.min(deIndex + 3, data.size());
		tablaEmpleados.setItems(FXCollections.observableArrayList(data.subList(deIndex, aIndex)));
		contenedor.getChildren().add(tablaEmpleados);
		return contenedor;
	}

}
