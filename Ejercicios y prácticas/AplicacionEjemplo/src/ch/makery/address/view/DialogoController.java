package ch.makery.address.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ch.makery.address.model.DateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
    	    	if (DateUtil.parse(valor.getText()).equals(null)) {
    	    		error = "El campo " + valor + " no es v�lido. Usa el formato dd/mm/yyyy";
    	    		errores.add(error);
    	    	}
    	    }
    	    
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

}
