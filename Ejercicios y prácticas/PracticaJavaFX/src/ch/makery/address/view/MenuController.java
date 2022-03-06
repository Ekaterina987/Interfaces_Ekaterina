package ch.makery.address.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class MenuController {
	// Pantalla principal en la que se a�ade o quita contenido
		private BorderPane rootLayout;
		
		@FXML
	    private void crear(ActionEvent event) {    	
	    	try {
				// Cargamos el archivo Controles Din�micos
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MenuController.class.getResource("UserCreate.fxml"));
				GridPane listadoControles = (GridPane) loader.load();

				// Se sit�a en el centro del dise�o principal
				rootLayout.setCenter(listadoControles);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

		@FXML
	    private void editar(ActionEvent event) {    	
	    	try {
				// Cargamos el archivo Controles Din�micos
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MenuController.class.getResource("UserCreate.fxml"));
				GridPane listadoControles = (GridPane) loader.load();

				UserCreateController uc= loader.getController();
				
				uc.cambiarLabel("Modificar empleado", "Guardar");
				// Se sit�a en el centro del dise�o principal
				rootLayout.setCenter(listadoControles);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
		@FXML
	    private void verUsuarios(ActionEvent event) {    	
	    	try {
				// Cargamos el archivo Controles Din�micos
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MenuController.class.getResource("UsersOverview.fxml"));
				SplitPane listadoControles = (SplitPane) loader.load();

				// Se sit�a en el centro del dise�o principal
				rootLayout.setCenter(listadoControles);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
		@FXML
	    private void abrirTutorial(ActionEvent event) {    	
	    	try {
				// Cargamos el archivo Controles Din�micos
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MenuController.class.getResource("Help.fxml"));
				AnchorPane listadoControles = (AnchorPane) loader.load();

				// Se sit�a en el centro del dise�o principal
				rootLayout.setCenter(listadoControles);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    @FXML
	    private void cerrarListado(ActionEvent event) {    	
	    	// Se elimina el contenido del nodo central
	    	rootLayout.setCenter(null);	
	    }

		public BorderPane getRootLayout() {
			return rootLayout;
		}

		public void setRootLayout(BorderPane rootLayout) {
			this.rootLayout = rootLayout;
		}	

}
