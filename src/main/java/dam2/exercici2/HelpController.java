/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dam2.exercici2;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 *Clase para controlar la vista de help, solo muestra info, no hace nada
 * @author ivanr
 */
public class HelpController {

    @FXML
    private Button btn_cancelar;

    @FXML
    private Text palabra;

    @FXML
    void switchToDificultad(ActionEvent event) throws IOException {
        App.setRoot("seleccionDificultad");
    }

}
