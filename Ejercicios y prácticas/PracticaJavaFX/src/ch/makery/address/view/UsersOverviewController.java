package ch.makery.address.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UsersOverviewController {

	@FXML
	private TableColumn<?, ?> colApellidos;

	@FXML
	private TableColumn<?, ?> colCiudad;

	@FXML
	private TableColumn<?, ?> colCorreo;

	@FXML
	private TableColumn<?, ?> colFecha;

	@FXML
	private TableColumn<?, ?> colNombre;

	@FXML
	private TableColumn<?, ?> colPosicion;

	@FXML
	private TableColumn<?, ?> colPuesto;

	@FXML
	private TableColumn<?, ?> colResponsabilidades;

	@FXML
	private TableView<?> tablaEmpleados;

	@FXML
	private TreeView<String> treeDepartamentos;


	@FXML
	void initialize() {
		FileInputStream fis = null;
		FileInputStream fis1 = null;
    	try {
			fis = new FileInputStream("images/empleados.png");
			fis1 = new FileInputStream("images/departamento.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// Icono para el TreeView
    	ImageView imageView = new ImageView(new Image(fis)); 
    	imageView.setPreserveRatio(true);
    	imageView.setFitHeight(40);
    	
    	ImageView imageView1 = new ImageView(new Image(fis1)); 
    	imageView1.setPreserveRatio(true);
    	imageView1.setFitHeight(40);
    	
		TreeItem<String> rootItem = new TreeItem<String>("Todos", imageView);
		TreeItem<String> departamentosItem = new TreeItem<String>("Departamentos", imageView1);
		// Ítem de primer nivel
		rootItem.getChildren().add(departamentosItem);
		departamentosItem.getChildren().add(new TreeItem<String>("Sistemas y desarrollo"));
		departamentosItem.getChildren().add(new TreeItem<String>("Comercial y publicidad"));
		departamentosItem.getChildren().add(new TreeItem<String>("Servicios Compartidos"));

		treeDepartamentos.setCellFactory(TextFieldTreeCell.forTreeView());
		// Expadimos por defecto el ítem raíz
		rootItem.setExpanded(true);
		treeDepartamentos.setRoot(rootItem);

	}

}
