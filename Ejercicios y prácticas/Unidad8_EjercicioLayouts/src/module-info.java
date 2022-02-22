module Unidad8_EjercicioLayouts {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	
	opens ch.makery.address to javafx.graphics, javafx.fxml;
	opens ch.makery.address.view to javafx.fxml;
}
