package App;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PRINCIPAL extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        showView("VISTA_1.fxml");
    }

    public static void showView(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(PRINCIPAL.class.getResource("/VISTAS/" + fxml));
        Pane ventana = (Pane) loader.load();
        Scene scene = new Scene(ventana);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
