package CONTROLADORES;

import App.PRINCIPAL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CONTROLADOR_3 {

    @FXML
    private Button AGREGARBTN;

    @FXML
    private TextField COLUMNACTXT;

    @FXML
    private Button LIMPIARDATOSBTN;

    @FXML
    private Button LIMPIARRESULTADOBTN;

    @FXML
    private TextArea MATRIZTXT;

    @FXML
    private Button REGRESAR_3;

    @FXML
    private TextField RENGLONRTXT;

    @FXML
    private TextArea RESULTADOTXT;

    @FXML
    private Button SIGUIENTE_3;

    @FXML
    void AGREGAR(ActionEvent event) {
        try {
            int R = Integer.parseInt(RENGLONRTXT.getText());
            int C = Integer.parseInt(COLUMNACTXT.getText());

            String[] rows = MATRIZTXT.getText().split("\n");
            double[][] matriz = new double[rows.length][];

            for (int i = 0; i < rows.length; i++) {
                String[] elements = rows[i].split(",");
                matriz[i] = new double[elements.length];
                for (int j = 0; j < elements.length; j++) {
                    matriz[i][j] = Double.parseDouble(elements[j].trim());
                }
            }

            if (R >= matriz.length || C >= matriz[0].length || matriz[R][C] == 0) {
                RESULTADOTXT.setText("-1");
                return;
            }

            double divisor = matriz[R][C];
            for (int j = 0; j < matriz[R].length; j++) {
                matriz[R][j] /= divisor;
            }

            for (int i = 0; i < matriz.length; i++) {
                if (i != R) {
                    double factor = matriz[i][C];
                    for (int j = 0; j < matriz[i].length; j++) {
                        matriz[i][j] -= factor * matriz[R][j];
                    }
                }
            }

            displayResult(matriz);
        } catch (NumberFormatException e) {
            RESULTADOTXT.setText("entrada no válida");
        } catch (ArrayIndexOutOfBoundsException e) {
            RESULTADOTXT.setText("índices fuera de rango");
        }
    }

    private void displayResult(double[][] matriz) {
        StringBuilder resultText = new StringBuilder();
        for (double[] row : matriz) {
            resultText.append("{ ");
            for (double value : row) {
                resultText.append(String.format("%.1f ", value));
            }
            resultText.append("}\n");
        }
        RESULTADOTXT.setText(resultText.toString());
    }

    @FXML
    void LIMPIARDATOS(ActionEvent event) {
        COLUMNACTXT.setText("");
        RENGLONRTXT.setText("");
        MATRIZTXT.clear();
    }

    @FXML
    void LIMPIARRESULTADO(ActionEvent event) {
        RESULTADOTXT.clear();
    }

    @FXML
    void REGRESAR(ActionEvent event) {
        try {
            PRINCIPAL.showView("VISTA_2.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void SIGUIENTE(ActionEvent event) {
        try {
            PRINCIPAL.showView("VISTA_4.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
