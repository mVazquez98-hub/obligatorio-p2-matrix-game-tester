package obligatorio;

public class Tablero {

    private static final int FILAS = 8;
    private static final int COLUMNAS = 10;
    private String[][] matriz;

    public Tablero() {
        matriz = new String[FILAS][COLUMNAS];
        cargarMatrizPorDefecto();
    }

    public String[][] getMatriz() {
        return matriz;
    }

    public void cargarMatrizPorDefecto() {

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

    public String prepararTablero() {
        return "";
    }

    public boolean verificarConexion(String color) {
        return false;
    }

}
