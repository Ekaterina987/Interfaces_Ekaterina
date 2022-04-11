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
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
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

	@FXML
	private RadioButton rEmpleado;

	@FXML
	private RadioButton rAdministrador;

	@FXML
	private RadioButton rDirector;
	@FXML
	private Label plus;

	private Empleado empleado;

	
	
    @FXML
    void initialize() {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("images/no-image.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		noImagen.setImage(new Image(fis));
		noImagen.setPreserveRatio(true);

		Main.inhabilitarMenu();


        choiceDepartamento.getItems().addAll("Sistemas y desarrollo", "Comercial y publicidad", "Servicios compartidos"); 
    	
        responsabilidades.addAll("Programación", "Diseño", "Gestión bases de datos", "Actualizaciones", "Mantenimiento aplicación", "Captación y mantenimiento de sponsors", "Relación con usuarios", "Mantenimiento redes sociales", "Administración de empresa", "RRHH", "Contabilidad", "Contacto colaboradores");

        if (eleccion.isEmpty()) {
        	eleccion.add("Elige una responsabilidad");
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
    	
    	comboCiudad.getItems().addAll("Madrid", "Santiago de Compostela", "Granada", "León");

		empleado = new Empleado();

    }
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

	@FXML
	void descartar(ActionEvent event) {
		Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
		confirmAlert.setTitle("Confirmar descarte empleado");
		confirmAlert.setHeaderText("¿Estás seguro de que quieres descartar los cambios?");

		Optional<ButtonType> result = confirmAlert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			Main.verEmpleados();
		}

	}
	@FXML
	void handleDragOver(DragEvent event) {
		if (event.getDragboard().hasFiles()) {
			event.acceptTransferModes(TransferMode.ANY);
		}
	}

	@FXML
	void handleDrop(DragEvent event) throws FileNotFoundException {
		List<File> files = event.getDragboard().getFiles();
		Image img = new Image(new FileInputStream(files.get(0)));
		noImagen.setImage(img);
	}
	@FXML
	void aniadirImagen(ActionEvent event) throws FileNotFoundException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selecciona una imagen");
		File imagen = fileChooser.showOpenDialog(Main.getStage());

		Image img = new Image(new FileInputStream(imagen));
		noImagen.setImage(img);
		noImagen.setOpacity(1);
		plus.setOpacity(0);

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

}
