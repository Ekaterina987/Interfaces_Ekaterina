package ch.makery.address.view.employee.create;




import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

}
