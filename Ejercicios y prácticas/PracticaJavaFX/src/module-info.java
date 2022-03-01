module PracticaJavaFX {
	requires javafx.controls;
	
	opens ch.makery.address to javafx.graphics, javafx.fxml;
}
