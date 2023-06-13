package dam2.exercici2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static final int WINDOW_WIDTH = 1070;
    private static final int WINDOW_HEIGHT = 700;

    /**
     * Método de inicio de la aplicación.
     *
     * @param stage la ventana principal de la aplicación
     * @throws IOException si ocurre un error al cargar el archivo FXML
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Establece la raíz de la escena actual.
     *
     * @param fxml el nombre del archivo FXML
     * @throws IOException si ocurre un error al cargar el archivo FXML
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Carga el archivo FXML y devuelve el nodo raíz.
     *
     * @param fxml el nombre del archivo FXML
     * @return el nodo raíz cargado desde el archivo FXML
     * @throws IOException si ocurre un error al cargar el archivo FXML
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args los argumentos de línea de comandos
     */
    public static void main(String[] args) {
        launch();
    }

}
