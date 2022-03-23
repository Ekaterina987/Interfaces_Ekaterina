package ch.makery.address.view;


import ch.makery.address.Main;
import ch.makery.address.model.DateUtil;
import ch.makery.address.model.Empleado;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.FlowPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;


public class MenuController {
	@FXML
	private ChoiceBox<String> choiceDepartamento;

	@FXML
	private ComboBox<String> comboCiudad;

	@FXML
	private ListView<String> listResponsabilidades;

	@FXML
	public static final ObservableList<String> responsabilidades =
			FXCollections.observableArrayList();

	@FXML
	public static final ObservableList<String> eleccion =
			FXCollections.observableArrayList();

	@FXML
	private Label empleadoLabel;

	@FXML
	private Button mainButton;

	@FXML
	private TextField inputApellidos;

	@FXML
	private PasswordField inputContrasenia;

	@FXML
	private TextField inputCorreo;

	@FXML
	private DatePicker inputFecha;

	@FXML
	private TextField inputNombre;

	@FXML
	private TextField inputPuesto;


	@FXML
	private ToggleGroup posicion;

	@FXML
	private ImageView noImagen;

	private Empleado empleado;

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
	private ImageView borrarEmpleados;

	@FXML
	private ImageView crearEmpleados;

	@FXML
	private ImageView editarEmpleados;

	@FXML
	private ImageView navegar;

	@FXML
	private ImageView verEmpleados;

	@FXML
	private ImageView logoDemtr;

