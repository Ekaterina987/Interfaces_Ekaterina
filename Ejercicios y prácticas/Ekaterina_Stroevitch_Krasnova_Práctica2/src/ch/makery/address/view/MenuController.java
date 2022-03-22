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
import java.util.ArrayList;
import java.util.Arrays;


public class MenuController {
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
		Main.crear();
	}

	@FXML
	private void editar(ActionEvent event) {
		ArrayList<String> resp = new ArrayList<>(Arrays.asList("Administración de empresa", "RRHH", "Contabilidad", "Contacto colaboradores"));
		Main.editar(new Empleado("Tony", "Ávila", "tonyavila@demtr.com", "c0ntra5eniA", "Servicios compartidos", "Director", "Jefe", resp,"06/03/2022", "Madrid"));
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


	public FlowPane getInicio() {
		return inicio;
	}

}
