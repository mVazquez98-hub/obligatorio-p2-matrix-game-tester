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
        String[] filasPorDefecto = {
            "VVNNVVNNVV",
            "NNNNNNNNNN",
            "NNNVVNNVNN",
            "VVVVVVVVVV",
            "VVVVVVVVVV",
            "BBBVVBBVBB",
            "BBBBBBBBBB",
            "VVBBVVBBVV"
        };
        for (int i = 0; i < filasPorDefecto.length; i++) {
            for (int j = 0; j < filasPorDefecto[0].length(); j++) {
                String valor = "" + filasPorDefecto[i].charAt(j);
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
        boolean cargada = false;
        if (matrizValida(filasIngresadas)) {
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    String valor = "" + filasIngresadas[i].charAt(j);
                    matriz[i][j] = valor.toUpperCase();

                }
            }

        }
        return cargada;
    }

    private boolean matrizValida(String[] filasIngresadas) {
        boolean valida = true;
        if (filasIngresadas.length != filas) {
            valida = false;
        }

        for (int i = 0; i < filasIngresadas.length && valida; i++) {
            if (filasIngresadas[i].length() != columnas) {
                valida = false;
            } else {
                for (int j = 0; j < columnas && valida; j++) {
                    String valor = "" + filasIngresadas[i].charAt(j);
                    String valorMayus = valor.toUpperCase();
                    if (!valorMayus.equalsIgnoreCase("B") && !valorMayus.equalsIgnoreCase("N") && !valorMayus.equalsIgnoreCase("V")) {
                        valida = false;
                    }
                }
            }

        }
        return valida;

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
