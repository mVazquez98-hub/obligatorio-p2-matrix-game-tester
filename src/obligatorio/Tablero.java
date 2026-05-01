package obligatorio;

public class Tablero {

    private static final int filas = 8;
    private static final int columnas = 10;
    private String[][] matriz;

    public Tablero() {
        this.matriz = new String[filas][columnas];
        cargarMatrizPorDefecto();
    }

    public String[][] getMatriz() {
        return matriz;
    }

    public void cargarMatrizPorDefecto() {
        String[] filas = {
            "VVNNVVNNVV",
            "NNNNNNNNNN",
            "NNNVVNNVNN",
            "VVVVVVVVVV",
            "VVVVVVVVVV",
            "BBBVVBBVBB",
            "BBBBBBBBBB",
            "VVBBVVBBVV"
        };
        for (int i = 0; i < filas.length; i++) {
            for (int j = 0; j < filas[0].length(); j++) {
                String valor = "" + filas[i].charAt(j);
                matriz[i][j] = valor;

            }
        }

    }

    public String prepararTablero() {
        String tablero = "";
        for (int i = 0; i < filas; i++) {
            tablero += "+---+---+---+---+---+---+---+---+---+---+\n";
            for (int j = 0; j < columnas; j++) {
                tablero += "| " + matriz[i][j] + " ";
            }
            tablero += "|\n";
        }
        tablero += "+---+---+---+---+---+---+---+---+---+---+\n";

        return tablero;

    }

    public boolean cargarMatriz(String[] filasIngresadas) {
        return false;
    }

    public String[][] copiarMatriz() {
        return null;
    }

    public int contarFichas(String color) {
        return 0;
    }

    public boolean validarMovimientoIndividual(String color, String sentido, int fila, int columna, int pasos) {
        return false;
    }

    public boolean validarMovimientoEnGrupo(String color, String forma, String sentido, int fila, int columna, int tamanio, int pasos) {
        return false;
    }

    public boolean verificarConexion(String color) {
        return false;
    }

}
