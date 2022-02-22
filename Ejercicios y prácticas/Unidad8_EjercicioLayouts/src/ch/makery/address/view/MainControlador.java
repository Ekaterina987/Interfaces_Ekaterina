package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;


public class MainControlador {

	@FXML
	private ChoiceBox<String> locationBox;


    @FXML
    void initialize() {
    	locationBox.getItems().addAll("New York", "Orlando", "London", "Manchester"); 
    }

}