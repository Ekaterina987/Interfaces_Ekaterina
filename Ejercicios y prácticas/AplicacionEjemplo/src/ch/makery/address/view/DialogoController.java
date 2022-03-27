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

	private Person persona;

    private Map<String, TextField> fields = new HashMap<>();

    private Main main;
    
    private PersonController peC;


	@FXML
    void cancelAction(ActionEvent event) {

    }

    @FXML
    void okAction(ActionEvent event) {
        System.out.println(firstName.getText());
		Main.validar(persona, fields, firstName.getText(), lastName.getText(), street.getText(), city.getText(), pc.getText(), birthday.getText());
    }

    @FXML
    void initialize() {


    	fields.put("first name", firstName);
    	fields.put("last name", lastName);
    	fields.put("street", street);
    	fields.put("city", city);
    	fields.put("postal code", pc);
    	fields.put("birthday", birthday);
        persona = new Person();


    }

	public  void editar(Person persona){
		if (persona!= null){
			this.persona = persona;
			firstName.setText(persona.getFirstName());
			lastName.setText(persona.getLastName());
			street.setText(persona.getStreet());
			city.setText(persona.getCity());
			pc.setText(Integer.toString(persona.getPC()));
			birthday.setText(persona.getBirthday());
		}
	}


}
