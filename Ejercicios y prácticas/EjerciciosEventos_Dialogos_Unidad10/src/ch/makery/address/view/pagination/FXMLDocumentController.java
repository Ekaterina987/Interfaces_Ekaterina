package ch.makery.address.view.pagination;

import ch.makery.address.model.Nombres;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class FXMLDocumentController {
    @FXML
    private ProgressBar proggressBar;
    @FXML
    private Pagination pagination;

    @FXML
    void initialize() {

        pagination.setMaxPageIndicatorCount(4);
        pagination.setPageCount(4);
        pagination.setStyle("-fx-border-color:#5a708c;-fx-background-color:#ccdcf6;");
        pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                return createPage(pageIndex);
            }
        });
        int paginas = pagination.getPageCount();
        double fraccion = 1 / (double)paginas;
        double valor = fraccion * ((double) pagination.getCurrentPageIndex() + 1);
        proggressBar.setProgress((valor));
        pagination.currentPageIndexProperty().addListener((observable, oldValue, newValue) -> {

            double valor1 = fraccion * (newValue.doubleValue() + 1);
            proggressBar.setProgress((valor1));

        });
    }
    public VBox createPage(int pageIndex) {
        int itemsPerPage = 5;
        VBox box = new VBox();
        int page = pageIndex * itemsPerPage;
        for (int i = page; i < page + itemsPerPage; i++) {

            Label text = new Label( (i+1) + ".- " + Nombres.getFullName());
            text.setStyle("-fx-font-size:14;-fx-background-color:#92b5e7 ;-fx-pref-width:300px;-fx-padding:8px 0 8px 8px;");
            box.getChildren().add(text);
        }
        return box;
    }

}