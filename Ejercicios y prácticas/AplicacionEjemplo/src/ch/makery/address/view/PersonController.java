package ch.makery.address.view;

import ch.makery.address.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonController {


    @FXML
    private TableColumn<Person, String> firstNameColumn;

    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private TableView<Person> personTable;

    @FXML
    void initialize() {
    	
    }

}