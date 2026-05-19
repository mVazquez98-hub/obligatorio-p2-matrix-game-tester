/*
*Nombre: Martín Vázquez - Número de estudiante: 180210
*/
package obligatorio;

public class Tablero {

    private static final int filas = 8;
    private static final int columnas = 10;
    private String[][] matriz;

    public Tablero() {
        this.matriz = new String[filas][columnas];
        cargarMatrizPorDefecto();
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
        boolean valido = true;
        if (!color.equalsIgnoreCase("B") && !color.equalsIgnoreCase("N")) {
            valido = false;
        } else if (!formaValida(forma)) {
            valido = false;
        } else if (!sentidoLinealValido(sentido)) {
            valido = false;
        } else if (!sentidoPorColorValido(color, sentido)) {
            valido = false;
        } else if (!sentidoValidoConForma(forma, sentido)) {
            valido = false;
        } else if (pasos <= 0) {
            valido = false;
        } else if (tamanio <= 0) {
            valido = false;
        } else if (!grupoValido(color, forma, fila, columna, tamanio)) {
            valido = false;
        } else if (!caminoGrupoLibre(forma, fila, columna, tamanio, sentido, pasos)) {
            valido = false;

        } else if (!destinoGrupoValido(forma, fila, columna, tamanio, sentido, pasos)) {
            valido = false;
        }
        if (valido) {
            moverGrupo(color, forma, fila, columna, tamanio, sentido, pasos);
        }

        return valido;
    }

    private boolean formaValida(String forma) {
        boolean valido = false;
        if (forma.equalsIgnoreCase("H") || forma.equalsIgnoreCase("V")) {
            valido = true;
        }
        return valido;

    }

    private boolean sentidoLinealValido(String sentido) {
        boolean valido = false;
        if (sentido.equalsIgnoreCase("N")
                || sentido.equalsIgnoreCase("S")
                || sentido.equalsIgnoreCase("E")
                || sentido.equalsIgnoreCase("O")) {
            valido = true;
        }
        return valido;

    }

    private boolean sentidoPorColorValido(String color, String sentido) {
        boolean permitido = false;
        if (color.equalsIgnoreCase("B")) {
            if (sentido.equalsIgnoreCase("N")
                    || sentido.equalsIgnoreCase("E")
                    || sentido.equalsIgnoreCase("O")) {
                permitido = true;

            }
        } else if (color.equalsIgnoreCase("N")) {
            if (sentido.equalsIgnoreCase("S")
                    || sentido.equalsIgnoreCase("E")
                    || sentido.equalsIgnoreCase("O")) {
                permitido = true;
            }
        }
        return permitido;
    }

    private boolean sentidoValidoConForma(String forma, String sentido) {
        boolean permitido = false;
        if (forma.equalsIgnoreCase("H")) {
            if (sentido.equalsIgnoreCase("N")
                    || sentido.equalsIgnoreCase("S")) {
                permitido = true;

            }
        } else if (forma.equalsIgnoreCase("V")) {
            if (sentido.equalsIgnoreCase("E")
                    || sentido.equalsIgnoreCase("O")) {
                permitido = true;

            }
        }
        return permitido;
    }

    private boolean grupoValido(String color, String forma, int fila, int columna, int tamanio) {
        boolean valido = true;
        for (int i = 0; i < tamanio && valido; i++) {
            int filaActual = 0;
            int columnaActual = 0;
            if (forma.equalsIgnoreCase("H")) {
                filaActual = fila;
                columnaActual = columna + i;
            } else if (forma.equalsIgnoreCase("V")) {
                filaActual = fila + i;
                columnaActual = columna;
            }
            if (!posicionValida(filaActual, columnaActual)) {
                valido = false;
            } else if (!matriz[filaActual][columnaActual].equalsIgnoreCase(color)) {
                valido = false;
            }
        }
        return valido;
    }

    private boolean caminoGrupoLibre(String forma, int fila, int columna, int tamanio, String sentido, int pasos) {
        boolean libre = true;
        for (int i = 0; i < tamanio && libre; i++) {
            int filaActual = 0;
            int columnaActual = 0;
            if (forma.equalsIgnoreCase("H")) {
                filaActual = fila;
                columnaActual = columna + i;
            } else if (forma.equalsIgnoreCase("V")) {
                filaActual = fila + i;
                columnaActual = columna;
            }
            for (int j = 1; j < pasos && libre; j++) {
                int filaIntermedia = filaDestino(filaActual, sentido, j);
                int columnaIntermedia = columnaDestino(columnaActual,sentido,j);
                if(!posicionValida(filaIntermedia, columnaIntermedia)){
                    libre = false;
                }else if(!matriz[filaIntermedia][columnaIntermedia].equalsIgnoreCase("V")){
                    libre = false;
                    
                }
            }
        }return libre;
    }

