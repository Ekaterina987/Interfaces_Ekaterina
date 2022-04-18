package ch.makery.address.view.help;

        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.net.URL;
        import java.util.ResourceBundle;

        import ch.makery.address.Main;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Accordion;
        import javafx.scene.control.ProgressBar;
        import javafx.scene.control.TitledPane;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;
        import javafx.scene.layout.StackPane;

public class HelpController {


    @FXML
    private ImageView borrarEmpleados;

    @FXML
    private ImageView crearEmpleados;

    @FXML
    private ImageView editarEmpleados;

    @FXML
    private ImageView navegar;

    @FXML
    private ImageView verEmpleados;

    @FXML
    private ProgressBar barraProgreso;

    @FXML
    private Accordion acordeon;

    @FXML
    private TitledPane paneBorrar;

    @FXML
    private TitledPane paneCrear;

    @FXML
    private TitledPane paneEditar;

    @FXML
    private TitledPane paneNavegar;

    @FXML
    private TitledPane paneVer;

    @FXML
    private ImageView aniadirImagen;

    @FXML
    private ImageView ayudaContrasenia;

    @FXML
    private ImageView filtrarEmpleados;

    public TitledPane getPaneBorrar() {
        return paneBorrar;
    }

    public TitledPane getPaneCrear() {
        return paneCrear;
    }

    public TitledPane getPaneEditar() {
        return paneEditar;
    }

    public TitledPane getPaneNavegar() {
        return paneNavegar;
    }

    public TitledPane getPaneVer() {
        return paneVer;
    }

    public Accordion getAcordeon() {
        return acordeon;
    }

    @FXML
    void initialize() {
        inicializarImagenes();
        progresoAcordeon();

    }
    private void inicializarImagenes(){
        FileInputStream fis = null;
        FileInputStream fis1 = null;
        FileInputStream fis2 = null;
        FileInputStream fis3 = null;
        FileInputStream fis4 = null;
        FileInputStream fis5 = null;
        FileInputStream fis6 = null;
        FileInputStream fis7 = null;
        try {
            fis = new FileInputStream("resources/images/menu.png");
            fis1 = new FileInputStream("resources/images/verEmpleados.png");
            fis2 = new FileInputStream("resources/images/crearEmpleados.png");
            fis3 = new FileInputStream("resources/images/modificarEmpleado.png");
            fis4 = new FileInputStream("resources/images/borrarEmpleado.png");
            fis5 = new FileInputStream("resources/images/filtro.png");
            fis6 = new FileInputStream("resources/images/subirImagen.png");
            fis7 = new FileInputStream("resources/images/contrasenia.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        navegar.setImage(new Image(fis));
        verEmpleados.setImage(new Image(fis1));
        crearEmpleados.setImage(new Image(fis2));
        editarEmpleados.setImage(new Image(fis3));
        borrarEmpleados.setImage(new Image(fis4));
        filtrarEmpleados.setImage(new Image(fis5));
        aniadirImagen.setImage(new Image(fis6));
        ayudaContrasenia.setImage(new Image(fis7));

    }
    private void progresoAcordeon(){
        barraProgreso.setProgress(0);
        acordeon.expandedPaneProperty().addListener((observable, oldValue, newValue) -> {
            int valor = -1;
            for(int i = 0; i < acordeon.getPanes().size(); i++){
                TitledPane tp = acordeon.getPanes().get(i);
                if(tp == newValue){
                    valor = i;
                }
            }
            double fraccion = 1 / (double)acordeon.getPanes().size();
            double valorFinal = fraccion * (valor + 1);
            barraProgreso.setProgress((valorFinal));

        });
    }
    @FXML
    void ayudaBorrar(ActionEvent event) {
        acordeon.setExpandedPane(paneBorrar);
    }


}
