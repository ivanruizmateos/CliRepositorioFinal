/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dam2.exercici2;

import com.mycompany.common.ILogin;
import com.mycompany.common.Jugadores;
import com.mycompany.common.Lookups;
import static dam2.exercici2.LoginController.loginI;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumnBase;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TableHeaderRow;
import javax.naming.NamingException;

/**
 * Controlador para la vista de puntuaciones del juego.
 * Muestra una tabla con el nombre de los jugadores y sus puntuaciones.
 * Los datos se obtienen a través de un EJB remoto.
 * @author ivanr
 */
public class HallOfFameController implements Initializable {

    ObservableList<List> list;

    @FXML
    private TableView HallOfFame;

    @FXML
    private TableColumn<Jugadores, String> jugador;

    @FXML
    private TableColumn<Jugadores, Integer> puntuacion;

    @FXML
    private Button btn_cancelar;

    /**
     * Cambia a la vista de selección de dificultad.
     *
     * @param event el evento de acción
     * @throws IOException si ocurre un error al cargar la vista
     */
    @FXML
    void switchToDificultad(ActionEvent event) throws IOException {
        App.setRoot("seleccionDificultad");
    }

    /**
     * Inicializa el controlador.
     *
     * @param url la ubicación del archivo FXML
     * @param rb los recursos específicos de localización
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            try {
                
                loginI = Lookups.loginEJBRemoteLookup();
            } catch (NamingException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            list = FXCollections.observableArrayList(loginI.getHallOfFame());
            jugador.setCellValueFactory(new PropertyValueFactory<>("username"));
            puntuacion.setCellValueFactory(new PropertyValueFactory<>("puntosGanados"));
            HallOfFame.setItems(list);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    

}
