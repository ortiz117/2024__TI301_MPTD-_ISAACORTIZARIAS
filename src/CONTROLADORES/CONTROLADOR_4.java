package CONTROLADORES;

import App.PRINCIPAL;
import MODELOS.Simplex;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;

public class CONTROLADOR_4 {

    @FXML
    private Button AGREGARBTN;

    @FXML
    private Button LIMPIAROBTN;

    @FXML
    private Button LIMPIARRBTN;

    @FXML
    private TextArea MATRIZORIGTXT;

    @FXML
    private TextArea MATRIZRESUTXT;

    @FXML
    private Button REGRESAR_4BTN;

    @FXML
    private Button SIGUIENTE_4BTN;

    @FXML
    void AGREGAR(ActionEvent event) {
        try {
            String matrizInput = MATRIZORIGTXT.getText();
            Simplex simplex = new Simplex();
            String resultado = simplex.resolver(matrizInput);
            MATRIZRESUTXT.setText(resultado);
        } catch (Exception e) {
            mostrarError("formato incorrecto");
        }
    }

    @FXML
    void LIMPIARO(ActionEvent event) {
        MATRIZORIGTXT.clear();
    }

    @FXML
    void LIMPIARR(ActionEvent event) {
        MATRIZRESUTXT.clear();
    }

    @FXML
    void REGRESAR(ActionEvent event) {
        try {
            PRINCIPAL.showView("VISTA_3.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void SIGUIENTE(ActionEvent event) {
        try {
            PRINCIPAL.showView("VISTA_5.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
