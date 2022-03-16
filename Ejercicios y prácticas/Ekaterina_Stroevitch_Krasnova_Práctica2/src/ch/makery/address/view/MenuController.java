package ch.makery.address.view;


import ch.makery.address.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;


public class MenuController {
		private Main main;
		
		 @FXML
		 private FlowPane inicio;
		
		@FXML
	    private void crear(ActionEvent event) {    	
	    	main.crear();
	    }

		@FXML
	    private void editar(ActionEvent event) {    	
	    	main.editar();
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
