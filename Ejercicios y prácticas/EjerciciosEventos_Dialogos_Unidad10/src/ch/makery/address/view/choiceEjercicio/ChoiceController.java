package ch.makery.address.view.choiceEjercicio;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.input.MouseEvent;

public class ChoiceController {
    @FXML
    private ListView<String> lista;

    @FXML
    private Button boton;

    @FXML
    public static final ObservableList<String> opciones =
            FXCollections.observableArrayList();

    @FXML
    void showDialogo(ActionEvent event) {

        ObservableList<String> elementos = lista.getSelectionModel().getSelectedItems();

        ChoiceDialog<String> choiceDialog = new ChoiceDialog<String>(elementos.get(0), elementos);
        choiceDialog.setTitle("Ejemplo...");
        choiceDialog.setHeaderText("Seleccione un valor...");


        choiceDialog.showAndWait().ifPresent(response -> {
            lista.getSelectionModel().clearSelection();
            lista.getSelectionModel().select(response);
        });
    }

    @FXML
    void initialize(){
        for (int i = 1; i <= 10; i++){
            opciones.add("Opción " + i);
        }

        lista.setItems(opciones);
        lista.setCellFactory(ComboBoxListCell.forListView(opciones));

        lista.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        boton.disableProperty().bind(Bindings.isEmpty(lista.getSelectionModel().getSelectedItems()));
        EventHandler<MouseEvent> hover = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);

                alerta.setTitle("Aviso sobre listado");
                alerta.setHeaderText("Sin items seleccionados");
                alerta.setContentText("No se puede mostrar el diálogo hasta que se seleccione un ítem como mínimo");

                alerta.showAndWait();

            }
        };
        lista.addEventHandler(MouseEvent.MOUSE_EXITED,hover);

        lista.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            lista.removeEventHandler(MouseEvent.MOUSE_EXITED, hover);

        });

    }
}
