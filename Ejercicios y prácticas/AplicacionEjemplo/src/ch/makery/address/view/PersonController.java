package ch.makery.address.view;

import ch.makery.address.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

	// Lista auxiliar para TableView
	private ObservableList<Person> data = FXCollections.observableArrayList(
			new Person("Hans", "Muster", "Elm", "Bremen", 342146, "21/08/1956"),
			new Person("Ruth", "Mueller", "Birch", "Munich", 379523, "12/05/1972"),
			new Person("Heinz", "Kurz", "Pine", "Berlin", 876532, "03/12/1995"),
			new Person("Cornelia", "Meier", "Willow", "Hamburgo", 534234, "19/04/1967"),
			new Person("Werner", "Meyer", "Oak", "Colonia", 543245, "07/07/2003"),
			new Person("Lydia", "Kunz", "Elder", "Dusseldorf", 146217, "16/11/1984"),
			new Person("Anna", "Best", "Maple", "Dresde", 852542, "05/06/1997"),
			new Person("Stefan", "Meier", "Walnut", "Nuremberg", 557217, "31/10/2006"),
			new Person("Martin", "Mueller", "Spruce", "Leipzig", 956424, "11/01/1934"));

	@FXML
	void initialize() {
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("lastName"));
        
        // Se rellena la tabla con objetos de la clase Person
        personTable.setItems(data); 
        
        personTable.setRowFactory(tv -> {
            TableRow<Person> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                     && event.getClickCount() == 1) {

                    Person clickedRow = row.getItem();
                    generarValores(clickedRow);
                    
                }
            });
            return row ;
        });
	}
	
	private void generarValores(Person persona) {
		labelFirstName.setText(persona.getFirstName());
		labelLastName.setText(persona.getLastName());
		labelStreet.setText(persona.getStreet());
		labelCity.setText(persona.getCity());
		labelPC.setText(Integer.toString(persona.getPC()));
		labelBirthday.setText(persona.getBirthday());
	}

}