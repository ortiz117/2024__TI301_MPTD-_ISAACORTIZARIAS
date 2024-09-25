package CONTROLADORES;

import App.PRINCIPAL;
import MODELOS.Simplex1;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CONTROLADOR_5 {

    @FXML
    private Button AGREGARBTN;

    @FXML
    private Label LABELSOLUCION;

    @FXML
    private Button LIMPIAROBTN;

    @FXML
    private Button LIMPIARRBTN;

    @FXML
    private TextArea MATRIZOTXT;

    @FXML
    private TextField COLUMNATXT;

    @FXML
    private TextField RNEGLONPTXT;

    @FXML
    private TextArea MATRIZRTXT;

    @FXML
    private Button REGRESAR_5BTN;

    @FXML
    void AGREGAR(ActionEvent event) {
        try {
            String matrizInput = MATRIZOTXT.getText().trim();
            int R = Integer.parseInt(RNEGLONPTXT.getText());
            int C = Integer.parseInt(COLUMNATXT.getText());

            // Parsear la entrada de la matriz en un array bidimensional
            String[] rows = matrizInput.split("\n");
            double[][] matriz = new double[rows.length][C];

            for (int i = 0; i < rows.length; i++) {
                String[] elements = rows[i].trim().split("\\s+");
                if (elements.length != C) {
                    throw new IllegalArgumentException("La fila " + (i + 1) + " no tiene " + C + " columnas.");
                }
                for (int j = 0; j < C; j++) {
                    matriz[i][j] = Double.parseDouble(elements[j]);
                }
            }

            // Crear la instancia de Simplex1
            Simplex1 simplex = new Simplex1(matriz, R, C);
            String resultadoTabla = simplex.resolver();
            MATRIZRTXT.setText(resultadoTabla);

        } catch (NumberFormatException e) {
            LABELSOLUCION.setText("Error: formato numérico incorrecto.");
        } catch (IllegalArgumentException e) {
            LABELSOLUCION.setText("Error: " + e.getMessage());
        } catch (Exception e) {
            LABELSOLUCION.setText("Error: formato incorrecto.");
        }
    }

    @FXML
    void LIMPIARO(ActionEvent event) {
        MATRIZOTXT.clear();
        MATRIZRTXT.clear();
        LABELSOLUCION.setText("");
        RNEGLONPTXT.setText("");
        COLUMNATXT.setText("");
    }

    @FXML
    void LIMPIARR(ActionEvent event) {
        MATRIZRTXT.clear();
        LABELSOLUCION.setText("");
    }

    @FXML
    void REGRESAR(ActionEvent event) {
        try {
            PRINCIPAL.showView("VISTA_4.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}