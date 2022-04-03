module ch.makery.address {
    requires javafx.controls;
    requires javafx.fxml;


    opens ch.makery.address.flowpanecss to javafx.fxml;
    opens ch.makery.address.layoutCompleto to javafx.fxml;
    exports ch.makery.address.flowpanecss;
    exports ch.makery.address.layoutCompleto;
}