package ch.makery.address.view;

import ch.makery.address.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;

public class PersonMapController {
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
	
	public static final String Column1MapKey = "A";
    public static final String Column2MapKey = "B";
	
	@FXML
	void initialize() {
		firstNameColumn.setCellValueFactory(new MapValueFactory(Column1MapKey));

        lastNameColumn.setCellValueFactory(new MapValueFactory(Column2MapKey));
	}
}