    private boolean destinoGrupoValido(String forma, int fila, int columna, int tamanio, String sentido, int pasos) {
        boolean valido = true;
        for (int i = 0; i < tamanio && valido; i++) {
            int filaActual = 0;
            int columnaActual = 0;
            if (forma.equalsIgnoreCase("H")) {
                filaActual = fila;
                columnaActual = columna + i;
            } else if (forma.equalsIgnoreCase("V")) {
                filaActual = fila + i;
                columnaActual = columna;
            }
            int filaFinal = filaDestino(filaActual, sentido, pasos);
            int columnaFinal = columnaDestino(columnaActual, sentido, pasos);
            if (!posicionValida(filaFinal, columnaFinal)) {
                valido = false;
            } else if (!matriz[filaFinal][columnaFinal].equalsIgnoreCase("V")) {
                valido = false;
            }
        }
        return valido;
    }

    private void moverGrupo(String color, String forma, int fila, int columna, int tamanio, String sentido, int pasos) {
        for (int i = 0; i < tamanio; i++) {
            int filaActual = 0;
            int columnaActual = 0;
            if (forma.equalsIgnoreCase("H")) {
                filaActual = fila;
                columnaActual = columna + i;
            } else if (forma.equalsIgnoreCase("V")) {
                filaActual = fila + i;
                columnaActual = columna;
            }
            matriz[filaActual][columnaActual] = "V";

        }
        for (int i = 0; i < tamanio; i++) {
            int filaActual = 0;
            int columnaActual = 0;
            if (forma.equalsIgnoreCase("H")) {
                filaActual = fila;
                columnaActual = columna + i;
            } else if (forma.equalsIgnoreCase("V")) {
                filaActual = fila + i;
                columnaActual = columna;
            }
            int filaFinal = filaDestino(filaActual, sentido, pasos);
            int columnaFinal = columnaDestino(columnaActual, sentido, pasos);
            matriz[filaFinal][columnaFinal] = color.toUpperCase();
        }

    }

    public boolean verificarConexion(String color) {
        boolean conectado = false;
        int filaInicio = -1;
        int columnaInicio = -1;
        int totalFichas = contarFichas(color);

        if (totalFichas == 0) {
            return false;
        }
        for (int i = 0; i < matriz.length && filaInicio == -1; i++) {
            for (int j = 0; j < matriz[0].length && filaInicio == -1; j++) {
                if (matriz[i][j].equalsIgnoreCase(color)) {
                    filaInicio = i;
                    columnaInicio = j;
                }

            }
        }
        boolean[][] visitadas = new boolean[filas][columnas];
        int conectadas = contarConectadas(color, filaInicio, columnaInicio, visitadas);

        if (conectadas == totalFichas) {
            conectado = true;
        }
        return conectado;
    }
    
           
    private int contarConectadas(String color, int filaInicio, int columnaInicio, boolean[][] visitadas) {
        int contador = 0;

    int[] filasPendientes = new int[filas * columnas];
    int[] columnasPendientes = new int[filas * columnas];

    int posicionARevisar = 0;
    int proximaPosicionLibre = 0;

    filasPendientes[proximaPosicionLibre] = filaInicio;
    columnasPendientes[proximaPosicionLibre] = columnaInicio;
    proximaPosicionLibre++;

    visitadas[filaInicio][columnaInicio] = true;

    while (posicionARevisar < proximaPosicionLibre) {
        int filaActual = filasPendientes[posicionARevisar];
        int columnaActual = columnasPendientes[posicionARevisar];
        posicionARevisar++;
        contador++;

        for (int i = filaActual - 1; i <= filaActual + 1; i++) {
            for (int j = columnaActual - 1; j <= columnaActual + 1; j++) {
                if (!(i == filaActual && j == columnaActual)) {
                    if (posicionValida(i, j)
                            && !visitadas[i][j]
                            && matriz[i][j].equalsIgnoreCase(color)) {

                        visitadas[i][j] = true;

                        filasPendientes[proximaPosicionLibre] = i;
                        columnasPendientes[proximaPosicionLibre] = j;
                        proximaPosicionLibre++;
                    }
                }
            }
        }
    }

    return contador;
}

}
