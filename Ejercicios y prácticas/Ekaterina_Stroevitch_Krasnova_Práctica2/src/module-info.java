module Ekaterina_Stroevitch_Krasnova_Practica2 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens ch.makery.address to javafx.graphics, javafx.fxml;
	opens ch.makery.address.view to javafx.graphics, javafx.fxml;
	opens ch.makery.address.model to javafx.graphics, javafx.fxml, javafx.base;
	opens ch.makery.address.view.employee.create to javafx.graphics, javafx.fxml, javafx.base;
	opens ch.makery.address.view.employees.overview to javafx.graphics, javafx.fxml, javafx.base;
}
