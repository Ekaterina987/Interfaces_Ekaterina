package ch.makery.address.view;


import ch.makery.address.Main;
import ch.makery.address.model.Empleado;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class MenuController {
	private Main main;
	@FXML
	private ImageView logoDemtr;

	@FXML
	private FlowPane inicio;
	@FXML
	void initialize() {

		FileInputStream fis = null;
		try {
			fis = new FileInputStream("images/logo1.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		logoDemtr.setImage(new Image(fis));
		logoDemtr.setPreserveRatio(true);


	}

	@FXML
	private void crear(ActionEvent event) {
		main.crear();
	}

	@FXML
	private void editar(ActionEvent event) {
		main.editar(new Empleado());
	}
	@FXML
	private void verUsuarios(ActionEvent event) {
		main.verEmpleados();
	}
	@FXML
	private void abrirTutorial(ActionEvent event) {
		main.abrirTutorial();
	}
	@FXML
	private void cerrarListado(ActionEvent event) {
		main.cerrarListado();
	}


	public void setMain(Main main) {
		this.main = main;
	}

	public FlowPane getInicio() {
		return inicio;
	}

}
