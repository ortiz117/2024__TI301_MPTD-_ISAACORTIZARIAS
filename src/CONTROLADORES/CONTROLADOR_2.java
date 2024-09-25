package CONTROLADORES;

import App.PRINCIPAL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class CONTROLADOR_2 {

    @FXML
    private Button AGREGARBTN;

    @FXML
    private Button LIMIPIARTXTAREA;

    @FXML
    private TextField COL1TXT;

    @FXML
    private TextField COL2TXT;

    @FXML
    private Label LABELNOPOSITIVOS;

    @FXML
    private Button LIMPIARBTN;

    @FXML
    private TextArea NUMEROSTXTA;

    @FXML
    private Button REGRESARBTN;

    @FXML
    private Button SIGUIENTE_2BTN;

    @FXML
    void AGREGAR(ActionEvent event) {
        String col1Input = COL1TXT.getText();
        String col2Input = COL2TXT.getText();

        String[] col1Lines = col1Input.split(",");
        String[] col2Lines = col2Input.split(",");

        List<Double> resultados = new ArrayList<>();

        for (int i = 0; i < col1Lines.length && i < col2Lines.length; i++) {
            try {
                double num1 = Double.parseDouble(col1Lines[i].trim());
                double num2 = Double.parseDouble(col2Lines[i].trim());

                if (num1 != 0) {
                    double division = num2 / num1;
                    resultados.add(division);
                } else {
                    resultados.add(Double.NaN);
                }
            } catch (NumberFormatException e) {
                NUMEROSTXTA.setText("Entrada no válida");
                return;
            }
        }

        double minPositivo = Double.MAX_VALUE;
        int indiceMinPositivo = -1;

        for (int i = 1; i < resultados.size(); i++) {
            double valor = resultados.get(i);
            if (valor > 0 && valor < minPositivo) {
                minPositivo = valor;
                indiceMinPositivo = i;
            }
        }

        if (indiceMinPositivo == -1) {
            LABELNOPOSITIVOS.setText("-1");
        } else {
            LABELNOPOSITIVOS.setText(String.valueOf(indiceMinPositivo));
        }

        StringBuilder resultadosText = new StringBuilder("Resultados:\n");
        for (int i = 0; i < resultados.size(); i++) {
            resultadosText.append("Resultado ").append(i).append(": ").append(resultados.get(i)).append("\n");
        }
        NUMEROSTXTA.setText(resultadosText.toString());
    }

    @FXML
    void LIMPIAR(ActionEvent event) {
        COL1TXT.clear();
        COL2TXT.clear();
        LABELNOPOSITIVOS.setText("");
    }

    @FXML
    void LIMPIARTXTXAREA(ActionEvent event) {
        NUMEROSTXTA.clear();
    }

    @FXML
    void REGRESAR_1BTN(ActionEvent event) {
        try {
            PRINCIPAL.showView("VISTA_1.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void SIGUIENTE_2(ActionEvent event) {
        try {
            PRINCIPAL.showView("VISTA_3.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
