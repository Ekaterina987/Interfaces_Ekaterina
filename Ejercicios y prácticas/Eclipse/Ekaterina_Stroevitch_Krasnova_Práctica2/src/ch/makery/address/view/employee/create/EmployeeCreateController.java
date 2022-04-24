package ch.makery.address.view.employee.create;




import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ch.makery.address.Main;
import ch.makery.address.model.DateUtil;
import ch.makery.address.model.Empleado;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

public class EmployeeCreateController {


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
	public static ObservableList<String> eleccion;
	
	@FXML
    private Label empleadoLabel;
	
	@FXML
    private Button mainButton;
	@FXML
	private Label ayuda;

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

	@FXML
	private RadioButton rEmpleado;

	@FXML
	private RadioButton rAdministrador;

	@FXML
	private RadioButton rDirector;
	@FXML
	private Label plus;
	@FXML
	private StackPane tooltip;
	@FXML
	private GridPane gridPane;

	private Empleado empleado;

	private boolean editar;

	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}
	/* 
	 * Se da una url a las imágenes, se inhabilitan los menús de editar y borrar, se inicializa la lista de responsabilidades 
	 * y el tooltip de la contraseña, se formatea el campo del correo para que las letras se vuelvan minúsculas, se añaden los elementos
	 * del desplegable del campo departamento y los elementos del desplegable del campo ciudad y se inicializa el empleado como un empleado vacío
	 */
	@FXML
    void initialize() {

		urlImagenes();
		Main.inhabilitarMenu();
		inicializarResponsabilidades();
		inicializarTootlip();
		inputCorreo.setTextFormatter(new TextFormatter<>((change) -> {
			change.setText(change.getText().toLowerCase());
			return change;
		}));

        choiceDepartamento.getItems().addAll("Sistemas y desarrollo", "Comercial y publicidad", "Servicios compartidos"); 

    	comboCiudad.getItems().addAll("Madrid", "Santiago de Compostela", "Granada", "León");

		empleado = new Empleado();

    }
	/*
	 * Método que permite mandar los datos del empleado que se ha creado o modificado para que los valide
	 */
	@FXML
	private void guardarEmpleado(ActionEvent event) {
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
	/*
	 * Método que permite descartar los cambios y lanza una alerta de confirmación de dicho descarte
	 */
	@FXML
	void descartar(ActionEvent event) {
		Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
		confirmAlert.getDialogPane().addEventFilter(KeyEvent.KEY_PRESSED, Main.getManejo());
		confirmAlert.setTitle("Confirmar descarte empleado");
		confirmAlert.setHeaderText("¿Estás seguro de que quieres descartar los cambios?");

		Optional<ButtonType> result = confirmAlert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			Main.verEmpleados();
		}

	}
	/*
	 * Método que gestiona el evento de arrastrar un fichero sobre el control de la imagen del empleado
	 */
	@FXML
	void handleDragOver(DragEvent event) {
		if (event.getDragboard().hasFiles()) {
			event.acceptTransferModes(TransferMode.ANY);
		}
	}
	/*
	 * Método que gestiona el evento de soltar un fichero sobre el control de la imagen del empleado, recoge dicha imagen
	 * y modifica los estilos de la imagen
	 */
	@FXML
	void handleDrop(DragEvent event) throws FileNotFoundException {
		List<File> files = event.getDragboard().getFiles();
		Image imagen = new Image(new FileInputStream(files.get(0)));
		if(imagen!=null) {
			noImagen.setImage(imagen);
			noImagen.setOpacity(1);
			plus.setOpacity(0);
		}

	}
	/*
	 * Método que permite añadir una imagen a través del explorador de archivos
	 */
	@FXML
	void aniadirImagen(ActionEvent event) throws FileNotFoundException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selecciona una imagen");
		File imagen = fileChooser.showOpenDialog(Main.getStage());
		if(imagen!=null) {
			Image img = new Image(new FileInputStream(imagen));
			noImagen.setImage(img);
			noImagen.setOpacity(1);
			plus.setOpacity(0);
		}

	}
	/*
	 * Método que comprueba si se desea crear o editar un empleado y manda a la sección del tutorial correspondiente
	 */
	@FXML
	void irAyuda(ActionEvent event) {
		if(editar){
			Main.ayudaEditar();
		}else{
			Main.ayudaCrear();
		}

	}
	/*
	 * Método que establece una url a la imágen ya predefinida, por si la url del fxml falla
	 */
	private void urlImagenes(){
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("resources/images/no-image.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		noImagen.setImage(new Image(fis));
		noImagen.setPreserveRatio(true);
	}
	/*
	 * Método que añade los elementos de la lista de responsabilidades, y permite seleccionarlos de forma dinámica, añadiéndose la opción
	 * de elegir responsabilidad cada vez que se elige una, eliminando de las opciones las que ya han sido escogidas, cambiando el estilo
	 * de la opción de elegir una responsabilidad y estableciendo un menú de contexto a las responsabilidades ya escogidas para permitir 
	 * eliminarlas si se desea
	 */
	private void inicializarResponsabilidades(){
		responsabilidades.addAll("Programación", "Diseño", "Gestión bases de datos", "Actualizaciones", "Mantenimiento aplicación", "Captación y mantenimiento de sponsors", "Relación con usuarios", "Mantenimiento redes sociales", "Administración de empresa", "RRHH", "Contabilidad", "Contacto colaboradores");
		eleccion = FXCollections.observableArrayList();
		if (eleccion.isEmpty()) {
			eleccion.add("Elige una responsabilidad");
		}

		listResponsabilidades.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!(eleccion.get(eleccion.size() - 1).equals("Elige una responsabilidad"))) {
					eleccion.add("Elige una responsabilidad");
				}
				responsabilidades.remove(newValue);
			}
		});
		listResponsabilidades.setItems(eleccion);

		listResponsabilidades.setCellFactory(lv -> {
			ListCell<String> cell = new ComboBoxListCell<>(responsabilidades);

			ContextMenu contextMenu = new ContextMenu();

			MenuItem eliminar = new MenuItem("Eliminar");
			eliminar.setOnAction(e->confirmacionEliminar(cell.getItem()));

			contextMenu.getItems().add(eliminar);
			cell.itemProperty().addListener((observableValue, oldValue, newValue) ->{
				if(newValue!= null && newValue.equals("Elige una responsabilidad")){
					cell.setStyle("-fx-background-color: -fx-gris-medio; -fx-text-fill: -fx-blanco");
				}else{
					if(cell.getIndex() % 2 == 0){
						cell.setStyle("-fx-background-color: white; -fx-text-fill: -fx-negro");
					}else{
						cell.setStyle("-fx-background-color: -fx-blanco; -fx-text-fill: -fx-negro");
					}

				}
			});
			cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
				if (isNowEmpty) {
					cell.setContextMenu(null);
				} else {
					cell.itemProperty().addListener((observableValue, oldValue, newValue) ->{
						if (newValue!= null && newValue.equals("Elige una responsabilidad")) {
							cell.setContextMenu(null);
						} else if(newValue== null) {
							cell.setContextMenu(null);
						}
						else{
							cell.setContextMenu(contextMenu);
						}
					});
				}
			});


			return cell;
		});

		listResponsabilidades.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
	/*
	 * Método que muestra el tooltip cuando se posiciona el ratón sobre el icono de interrogación y lo oculta cuando se quita el cursor
	 */
	private void inicializarTootlip(){
		tooltip.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				Point2D point = tooltip.localToScene(0.0,  0.0);
				Main.mostrarPopup(point);
			}

		});
		tooltip.setOnMouseExited(
				e->Main.ocultarPopup());
	}
	/*
	 * Método que se ejecuta cuando se desea modificar un empleado, se cambian los valores de algunas etiquetas, y se rellenan los campos
	 * con los datos del empleado
	 */
    
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
		switch (empleado.getPosicion()){
			case "Empleado":
				rEmpleado.setSelected(true);
				break;
			case "Administrador":
				rAdministrador.setSelected(true);
				break;
			case "Director":
				rDirector.setSelected(true);
				break;
		}
    }
    /*
     * Método que muestra una alerta de confirmación y en caso de que se confirme, elimina el elemento de la lista de responsabilidades
     * que se haya seleccionado
     */
	private void confirmacionEliminar(String item){
		Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
		confirmAlert.getDialogPane().addEventFilter(KeyEvent.KEY_PRESSED, Main.getManejo());
		confirmAlert.setTitle("Confirmar borrar responsabilidad");
		confirmAlert.setHeaderText("¿Estás seguro de que quieres borrar la responsabilidad?");

		Optional<ButtonType> result = confirmAlert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			if(listResponsabilidades.getItems().size() == 1){
				listResponsabilidades.getItems().add("Elige una responsabilidad");
			}
			responsabilidades.add(item);
			listResponsabilidades.getItems().remove(item);

		}
	}

}