	@FXML
	private FlowPane inicio;
	@FXML
	void initialize() {
		crearTree();

		FileInputStream fis = null;
		try {
			fis = new FileInputStream("images/logo1.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (logoDemtr == null){
			logoDemtr = new ImageView();
		}
		logoDemtr.setImage(new Image(fis));
		logoDemtr.setPreserveRatio(true);

		FileInputStream fisa = null;
		try {
			fisa = new FileInputStream("images/no-image.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (noImagen == null){
			noImagen = new ImageView();
		}

		noImagen.setImage(new Image(fisa));
		noImagen.setPreserveRatio(true);

		if (choiceDepartamento == null){
			choiceDepartamento = new ChoiceBox<>();
		}
		choiceDepartamento.getItems().addAll("Sistemas y desarrollo", "Comercial y publicidad", "Servicios compartidos");
		responsabilidades.addAll("Programación", "Diseño", "Gestión bases de datos", "Actualizaciones", "Mantenimiento aplicación", "Captación y mantenimiento de sponsors", "Relación con usuarios", "Mantenimiento redes sociales", "Administración de empresa", "RRHH", "Contabilidad", "Contacto colaboradores");

		if (eleccion.isEmpty()) {
			eleccion.add("Elige una responsabilidad");
		}
		if (listResponsabilidades == null){
			listResponsabilidades = new ListView<>();
		}
		listResponsabilidades.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!(eleccion.get(eleccion.size() - 1).equals("Elige una responsabilidad"))) {
					eleccion.add("Elige una responsabilidad");
				}
			}
		});
		listResponsabilidades.setItems(eleccion);
		listResponsabilidades.setCellFactory(ComboBoxListCell.forListView(responsabilidades));

		listResponsabilidades.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		if (comboCiudad == null){
			comboCiudad = new ComboBox<>();
		}
		comboCiudad.getItems().addAll("Madrid", "Santiago de Compostela", "Granada", "León");

		empleado = new Empleado();

		FileInputStream fisb = null;
		FileInputStream fis1 = null;
		FileInputStream fis2 = null;
		FileInputStream fis3 = null;
		FileInputStream fis4 = null;
		try {
			fisb = new FileInputStream("images/menu.png");
			fis1 = new FileInputStream("images/verEmpleados.png");
			fis2 = new FileInputStream("images/crearEmpleados.png");
			fis3 = new FileInputStream("images/modificarEmpleado.png");
			fis4 = new FileInputStream("images/borrarEmpleado.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if (navegar == null){
			navegar = new ImageView();
		}
		if (verEmpleados == null){
			verEmpleados = new ImageView();
		}
		if (crearEmpleados == null){
			crearEmpleados = new ImageView();
		}
		if (editarEmpleados == null){
			editarEmpleados = new ImageView();
		}
		if (borrarEmpleados == null){
			borrarEmpleados = new ImageView();
		}
		navegar.setImage(new Image(fisb));
		verEmpleados.setImage(new Image(fis1));
		crearEmpleados.setImage(new Image(fis2));
		editarEmpleados.setImage(new Image(fis3));
		borrarEmpleados.setImage(new Image(fis4));


	}

	@FXML
	private void crear(ActionEvent event) {
		Main.crear();
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

		if (plusIcon == null){
			plusIcon = new ImageView();
		}
		plusIcon.setImage(new Image(fis2));



		TreeItem<String> rootItem = new TreeItem<String>("Todos", imageView);
		TreeItem<String> departamentosItem = new TreeItem<String>("Departamentos", imageView1);

		rootItem.getChildren().add(departamentosItem);
		departamentosItem.getChildren().add(new TreeItem<String>("Sistemas y desarrollo"));
		departamentosItem.getChildren().add(new TreeItem<String>("Comercial y publicidad"));
		departamentosItem.getChildren().add(new TreeItem<String>("Servicios Compartidos"));

		if (treeDepartamentos == null){
			treeDepartamentos = new TreeView<>();
		}
		treeDepartamentos.setCellFactory(TextFieldTreeCell.forTreeView());

		rootItem.setExpanded(true);
		treeDepartamentos.setRoot(rootItem);
	}

	public void setDatos(TableView<Empleado> tablaEmpleados) {
		this.tablaEmpleados = tablaEmpleados;
		if (colNombre == null){
			colNombre = new TableColumn<>();
		}
		if (colApellidos == null){
			colApellidos = new TableColumn<>();
		}
		if (colCorreo == null){
			colCorreo = new TableColumn<>();
		}
		if (colDepartamento == null){
			colDepartamento = new TableColumn<>();
		}
		if (colPuesto == null){
			colPuesto = new TableColumn<>();
		}
		if (colPosicion == null){
			colPosicion = new TableColumn<>();
		}
		if (colResponsabilidades == null){
			colResponsabilidades = new TableColumn<>();
		}
		if (colFecha == null){
			colFecha = new TableColumn<>();
		}
		if (colCiudad == null){
			colCiudad = new TableColumn<>();
		}
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
				if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY
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
	public void editarEmpleado(Empleado empleado) {
		this.empleado = empleado;
		empleadoLabel.setText("Modificar empleado");
		mainButton.setText("Guardar");

		inputNombre.setText(empleado.getNombre());
		inputApellidos.setText(empleado.getApellidos());
		inputCorreo.setText(empleado.getCorreo());
		inputContrasenia.setText(empleado.getContrasenia());
		ObservableList<String> resps =
				FXCollections.observableArrayList();
		for(String respons : empleado.getResponsabilidadesArray()){
			resps.add(respons);
		}
		resps.add("Elige una responsabilidad");
		listResponsabilidades.setItems(resps);
		inputFecha.setValue(DateUtil.parse(empleado.getFechaInicio()));
		choiceDepartamento.setValue(empleado.getDepartamento());
		inputPuesto.setText(empleado.getPuesto());
		comboCiudad.setValue(empleado.getCiudad());
	}

	@FXML
	private void editar(ActionEvent event) {
		/*ArrayList<String> resp = new ArrayList<>(Arrays.asList("Administración de empresa", "RRHH", "Contabilidad", "Contacto colaboradores"));
		Main.editar(new Empleado("Tony", "Ávila", "tonyavila@demtr.com", "c0ntra5eniA", "Servicios compartidos", "Director", "Jefe", resp,"06/03/2022", "Madrid"));*/
		if (actual != null) {
			Main.editar(actual);
		}
	}
	@FXML
	private void guardarEmpleado(ActionEvent event) {
		handleEmpleado(event, empleado);
	}

	public void handleEmpleado(ActionEvent event, Empleado empleado)
	{
		RadioButton posicionSeleccionada = (RadioButton) posicion.getSelectedToggle();
		String fecha = "";
		try {
			fecha = inputFecha.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}catch(Exception e){

		}
		String resp = "";
		listResponsabilidades.getItems().stream().map(Object::toString).collect(Collectors.joining(", "));
		ObservableList<String> ol = FXCollections.observableArrayList(listResponsabilidades.getItems());
		ol.remove(listResponsabilidades.getItems().size() - 1);
		resp = ol.stream().map(Object::toString).collect(Collectors.joining(", "));
		String dept = "";
		if (choiceDepartamento.getValue() != null) {
			dept = choiceDepartamento.getValue();
		}
		String ciudad = "";
		if (comboCiudad.getValue() != null) {
			ciudad = comboCiudad.getValue();
		}
		String pos = "";
		if(posicionSeleccionada != null){
			pos = posicionSeleccionada.getText();
		}

		Main.validarDatos(empleado, inputNombre.getText(), inputApellidos.getText(), inputCorreo.getText(),
				inputContrasenia.getText(), dept, pos,
				inputPuesto.getText(), resp, fecha, ciudad);

	}
	@FXML
	private void verUsuarios(ActionEvent event) {
		Main.verEmpleados();
	}
	@FXML
	private void abrirTutorial(ActionEvent event) {
		Main.abrirTutorial();
	}
	@FXML
	private void cerrarListado(ActionEvent event) {
		Main.cerrarListado();
	}


	public FlowPane getInicio() {
		return inicio;
	}

}
