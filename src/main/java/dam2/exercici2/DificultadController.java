/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dam2.exercici2;

/**
 * Controlador para la vista de selección de dificultad.
 * @author ivanr
 */
import com.mycompany.common.ILogin;
import com.mycompany.common.IPartidaLogica;
import com.mycompany.common.Jugadores;
import com.mycompany.common.Lookups;
import com.mycompany.common.Partida;
import com.mycompany.main.LoginEJB;
import static dam2.exercici2.LoginController.loginI;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javax.naming.NamingException;

public class DificultadController implements Initializable {

    static IPartidaLogica partida;
    Jugadores jugador;
    static ILogin loginI;

    @FXML
    private Button btn_help;

    @FXML
    private Button btn_cancelar;

    @FXML
    private Button btn_fame;

    @FXML
    private Button btn_facil;

    @FXML
    private Button btn_normal;

    @FXML
    private Button btn_dificil;

    /**
     * Cambia a la vista del juego según la dificultad seleccionada.
     *
     * @param event el evento de acción
     * @throws IOException si ocurre un error al cargar la vista
     */
    @FXML
    void switchToJuego(ActionEvent event) throws IOException {
        Button clickedButton = (Button) event.getSource();
        String buttonText = clickedButton.getText();
        if (clickedButton.getId().equals("btn_facil") || clickedButton.getId().equals("btn_normal") || clickedButton.getId().equals("btn_dificil")) {

            partida.createMatch(loginI.getLoggedUser(), buttonText, 6, 0);
            App.setRoot("juego");
        }
    }

    /**
     * Cambia a la vista de inicio de sesión.
     *
     * @param event el evento de acción
     * @throws IOException si ocurre un error al cargar la vista
     */
    @FXML
    void switchToLogin(ActionEvent event) throws IOException {
        App.setRoot("login");
    }

    /**
     * Cambia a la vista de puntuaciones.
     *
     * @param event el evento de acción
     * @throws IOException si ocurre un error al cargar la vista
     */
    @FXML
    void switchToFame(ActionEvent event) throws IOException {
        App.setRoot("hallOfFame");
    }
    
    /**
     * Cambia a la vista de ayuda.
     *
     * @param event el evento de acción
     * @throws IOException si ocurre un error al cargar la vista
     */
    @FXML
    void switchToHelp(ActionEvent event) throws IOException {
        App.setRoot("help");
    }

    /**
     * Inicializa el controlador.
     *
     * @param url la ubicación del archivo FXML
     * @param resourceBundle los recursos específicos de localización
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loginI = Lookups.loginEJBRemoteLookup();

            partida = Lookups.partidaEJBRemoteLookup();
        } catch (NamingException ex) {
            Logger.getLogger(DificultadController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
