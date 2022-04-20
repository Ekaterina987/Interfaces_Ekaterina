package ch.makery.address.view;



import ch.makery.address.Main;
import ch.makery.address.model.Person;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

public class PersonController {

	@FXML
	private TableColumn<Person, String> firstNameColumn;

	@FXML
	private TableColumn<Person, String> lastNameColumn;

	@FXML
    private Label labelBirthday;

    @FXML
    private Label labelCity;

    @FXML
    private Label labelFirstName;

    @FXML
    private Label labelLastName;

    @FXML
    private Label labelPC;

    @FXML
    private Label labelStreet;
    
	@FXML
	private TableView<Person> personTable;

	private Person persona;

	public Person getPersona() {
		return persona;
	}

	public void setPersona(Person persona) {
		this.persona = persona;
	}

	@FXML
	void initialize() {
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("lastName"));
        

	}
	public void setData(ObservableList<Person> data){
		// Se rellena la tabla con objetos de la clase Person
		personTable.setItems(data);

		personTable.setRowFactory(tv -> {
			TableRow<Person> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY
						&& event.getClickCount() == 1) {

					persona = row.getItem();
					generarValores(persona);

				}
			});
			return row ;
		});
	}
	
	public void generarValores(Person persona) {
		labelFirstName.setText(persona.getFirstName());
		labelLastName.setText(persona.getLastName());
		labelStreet.setText(persona.getStreet());
		labelCity.setText(persona.getCity());
		labelPC.setText(Integer.toString(persona.getPC()));
		labelBirthday.setText(persona.getBirthday());

	}
	public void resetValores(){
		labelFirstName.setText("No hay datos");
		labelLastName.setText("No hay datos");
		labelStreet.setText("No hay datos");
		labelCity.setText("No hay datos");
		labelPC.setText("No hay datos");
		labelBirthday.setText("No hay datos");
	}
	
	@FXML
    private void crearPersona(ActionEvent event) {   
		Main.crearPersona();
    }
	@FXML
	void editarPersona(ActionEvent event) {
		Main.editarPersona(this.persona);
	}
	@FXML
	void borrarPersona(ActionEvent event) {
		Main.borrarPersona(this.persona);
	}


}