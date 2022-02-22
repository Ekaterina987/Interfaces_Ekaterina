package ch.makery.address.view;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Separator;
import javafx.scene.control.cell.ComboBoxListCell;


public class MainControlador {

	@FXML
	private ChoiceBox<String> locationBox;
	
	public static final ObservableList java = 
	        FXCollections.observableArrayList();
	
	@FXML
	//private ListView<String> qualificationsListView;
	public static final ObservableList data = 
    		FXCollections.observableArrayList();
	@FXML
	 private ComboBox<String> idiomaCombo;


    @FXML
    void initialize() {
    	Separator sep = new Separator();
        sep.setMaxWidth(80);
        //Adding separator to the choice box
        ObservableList list = locationBox.getItems();
    	list.addAll("New York", "Orlando", sep, "London", "Manchester"); 
    	//for (int i = 0; i < 10; i++) {
		//			qualificationsListView.getItems().add("Indeterminate (pick a choice)");
		//	    }
    	final ListView qualificationsListView = new ListView(data);
    	java.addAll("Objects", "Classes", "Functions", "Variables", "Compiler", "Debugger", "Projects", "Beans", "Libraries", "Modules");
    	for (int i = 0; i < 10; i++) {
    		data.add("Indeterminate (pick a choice)");
        }
    	qualificationsListView.setItems(data);
    	qualificationsListView.setCellFactory(ComboBoxListCell.forListView(java));
    	
    	qualificationsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	
    	idiomaCombo.getItems().addAll("English", "Japanese", "Spanish");

    }

}