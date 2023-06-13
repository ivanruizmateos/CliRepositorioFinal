/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dam2.exercici2;

import com.mycompany.main.LoginEJB;
import static dam2.exercici2.DificultadController.loginI;
import static dam2.exercici2.DificultadController.partida;
import static dam2.exercici2.LoginController.loginI;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Controlador para la vista del juego del ahorcado.
 * Administra el juego y la interacción del usuario con los botones de letras.
 * Controla el temporizador y la imagen del ahorcado.
 * Muestra la palabra a adivinar y actualiza su estado al adivinar letras.
 * Gestiona el final de la partida por tiempo o por perder todas las vidas.
 * Proporciona una alerta de fin de partida.
 * Actualiza la imagen del ahorcado según los errores cometidos.
 * Permite volver al menú de selección de dificultad.
 * @author ivanr
 */
public class JuegoController implements Initializable {

    private int timeSeconds = 300;
    @FXML
    private Button btn_v;

    @FXML
    private Button btn_u;

    @FXML
    private Button btn_t;

    @FXML
    private Button btn_s;

    @FXML
    private Button btn_z;

    @FXML
    private Button btn_y;

    @FXML
    private Button btn_x;

    @FXML
    private Button btn_w;

    

    @FXML
    private Button btn_n;

    @FXML
    private Button btn_m;

    @FXML
    private Button btn_l;

    @FXML
    private Button btn_k;

    @FXML
    private Button btn_r;

    @FXML
    private Button btn_q;

    @FXML
    private Button btn_ñ;

    @FXML
    private Button btn_p;

    @FXML
    private Button btn_o;

    @FXML
    private Button btn_f;

    @FXML
    private Button btn_e;

    @FXML
    private Button btn_d;

    @FXML
    private Button btn_c;

    @FXML
    private Button btn_i;

    @FXML
    private Button btn_h;

    @FXML
    private Button btn_g;

    @FXML
    private Button btnj;

    @FXML
    private Button btn_b;

    @FXML
    private Button btn_a;

    @FXML
    private Text timer;

    @FXML
    private Text palabra;

    @FXML
    private ImageView imagen;

    private int errores = 0;

    

    /**
     * Gestiona la pulsación de los botones de letras.
     *
     * @param event el evento de acción
     * @throws IOException si ocurre un error al cambiar de vista
     */
    @FXML
    private void handleButtonClick(ActionEvent event) throws IOException {

        Button clickedButton = (Button) event.getSource();
        String buttonText = clickedButton.getText().toLowerCase();

        if (!clickedButton.getId().equals("btn_cancelar")) {

            String resultado = partida.adivinarLetra(buttonText.toLowerCase().charAt(0));

            if (resultado.equals("incorrecto")) {
                actualizarImagenAhorcado();
                
                if (errores <= 6) {
                    errores++;
                }
            } else if (resultado.equals("sin vidas")) {
                actualizarImagenAhorcado();
                errores++;
                
                alerta();

            } else {
                palabra.setText(resultado);

            }

        }
    }

    /**
     * Actualiza el temporizador del juego.
     */
    private void updateTimer() {
        timeSeconds--;

        if (timeSeconds == 0) {
            // Fin de la partida, volver a la selección de dificultad
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Fin de la partida");
                alert.setHeaderText(null);
                alert.setContentText("Se ha agotado el tiempo, volverás al menú de selección");
                alert.showAndWait();
                partida.endMatchForTime();
                try {
                    App.setRoot("seleccionDificultad");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            // Detener el contador en "00:00"
            timeSeconds = 0;
        }

        // Formatea el tiempo en minutos y segundos
        String timeString = String.format("%02d:%02d", timeSeconds / 60, timeSeconds % 60);
        timer.setText(timeString);
    }

    @FXML
    void setLetter(ActionEvent event) throws IOException {

    }

    /**
     * Inicializa el controlador.
     *
     * @param url la ubicación del archivo FXML
     * @param rb los recursos específicos de localización
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        palabra.setText(partida.getWords().toString());
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), evt -> {
            if(timeSeconds!=0){
                updateTimer();
            }else{
                timer.setText("00:00");
            }
            
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Actualiza la imagen del ahorcado según los errores cometidos.
     */
    private void actualizarImagenAhorcado() {
        ArrayList<Image> imagenesAhorcado = new ArrayList<>();
        imagenesAhorcado.add(new Image("/img/imagen_1.png"));
        imagenesAhorcado.add(new Image("/img/imagen_2.png"));
        imagenesAhorcado.add(new Image("/img/imagen_3.png"));
        imagenesAhorcado.add(new Image("/img/imagen_4.png"));
        imagenesAhorcado.add(new Image("/img/imagen_5.png"));
        imagenesAhorcado.add(new Image("/img/imagen_6.png"));

        imagen.setImage(imagenesAhorcado.get(errores));

    }

    /**
     * Muestra una alerta de fin de partida y vuelve al menú de selección de dificultad.
     *
     * @throws IOException si ocurre un error al cambiar de vista
     */
    public void alerta() throws IOException {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fin de la partida");
        alert.setHeaderText(null);
        alert.setContentText("Has perdido, volverás al menú de selección");
        alert.showAndWait();
        App.setRoot("seleccionDificultad");
    }

}
