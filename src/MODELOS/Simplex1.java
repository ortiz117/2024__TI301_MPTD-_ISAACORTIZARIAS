package MODELOS;

public class Simplex1 {

    private double pl[][];
    private int R;
    private int C;

    public Simplex1(double[][] pl, int R, int C) {
        this.pl = pl;
        this.R = R;  // Cambiado de C a R
        this.C = C;
    }

    public String resolver() {
        // Verificar que R y C estén dentro de los límites de la matriz
        if (R < 0 || R >= pl.length || C < 0 || C >= pl[0].length) {
            return "Error: índices fuera de los límites.";
        }

        double pivote = pl[R][C];
        for (int j = 0; j < pl[0].length; j++) {
            pl[R][j] /= pivote;
        }
        for (int i = 0; i < pl.length; i++) {
            if (i != R) {
                double factor = pl[i][C];
                for (int j = 0; j < pl[0].length; j++) {
                    pl[i][j] -= factor * pl[R][j];
                }
            }
        }

        // Devolver la matriz como String
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < pl.length; i++) {
            for (int j = 0; j < pl[i].length; j++) {
                resultado.append(String.format("%06.3f ", pl[i][j]));
            }
            resultado.append("\n");
        }
        return resultado.toString();
    }

    public void print() {
        for (int i = 0; i < pl.length; i++) {
            for (int j = 0; j < pl[i].length; j++) {
                System.out.printf("%06.3f ", pl[i][j]);
            }
            System.out.println();
        }
    }
}