module EjerciciosEventos_Dialogos_Unidad10 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	
	opens ch.makery.address to javafx.graphics, javafx.fxml;
	opens ch.makery.address.view.eventosValidar to javafx.graphics, javafx.fxml;
	opens ch.makery.address.view.draggableFiles to javafx.graphics, javafx.fxml;
	opens ch.makery.address.view.choiceEjercicio to javafx.graphics, javafx.fxml;
	opens ch.makery.address.view.pagination to javafx.graphics, javafx.fxml;
}
