package ch.makery.address;
	
import java.io.IOException;

import ch.makery.address.view.*;
import ch.makery.address.view.employee.create.EmployeeCreateController;
import ch.makery.address.view.employees.overview.EmployeesOverviewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class Main extends Application {
private BorderPane rootLayout;	
private MenuController menuController;
private EmployeeCreateController createController;
private EmployeesOverviewController overviewController;

	
	@Override
	public void start(Stage primaryStage) {
		setRootLayout(primaryStage);
	}
	public void setRootLayout(Stage primaryStage) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			menuController = loader.getController();
			menuController.setMain(this);

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Pr�ctica 2");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void crear() {    	
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MenuController.class.getResource("view/employee/create/EmployeeCreate.fxml"));
			GridPane ventanaCrear = (GridPane) loader.load();
			createController = loader.getController();
			createController.setMain(this);

			rootLayout.setCenter(ventanaCrear);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void editar() {    	
    	try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MenuController.class.getResource("view/employee/create/EmployeeCreate.fxml"));
			GridPane ventanaEditar = (GridPane) loader.load();

			createController = loader.getController();
			createController.setMain(this);
			
			createController.cambiarLabel("Modificar empleado", "Guardar");

			rootLayout.setCenter(ventanaEditar);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void verEmpleados() {    	
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MenuController.class.getResource("view/employees/overview/EmployeesOverview.fxml"));
			SplitPane ventanaEmpleados = (SplitPane) loader.load();
			
			overviewController = loader.getController();
			overviewController.setMain(this);

			rootLayout.setCenter(ventanaEmpleados);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	public void abrirTutorial() {    	
    	try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MenuController.class.getResource("view/help/Help.fxml"));
			AnchorPane tutorial = (AnchorPane) loader.load();
			
			rootLayout.setCenter(tutorial);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	public void cerrarListado() {    	
    	rootLayout.setCenter(menuController.getInicio());	
    }
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
