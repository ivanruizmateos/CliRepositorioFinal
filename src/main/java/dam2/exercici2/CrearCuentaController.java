package dam2.exercici2;




import com.mycompany.common.ILogin;
import com.mycompany.common.Lookups;
import static dam2.exercici2.LoginController.loginI;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.naming.NamingException;

/**
 * Controlador para la vista de creación de cuenta.
 */
public class CrearCuentaController implements Initializable {

    private static ILogin loginI;
    @FXML
    private Button btn_cancelar;

    @FXML
    private Button btn_crear;

    @FXML
    private TextField nickT;

    @FXML
    private TextField emailT;

    /**
     * Cambia a la vista de inicio de sesión.
     *
     * @param event el evento de acción
     * @throws IOException si ocurre un error al cargar la vista
     */
    @FXML
    void switchToLogin(ActionEvent event) throws IOException {
        String email = emailT.getText();
        String nick = nickT.getText();
        if(!email.equals("") && !nick.equals("")){
            loginI.register(email, nick);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Error, No puede haber campos vacíos");
            alert.showAndWait();
            nickT.clear();
            emailT.clear();
        }
        
        App.setRoot("login");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loginI = Lookups.loginEJBRemoteLookup();
        } catch (NamingException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
