package ch.makery.address.view;


import java.util.HashMap;
import java.util.Map;
import ch.makery.address.Main;
import ch.makery.address.model.Person;
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

	private Person persona;

    private Map<String, TextField> fields;


	@FXML
    void cancelAction(ActionEvent event) {

    }

    @FXML
    void okAction(ActionEvent event) {
		Main.validar(persona, fields);
    }

    @FXML
    void initialize() {

        fields = new HashMap<>();
    	fields.put("first name", firstName);
    	fields.put("last name", lastName);
    	fields.put("street", street);
    	fields.put("city", city);
    	fields.put("postal code", pc);
    	fields.put("birthday", birthday);
        persona = new Person();


    }

	public  void editar(Person persona){
			this.persona = persona;
			firstName.setText(persona.getFirstName());
			lastName.setText(persona.getLastName());
			street.setText(persona.getStreet());
			city.setText(persona.getCity());
			pc.setText(Integer.toString(persona.getPC()));
			birthday.setText(persona.getBirthday());
	}


}
