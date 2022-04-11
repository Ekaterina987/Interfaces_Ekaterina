package ch.makery.address.view;


import ch.makery.address.Main;
import ch.makery.address.model.Empleado;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;


public class MenuController {
	@FXML
	private ImageView logoDemtr;

	@FXML
	private FlowPane inicio;
	@FXML
	private MenuItem menuBorrar;
	@FXML
	private MenuItem menuEditar;

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

		inhabilitarMenu();


	}

	@FXML
	private void crear(ActionEvent event) {
		Main.crear();
	}

	@FXML
	private void editar(ActionEvent event) {
		Main.editarEmpleado();
	}
	@FXML
	void borrar(ActionEvent event) {
		Main.borrarEmpleado();
	}
	@FXML
	private void verUsuarios(ActionEvent event) {
		Main.verEmpleados();
	}
	@FXML
	private void abrirTutorial(ActionEvent event) {
		Main.abrirTutorial();
	}
	@FXML
	private void cerrarListado(ActionEvent event) {
		Main.cerrarListado();
	}

	public void inhabilitarMenu(){
		menuEditar.setDisable(true);
		menuBorrar.setDisable(true);
	}
	public void habilitarMenu(){
		menuEditar.setDisable(false);
		menuBorrar.setDisable(false);
	}


	public FlowPane getInicio() {
		return inicio;
	}

}
