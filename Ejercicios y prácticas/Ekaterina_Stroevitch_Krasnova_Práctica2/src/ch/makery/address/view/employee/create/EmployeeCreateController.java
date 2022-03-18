package ch.makery.address.view.employee.create;




import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import ch.makery.address.Main;
import ch.makery.address.model.Empleado;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
	
	private Main main;
	
	private Empleado empleado;
	
	private RadioButton posicionSeleccionada;
	
	
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
    	
    }
    
    public void cambiarLabel(String label, String button) {
    	empleadoLabel.setText(label);
    	mainButton.setText(button);
    }
    
    public void setMain(Main main) {
    	this.main = main;
    }
    
    public void setEmpleado(Empleado empleado) {
    	this.empleado = empleado;
    }
    @FXML
    private void guardarEmpleado(ActionEvent event) {
		posicionSeleccionada = (RadioButton) posicion.getSelectedToggle();
    	/*this.posicionSeleccionada = new RadioButton();
    	posicion.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
            	RadioButton posicionSelec = (RadioButton)t1.getToggleGroup().getSelectedToggle();
            	setPosicionSeleccionada(posicionSelec);
            }
        });*/
    	String fecha = "";
    	try {
    		 fecha = inputFecha.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    	}catch(Exception e){
    		
    	}
    	String resp = "";
    	if (listResponsabilidades.getItems().stream().map(Object::toString).collect(Collectors.joining(", ")) != null) {
    		ObservableList<String> ol = FXCollections.observableArrayList(listResponsabilidades.getItems());
    		ol.remove(listResponsabilidades.getItems().size() - 1);
    		resp = ol.stream().map(Object::toString).collect(Collectors.joining(", "));
    	}
    	String dept = "";
    	if (choiceDepartamento.getValue() != null) {
    		dept = choiceDepartamento.getValue();
    	}
    	String ciudad = "";
    	if (comboCiudad.getValue() != null) {
    		ciudad = comboCiudad.getValue();
    	}
    	main.validarDatos(inputNombre.getText(), inputApellidos.getText(), inputCorreo.getText(), 
    			inputContrasenia.getText(), dept, posicionSeleccionada.getText(),
    			inputPuesto.getText(), resp, fecha, ciudad);
    }
    
    private void setPosicionSeleccionada(RadioButton posicionSeleccionada) {
    	this.posicionSeleccionada = posicionSeleccionada;
    }

}
