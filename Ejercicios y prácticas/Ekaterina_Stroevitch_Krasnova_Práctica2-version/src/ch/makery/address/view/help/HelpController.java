package ch.makery.address.view.help;

        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.net.URL;
        import java.util.ResourceBundle;
        import javafx.fxml.FXML;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;

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
    void initialize() {
        FileInputStream fis = null;
        FileInputStream fis1 = null;
        FileInputStream fis2 = null;
        FileInputStream fis3 = null;
        FileInputStream fis4 = null;
        try {
            fis = new FileInputStream("images/menu.png");
            fis1 = new FileInputStream("images/verEmpleados.png");
            fis2 = new FileInputStream("images/crearEmpleados.png");
            fis3 = new FileInputStream("images/modificarEmpleado.png");
            fis4 = new FileInputStream("images/borrarEmpleado.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        navegar.setImage(new Image(fis));
        verEmpleados.setImage(new Image(fis1));
        crearEmpleados.setImage(new Image(fis2));
        editarEmpleados.setImage(new Image(fis3));
        borrarEmpleados.setImage(new Image(fis4));

    }

}
