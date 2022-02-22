package ch.makery.address.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Separator;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class MainControlador {

	@FXML
	private ChoiceBox<String> locationBox;
	
	@FXML
	public static final ObservableList<String> java = 
	        FXCollections.observableArrayList();
	
	@FXML
	public static final ObservableList<String> data = 
    		FXCollections.observableArrayList();
	
	@FXML
	ListView<String> qualificationsListView;
	
	@FXML
	 private ComboBox<String> idiomaCombo;
	
	@FXML
	 private TreeView<String> tree1;

	private final Node rootIcon =  new ImageView(new Image(getClass().getResourceAsStream("folder-icon.png")));

    @FXML
    void initialize() {
    	Separator sep = new Separator();
        sep.setMaxWidth(80);
        //Adding separator to the choice box
        ObservableList list = locationBox.getItems();
    	list.addAll("New York", "Orlando", sep, "London", "Manchester"); 
    	
    	java.addAll("Objects", "Classes", "Functions", "Variables", "Compiler", "Debugger", "Projects", "Beans", "Libraries", "Modules");
    	for (int i = 0; i < 10; i++) {
    		data.add("Indeterminate (pick a choice)");
        }
    	qualificationsListView.setItems(data);
    	qualificationsListView.setCellFactory(ComboBoxListCell.forListView(java));
    	
    	qualificationsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	
    	idiomaCombo.getItems().addAll("English", "Japanese", "Spanish");
    	
    	
    	
    	TreeItem<String> rootItem = new TreeItem<String>("Inbox", rootIcon);
    	 // Ítem de primer nivel
    	rootItem.getChildren().add(new TreeItem<String>("Sales"));
    	 rootItem.getChildren().add(new TreeItem<String>("Marketing"));
    	 rootItem.getChildren().add(new TreeItem<String>("Distribution"));
    	 rootItem.getChildren().add(new TreeItem<String>("Costs"));
    	 
    	 tree1.setCellFactory(TextFieldTreeCell.forTreeView());
    	 // Expadimos por defecto el ítem raíz
    	 rootItem.setExpanded(true);
    	 tree1.setRoot(rootItem); 


    }

}