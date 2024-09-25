package CONTROLADORES;

import App.PRINCIPAL;
import java.util.Arrays;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import javafx.scene.control.Label;

public class CONTROLADOR_1 {

    @FXML
    private Button AGREGARbtn;

    @FXML
    private Button LIMPIARBTN;

    @FXML
    private Label LABELMIN;

    @FXML
    private TextArea NUMEROSTXT;

    @FXML
    private TextField NUMEROStxt;

    @FXML
    private Button SIGUIENTE_1;

    @FXML
    void AGREGAR(ActionEvent event) {
        ArrayList<Integer> NUEVO = new ArrayList<>();
        String numeros = NUMEROStxt.getText();
        String NUMEROS1[] = numeros.split(",");
        boolean tienenegativos = false;

        for (String parte : NUMEROS1) {
            int numero = Integer.parseInt(parte.trim());
            NUEVO.add(numero);
            if (numero < 0) {
                tienenegativos = true;
            }
        }

        if (!tienenegativos) {
            LABELMIN.setText("-1");
        } else {
            int MINIMO = NUEVO.stream().mapToInt(Integer::intValue).min().orElse(0);
            LABELMIN.setText(String.valueOf(MINIMO));
        }

        NUMEROSTXT.setText(numeros);
    }

    @FXML
    void SIGUIENTE_1(ActionEvent event) {
        try {
            PRINCIPAL.showView("VISTA_2.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void LIMPIAR(ActionEvent event) {
        NUMEROStxt.setText("");
    }

}
