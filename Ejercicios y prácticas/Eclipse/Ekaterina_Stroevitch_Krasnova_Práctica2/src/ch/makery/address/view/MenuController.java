package ch.makery.address.view;


import ch.makery.address.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class MenuController {
	@FXML
	private ImageView logoDemtr;

	@FXML
	private AnchorPane inicio;
	@FXML
	private MenuItem menuBorrar;
	@FXML
	private MenuItem menuEditar;
	public AnchorPane getInicio() {
		return inicio;
	}

	/*
	 * Se inicializan las imágenes e inhabilitan los menús de editar y borrar
	 */
	@FXML
	void initialize() {
		inicializarImagenes();
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
	private void volverInicio(ActionEvent event) {
		Main.volverInicio();
	}
	@FXML
	void irAyuda(ActionEvent event) {
		Main.ayudaNavegar();
	}

	public void inhabilitarMenu(){
		menuEditar.setDisable(true);
		menuBorrar.setDisable(true);
	}
	public void habilitarMenu(){
		menuEditar.setDisable(false);
		menuBorrar.setDisable(false);
	}
	/*
	 * Se da un valor a las imágenes por si la url del fxml falla
	 */
	private void inicializarImagenes(){
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("resources/images/logo1.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		logoDemtr.setImage(new Image(fis));
		logoDemtr.setPreserveRatio(true);
	}

}
