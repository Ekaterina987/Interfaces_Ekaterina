package ch.makery.address.view;

import java.util.HashMap;
import java.util.Map;

import ch.makery.address.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class PersonMapController {
	@FXML
	private TableColumn<Map, String> firstNameColumn;

	@FXML
	private TableColumn<Map, String> lastNameColumn;

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
	private TableView<Map> personTable;
	
	public static final String Column1MapKey = "Name";
    public static final String Column2MapKey = "Last Name";
    
    private ObservableList<Map> data = FXCollections.observableArrayList();
    Person hans = new Person("Hans", "Muster", "Elm", "Bremen", 342146, "21/08/1956");
	Person ruth = new Person("Ruth", "Mueller", "Birch", "Munich", 379523, "12/05/1972");
	Person heinz = new Person("Heinz", "Kurz", "Pine", "Berlin", 876532, "03/12/1995");
	Person cornelia = new Person("Cornelia", "Meier", "Willow", "Hamburgo", 534234, "19/04/1967");
	Person werner = new Person("Werner", "Meyer", "Oak", "Colonia", 543245, "07/07/2003");
	Person lydia = new Person("Lydia", "Kunz", "Elder", "Dusseldorf", 146217, "16/11/1984");
	Person anna = new Person("Anna", "Best", "Maple", "Dresde", 852542, "05/06/1997");
	Person stefan = new Person("Stefan", "Meier", "Walnut", "Nuremberg", 557217, "31/10/2006");
	Person martin = new Person("Martin", "Mueller", "Spruce", "Leipzig", 956424, "11/01/1934");
	
	Person[] people = {hans, ruth, heinz, cornelia, werner, lydia, anna, stefan, martin};
	
	@FXML
	void initialize() {
		firstNameColumn.setCellValueFactory(new MapValueFactory(Column1MapKey));

        lastNameColumn.setCellValueFactory(new MapValueFactory(Column2MapKey));
        
        for (Person p : people) {
        	Map<String, String> dataRow = new HashMap<>();
    		dataRow.put(Column1MapKey, p.getFirstName());
    		dataRow.put(Column2MapKey, p.getLastName());
    		
    		data.add(dataRow);
        }
        
        personTable.setItems(data);
        
        personTable.setEditable(true);
        personTable.getSelectionModel().setCellSelectionEnabled(true);
        personTable.getColumns().setAll(firstNameColumn, lastNameColumn);
        
        Callback<TableColumn<Map, String>, TableCell<Map, String>>
        cellFactoryForMap = new Callback<TableColumn<Map, String>,
            TableCell<Map, String>>() {
                @Override
                public TableCell call(TableColumn p) {
                    return new TextFieldTableCell(new StringConverter() {
                        @Override
                        public String toString(Object t) {
                            return t.toString();
                        }
                        @Override
                        public Object fromString(String string) {
                            return string;
                        }                                    
                    });
                }
    };
    firstNameColumn.setCellFactory(cellFactoryForMap);
    lastNameColumn.setCellFactory(cellFactoryForMap);
	}
}
