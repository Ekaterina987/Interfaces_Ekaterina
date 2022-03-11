package ch.makery.address.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import ch.makery.address.Main;
import ch.makery.address.model.DateUtil;
import ch.makery.address.model.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class DialogoController {

	
    @FXML
    private TextField birthday;

    @FXML
    private TextField city;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField pc;

    @FXML
    private TextField street;
    
    //private TextField[] fields;
    private Map<String, TextField> fields = new HashMap<>();
    
    private List<String> errores = new ArrayList<>();
    
    Alert errorAlert = new Alert(AlertType.ERROR);
    Alert infoAlert = new Alert(AlertType.INFORMATION);
    
    private Main main;
    
    private PersonController peC;
    

    @FXML
    void cancelAction(ActionEvent event) {

    }

    @FXML
    void okAction(ActionEvent event) {
    	Iterator<String> it = fields.keySet().iterator();
    	 
    	while(it.hasNext()){
    	    String clave = it.next();
    	    TextField valor = fields.get(clave);
    	    String error = "";
    	    if (valor.getText().equals("")) {
    	    	error = "El campo " + clave + " est� vac�o";
    	    	errores.add(error);
    	    }
    	    if(clave.equals("postal code")) {
    	    	try {
    	    		int pc = Integer.parseInt(valor.getText());
    	    	}catch(Exception e) {
    	    		error = "Postal code no v�lido. Debe ser un n�mero entero";
    	    		errores.add(error);
    	    	}
    	    } else if(clave.equals("birthday")) {
    	    	if (DateUtil.parse(valor.getText()) == null) {
    	    		error = "El campo " + clave + " no es v�lido. Usa el formato dd/mm/yyyy";
    	    		errores.add(error);
    	    	}
    	    }
    	    
    	}
    	if(errores.size()>0) {
	    	errorAlert.setTitle("Hay campos incorrectos");
	    	errorAlert.setHeaderText("Por favor, rellena correctamente los campos");
	    	errorAlert.setContentText(String.join("\n", errores));
	    	
	
	    	errorAlert.showAndWait();
	    	errores.clear();
    	}else {
    		infoAlert.setTitle("�xito");
	    	infoAlert.setHeaderText("Se ha creado la persona");

	    	infoAlert.showAndWait();
	    	Person persona = new Person(firstName.getText(), lastName.getText(), street.getText(), city.getText(), Integer.parseInt(pc.getText()), birthday.getText());
	    	peC.aniadirPersona(persona);
    	}
    }

    @FXML
    void initialize() {
    	fields.put("first name", firstName);
    	fields.put("last name", lastName);
    	fields.put("street", street);
    	fields.put("city", city);
    	fields.put("postal code", pc);
    	fields.put("birthday", birthday);

    }
    
    public void setMain(Main main) {
		this.main = main;
	}
    public void setPersonController(PersonController peC) {
    	this.peC = peC;
    }

}
