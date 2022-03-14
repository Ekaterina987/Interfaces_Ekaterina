package ch.makery.address;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	 private AnchorPane rootLayout;
	 
	 
	 
	 @Override
	 public void start(Stage primaryStage) {
		 this.primaryStage = primaryStage;
		 this.primaryStage.setTitle("Di�logos");
		 initRootLayout();

	 }
	 /** Inicializa el dise�o de la pantalla principal. */
	 public void initRootLayout() {
	try {
	 // Carga el XML con el dise�o principal
	 FXMLLoader loader = new FXMLLoader();

	 loader.setLocation(Main.class.getResource("view/eventosValidar/Eventos.fxml"));
	 rootLayout = (AnchorPane) loader.load();
	 // Se a�ade el dise�o principal a la escena
	 Scene scene = new Scene(rootLayout);
	 primaryStage.setScene(scene);
	 primaryStage.show();
	} catch (IOException e) {
	 e.printStackTrace();
	}
	 }

	 /** Returns the main stage. */
	 public Stage getPrimaryStage() {
	 return primaryStage;
	 }
	 public static void main(String[] args) {
		 launch(args);
	 }

}