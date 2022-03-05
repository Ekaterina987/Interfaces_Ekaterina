package ch.makery.address.view;




import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.ComboBoxListCell;

public class UserCreateController {


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
    void initialize() {
        choiceDepartamento.getItems().addAll("Sistemas y desarrollo", "Comercial y publicidad", "Servicios compartidos"); 
    	
        responsabilidades.addAll("Programación", "Diseño", "Gestión bases de datos", "Actualizaciones", "Mantenimiento aplicación", "Captación y mantenimiento de sponsors", "Relación con usuarios", "Mantenimiento redes sociales", "Administración de empresa", "RRHH", "Contabilidad", "Contacto colaboradores");

    	eleccion.add("Elige una responsabilidad");
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

}
