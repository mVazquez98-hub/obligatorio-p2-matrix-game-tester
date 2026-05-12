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
            cargada = true;

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
        String[][] copiaMatriz = new String[filas][columnas];
        for (int i = 0; i < copiaMatriz.length; i++) {
            for (int j = 0; j < copiaMatriz[0].length; j++) {
                copiaMatriz[i][j] = matriz[i][j];
            }
        }
        return copiaMatriz;
    }

    public int contarFichas(String color) {
        int contador = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j].equalsIgnoreCase(color)) {
                    contador++;
                }
            }
        }
        return contador;
    }

    public boolean validarMovimientoIndividual(String color, String sentido, int fila, int columna, int pasos) {
        boolean valido = true;
        int filaDestino = filaDestino(fila, sentido, pasos);
        int colDestino = columnaDestino(columna, sentido, pasos);
        if (!color.equalsIgnoreCase("B") && !color.equalsIgnoreCase("N")) {
            valido = false;
        } else if (!sentidoPermitido(color, sentido)) {
            valido = false;

        } else if (pasos <= 0) {
            valido = false;

        } else if (!posicionValida(fila, columna)) {
            valido = false;
        } else if (!matriz[fila][columna].equalsIgnoreCase(color)) {
            valido = false;
        } else if (!posicionValida(filaDestino, colDestino)) {
            valido = false;
        } else if (!caminoLibre(fila, columna, sentido, pasos)) {
            valido = false;
        } else if (!destinoValido(filaDestino, colDestino, color)) {
            valido = false;
        }
        if (valido) {
            moverFicha(fila, columna, filaDestino, colDestino, color);
        }
        return valido;

    }

    private boolean posicionValida(int fila, int columna) {
        boolean valida = true;
        if (fila < 0 || fila >= filas || columna >= columnas || columna < 0) {
            valida = false;
        }

        return valida;
    }

    private boolean sentidoPermitido(String color, String sentido) {
        boolean permitido = false;
        if (color.equalsIgnoreCase("B")) {
            if (sentido.equalsIgnoreCase("N")
                    || sentido.equalsIgnoreCase("NE")
                    || sentido.equalsIgnoreCase("NO")
                    || sentido.equalsIgnoreCase("E")
                    || sentido.equalsIgnoreCase("O")) {
                permitido = true;
            }
        } else if (color.equalsIgnoreCase("N")) {
            if (sentido.equalsIgnoreCase("S")
                    || sentido.equalsIgnoreCase("SE")
                    || sentido.equalsIgnoreCase("SO")
                    || sentido.equalsIgnoreCase("E")
                    || sentido.equalsIgnoreCase("O")) {
                permitido = true;

            }
        }
        return permitido;

    }

    private int cambioFila(String sentido) {
        int cambio = 0;
        if (sentido.equalsIgnoreCase("N")
                || sentido.equalsIgnoreCase("NE")
                || sentido.equalsIgnoreCase("NO")) {
            cambio = -1;
        } else if (sentido.equalsIgnoreCase("S")
                || sentido.equalsIgnoreCase("SE")
                || sentido.equalsIgnoreCase("SO")) {
            cambio = 1;
        }
        return cambio;
    }

    private int cambioColumna(String sentido) {
        int cambio = 0;
        if (sentido.equalsIgnoreCase("E")
                || sentido.equalsIgnoreCase("NE")
                || sentido.equalsIgnoreCase("SE")) {
            cambio = 1;
        } else if (sentido.equalsIgnoreCase("O")
                || sentido.equalsIgnoreCase("NO")
                || sentido.equalsIgnoreCase("SO")) {
            cambio = -1;

        }
        return cambio;

    }

    private int filaDestino(int fila, String sentido, int pasos) {
        int filaDestino = fila + cambioFila(sentido) * pasos;
        return filaDestino;
    }

    private int columnaDestino(int columna, String sentido, int pasos) {
        int columnaDestino = columna + cambioColumna(sentido) * pasos;
        return columnaDestino;
    }

    private boolean caminoLibre(int fila, int columna, String sentido, int pasos) {
        boolean libre = true;
        int cambioFila = cambioFila(sentido);
        int cambioColumna = cambioColumna(sentido);
        for (int i = 1; i < pasos && libre; i++) {
            int filaActual = fila + cambioFila * i;
            int columnaActual = columna + cambioColumna * i;
            if (!posicionValida(filaActual, columnaActual)) {
                libre = false;
            } else if (!matriz[filaActual][columnaActual].equalsIgnoreCase("V")) {
                libre = false;
            }
        }

        return libre;
    }

    private boolean destinoValido(int filaDestino, int colDestino, String color) {
        boolean valido = true;
        if (!posicionValida(filaDestino, colDestino)) {
            valido = false;
        } else if (matriz[filaDestino][colDestino].equalsIgnoreCase(color)) {
            valido = false;
        }
        return valido;
    }

    private void moverFicha(int fila, int columna, int filaDestino, int columnaDestino, String color) {
        matriz[fila][columna] = "V";
        matriz[filaDestino][columnaDestino] = color.toUpperCase();
    }

    public boolean validarMovimientoEnGrupo(String color, String forma, String sentido, int fila, int columna, int tamanio, int pasos) {
        return false;
    }

    public boolean verificarConexion(String color) {
        return false;
    }

}
