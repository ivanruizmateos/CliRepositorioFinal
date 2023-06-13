package dam2.exercici2;


import com.mycompany.common.ILogin;
import com.mycompany.common.Lookups;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.naming.NamingException;

/**
 * Controlador para la vista de inicio de sesión.
 * Administra el inicio de sesión y el registro de nuevos usuarios.
 * Valida las credenciales ingresadas por el usuario.
 * Permite navegar a la vista de registro y al menú de selección de dificultad.
 * Crea una instancia de la interfaz ILogin utilizando Lookups.
 * Permite mostrar alertas de error en caso de credenciales inválidas.
 * @author
 */
public class LoginController implements Initializable {

    @FXML
    private Button btn_registrarse;

    @FXML
    private Button btn_login;

    @FXML
    private TextField nickT;

    @FXML
    private TextField emailT;

    static ILogin loginI;

    /**
     * Inicializa el controlador.
     *
     * @param url la ubicación del archivo FXML
     * @param resourceBundle los recursos específicos de localización
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loginI = createNewLogin();
        } catch (NamingException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Navega a la vista de registro.
     *
     * @param event el evento de acción
     * @throws IOException si ocurre un error al cambiar de vista
     */
    @FXML
    void switchToRegistro(ActionEvent event) throws IOException {
        App.setRoot("crearCuenta");
    }

    /**
     * Realiza el inicio de sesión y navega al menú de selección de dificultad.
     * Valida las credenciales ingresadas por el usuario.
     * Muestra una alerta de error en caso de credenciales inválidas.
     *
     * @param event el evento de acción
     * @throws IOException si ocurre un error al cambiar de vista
     * @throws NamingException si ocurre un error al crear la instancia de ILogin
     */
    @FXML
    void switchToMenu(ActionEvent event) throws IOException, NamingException {
        String email = emailT.getText();

        if (loginI.login(email).equals("Correcto")) {
            App.setRoot("seleccionDificultad");
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Error, las credenciales no son válidas");
            alert.showAndWait();
            nickT.clear();
            emailT.clear();
        }

    }
    /**
     * Crea una nueva instancia de la interfaz ILogin utilizando Lookups.
     *
     * @return la instancia de ILogin
     * @throws NamingException si ocurre un error al crear la instancia
     */
    public static ILogin createNewLogin() throws NamingException {
        return Lookups.loginEJBRemoteLookup();
    }

}
