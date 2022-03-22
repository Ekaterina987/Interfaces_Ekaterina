package ch.makery.address.view.draggableFiles;
import ch.makery.address.Main;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.annotation.Target;
import java.util.List;

public class DraggableViewController {
    private Main main;

    @FXML
    private ImageView imageView;
    @FXML
    private Label source;

    @FXML
    private Label target;

    @FXML
    void handleDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }
    @FXML
    void handleDrop(DragEvent event) throws FileNotFoundException {
        List<File> files = event.getDragboard().getFiles();
        Image img = new Image(new FileInputStream(files.get(0)));
        imageView.setImage(img);
    }
    @FXML
    void handleDragDetection(MouseEvent event) {
        Dragboard db = source.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putString(source.getText());
        db.setContent(cb);

        event.consume();
    }
    @FXML
    void handleTextDragOver(DragEvent event) {
        if (event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }
    @FXML
    void handleTextDrop(DragEvent event) {
        String str = event.getDragboard().getString();
        target.setText(str);
    }
    @FXML
    void handleDragDone(DragEvent event) {
        source.setText("Operación drag terminada");
    }
    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    void initialize() {
        source.addEventHandler(MouseEvent.MOUSE_PRESSED, ev -> {
            source.setCursor(Cursor.MOVE);
        });
        source.addEventHandler(MouseEvent.MOUSE_RELEASED, ev -> source.setCursor(Cursor.DEFAULT));
    }

}
