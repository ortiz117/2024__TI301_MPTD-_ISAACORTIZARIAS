package MODELOS;

import java.util.Arrays;

public class Simplex {

    private double[][] tabla;

    public Simplex() {
    }

    public Simplex(double[][] tabla) {
        this.tabla = tabla;
    }

    public double[][] getTabla() {
        return tabla;
    }

    public void setTabla(double[][] tabla) {
        this.tabla = tabla;
    }

    public String resolver(String matrizInput) {
        parsearMatriz(matrizInput);

        while (!esOptimo()) {
            int colPivote = seleccionarColumnaPivote();
            int filaPivote = seleccionarFilaPivote(colPivote);
            actualizarTabla(filaPivote, colPivote);
        }

        return generarSalida();
    }

    private void parsearMatriz(String matrizInput) {
        String[] filas = matrizInput.split("\n");
        int numFilas = filas.length;
        int numColumnas = filas[0].trim().split("\\s+").length;
        tabla = new double[numFilas][numColumnas];

        for (int i = 0; i < numFilas; i++) {
            String[] valores = filas[i].trim().split("\\s+");
            for (int j = 0; j < numColumnas; j++) {
                tabla[i][j] = Double.parseDouble(valores[j]);
            }
        }
    }

    private boolean esOptimo() {
        for (int j = 1; j < tabla[0].length - 1; j++) {
            if (tabla[0][j] < 0) {
                return false;
            }
        }
        return true;
    }

    private int seleccionarColumnaPivote() {
        int colPivote = 1; 
        for (int j = 1; j < tabla[0].length - 1; j++) {
            if (tabla[0][j] < tabla[0][colPivote]) {
                colPivote = j;
            }
        }
        return colPivote;
    }

    private int seleccionarFilaPivote(int colPivote) {
        int filaPivote = -1;
        double minRatio = Double.POSITIVE_INFINITY;

        for (int i = 1; i < tabla.length; i++) {
            if (tabla[i][colPivote] > 0) {
                double ratio = tabla[i][tabla[0].length - 1] / tabla[i][colPivote];
                if (ratio < minRatio) {
                    minRatio = ratio;
                    filaPivote = i;
                }
            }
        }
        return filaPivote;
    }

    private void actualizarTabla(int filaPivote, int colPivote) {
        double pivot = tabla[filaPivote][colPivote];

        
        for (int j = 0; j < tabla[0].length; j++) {
            tabla[filaPivote][j] /= pivot;
        }

       
        for (int i = 0; i < tabla.length; i++) {
            if (i != filaPivote) {
                double factor = tabla[i][colPivote];
                for (int j = 0; j < tabla[0].length; j++) {
                    tabla[i][j] -= factor * tabla[filaPivote][j];
                }
            }
        }
    }

    private String generarSalida() {
        StringBuilder sb = new StringBuilder();
        for (double[] fila : tabla) {
            sb.append(Arrays.toString(fila)).append("\n");
        }
        return sb.toString();
    }

    public double calcularValorMaximo() {
        double valorMaximo = 0;
        for (int j = 1; j < tabla[0].length - 1; j++) {
            if (tabla[0][j] == 0) {
                valorMaximo += tabla[0][j] * tabla[tabla.length - 1][j];
            }
        }
        return valorMaximo;
    }
}