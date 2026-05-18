package obligatorio;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Collections;

public class Sistema {

    private Tablero tablero;
    private ArrayList<Tester> testers;
    private ArrayList<Testeo> testeos;
    private Scanner in;

    public Sistema() {
        this.tablero = new Tablero();
        this.testers = new ArrayList<Tester>();
        this.testeos = new ArrayList<Testeo>();
        this.in = new Scanner(System.in);

    }

    public void iniciar() {
        String opcion = "";
        while (!opcion.equalsIgnoreCase("f")) {
            try {
                mostrarMenu();
                opcion = in.nextLine();
                if (opcion.equalsIgnoreCase("a")) {
                    registrarTester();
                } else if (opcion.equalsIgnoreCase("b")) {
                    registrarMatrizActual();
                } else if (opcion.equalsIgnoreCase("c")) {
                    registrarTesteo();
                } else if (opcion.equalsIgnoreCase("d")) {
                    consultarTesters();
                } else if (opcion.equalsIgnoreCase("e")) {
                    mostrarEstadisticas();
                } else if (opcion.equalsIgnoreCase("f")) {
                    System.out.println("Fin del programa.");
                } else {
                    System.out.println("Opción inválida.");
                }

            } catch (Exception e) {
                System.out.println("Ocurrió un error, ingrese una opción válida");

            }

        }

    }

    public void mostrarMenu() {
        System.out.println("a) Registrar tester");
        System.out.println("b) Registrar matriz actual del juego");
        System.out.println("c) Registrar testeo");
        System.out.println("d) Consulta de testers");
        System.out.println("e) Estadísticas");
        System.out.println("f) Terminar");
    }

    public void registrarTester() {
        String nombre = "";
        boolean nombreValido = false;
        int edad = 0;
        boolean edadValida = false;
        int aniosExperiencia = 0;
        boolean experienciaValida = false;

        System.out.println();
        System.out.println("=== Registrar tester ===");

        while (!nombreValido) {
            System.out.print("Ingrese nombre: ");
            nombre = in.nextLine();
            if (nombre.equals("")) {
                System.out.println("El nombre no puede estar vacío.");
            } else if (existeTester(nombre)) {
                System.out.println("Ya existe un tester con ese nombre.");
            } else {
                nombreValido = true;
            }
        }

        while (!edadValida) {
            try {
                System.out.print("Ingrese edad: ");
                edad = in.nextInt();
                in.nextLine();
                if (edad > 0) {
                    edadValida = true;
                } else {
                    System.out.println("La edad debe ser mayor a 0.");
                }
            } catch (InputMismatchException e) {
                System.out.println("La edad debe ser un número.");
                in.nextLine();

            }
        }
        while (!experienciaValida) {
            try {
                System.out.print("Ingrese años de experiencia: ");
                aniosExperiencia = in.nextInt();
                in.nextLine();
                if (aniosExperiencia < 0) {
                    System.out.println("Los años de experiencia no pueden ser negativos.");
                } else if (aniosExperiencia >= edad) {
                    System.out.println("Los años de experiencia deben ser menores a la edad.");
                } else {
                    experienciaValida = true;
                }

            } catch (InputMismatchException e) {
                System.out.println("Los años de experiencia deben ser un número.");
                in.nextLine();

            }

        }
        System.out.println("Nombre: " + nombre + " - edad: " + edad + " - Años de experiencia: " + aniosExperiencia);
        Tester nuevoTester = new Tester(nombre, edad, aniosExperiencia);
        testers.add(nuevoTester);
        System.out.println("Tester registrado correctamente.");

    }

    public void registrarMatrizActual() {
        System.out.println();
        System.out.println("=== Registrar matriz actual ===");
        System.out.println("Matriz actual:");
        System.out.println(tablero.prepararTablero());
        System.out.println("Si desea continuar con la matriz actual, presione S");
        System.out.println("De lo contrario, presione N");
        String respuesta = in.nextLine();
        if (respuesta.equalsIgnoreCase("S")) {
            System.out.println("Matriz actual:");
            System.out.println(tablero.prepararTablero());
        } else if (respuesta.equalsIgnoreCase("N")) {
            System.out.println("A - Cargar matriz por defecto");
            System.out.println("B - Cargar matriz manualmente");
            System.out.print("Ingrese opción: ");
            String opcion = in.nextLine();
            if (opcion.equalsIgnoreCase("A")) {
                tablero.cargarMatrizPorDefecto();
                System.out.println("Matriz por defecto: ");
                System.out.println(tablero.prepararTablero());
            } else if (opcion.equalsIgnoreCase("B")) {
                boolean cargada = false;
                while (!cargada) {
                    String[] filasIngresadas = new String[8];
                    for (int i = 0; i < filasIngresadas.length; i++) {
                        System.out.print("Ingrese fila " + (i + 1) + ": ");
                        filasIngresadas[i] = in.nextLine();
                    }
                    cargada = tablero.cargarMatriz(filasIngresadas);
                    if (!cargada) {
                        System.out.println("La matriz ingresada no es válida.");
                        System.out.println("Ingrese la matriz nuevamente.");
                    }

                }
                System.out.println("Matriz cargada correctamente.");
                System.out.println(tablero.prepararTablero());

            } else {
                System.out.println("Opción inválida");
            }

        }

    }

    public void registrarTesteo() {
        System.out.println();
        System.out.println("=== Registrar testeo ===");
        String resultado = "";
        String parametros = "";
        String[][] matrizOriginal = tablero.copiarMatriz();
        String[][] matrizResultado = new String[matrizOriginal.length][matrizOriginal[0].length];
        String comentario = "";
        int numero = 0;
        if (testers.isEmpty()) {
            System.out.println("No hay testers registrados");
        } else {
            Tester testerSeleccionado = elegirTester();
            int caso = elegirCaso();
            System.out.println("Tester seleccionado: " + testerSeleccionado.getNombre());
            System.out.println("Caso seleccionado: " + caso);

            if (caso == 1) {
                String color = pedirColor();
                matrizOriginal = tablero.copiarMatriz();
                int cantidad = tablero.contarFichas(color);
                matrizResultado = tablero.copiarMatriz();
                parametros = "Color: " + color;
                resultado = "Cantidad de fichas " + color + ": " + cantidad;

            } else if (caso == 2) {
                String color = pedirColor();
                String sentido = pedirSentidoIndividual();
                int fila = pedirEntero("Ingrese fila (de 0 a 7): ", "La fila debe ser un número");
                int columna = pedirEntero("Ingrese columna (de 0 a 9): ", "La columna debe ser un número");
                int pasos = pedirEntero("Ingrese cantidad de pasos: ", "Los pasos deben ser un número");
                matrizOriginal = tablero.copiarMatriz();
                boolean ok = tablero.validarMovimientoIndividual(color, sentido, fila, columna, pasos);
                matrizResultado = tablero.copiarMatriz();
                parametros = "Color: " + color + ", Sentido: " + sentido + ", fila: " + fila
                        + ", columna: " + columna + ", pasos: " + pasos;
                resultado = "Resultado: " + " " + ok;

            } else if (caso == 3) {
                String color = pedirColor();
                String forma = pedirForma();
                String sentido = pedirSentidoGrupo();
                int fila = pedirEntero("Ingrese fila (de 0 a 7): ", "La fila debe ser un número");
                int columna = pedirEntero("Ingrese columna (de 0 a 9): ", "La columna debe ser un número");
                int tamanio = pedirEntero("Ingrese tamaño: ", "Tamaño debe ser un número");
                int pasos = pedirEntero("Ingrese cantidad de pasos: ", "Los pasos deben ser un número");
                matrizOriginal = tablero.copiarMatriz();
                boolean ok = tablero.validarMovimientoEnGrupo(color, forma, sentido, fila, columna, tamanio, pasos);
                matrizResultado = tablero.copiarMatriz();
                parametros = "Color: " + color + ", Forma: " + forma + ", Sentido: " + sentido + ", Fila: " + fila
                        + ", Columna: " + columna + ", Tamaño: " + tamanio + ", Pasos: " + pasos;
                resultado = "Resultado: " + " " + ok;

            } else if (caso == 4) {
                matrizOriginal = tablero.copiarMatriz();
                String textoTablero = tablero.prepararTablero();
                matrizResultado = tablero.copiarMatriz();
                parametros = "Sin parámetros";
                resultado = textoTablero;
            } else if (caso == 5) {
                String color = pedirColor();
                matrizOriginal = tablero.copiarMatriz();
                boolean ok = tablero.verificarConexion(color);
                matrizResultado = tablero.copiarMatriz();
                parametros = "Color: " + color;
                resultado = "Resultado: " + ok;
            }
            System.out.println("Ingrese un comentario para registrar Testeo");
            comentario = in.nextLine();
            numero = testeos.size() + 1;
            Testeo nuevoTesteo = new Testeo(numero, caso, testerSeleccionado, parametros, comentario, resultado, matrizOriginal, matrizResultado);
            testeos.add(nuevoTesteo);
            testerSeleccionado.agregarTesteo(nuevoTesteo);
            System.out.println(resultado);
            System.out.println("Testeo registrado con exito");
        }

    }

    private String pedirColor() {
        String color = "";
        while (!color.equalsIgnoreCase("B") && !color.equalsIgnoreCase("N")) {
            System.out.println("Elija un color entre B o N");
            color = in.nextLine();
        }
        return color.toUpperCase();
    }

    private String pedirSentidoIndividual() {
        String sentido = "";
        while (!sentido.equalsIgnoreCase("N")
                && !sentido.equalsIgnoreCase("S")
                && !sentido.equalsIgnoreCase("E")
                && !sentido.equalsIgnoreCase("O")
                && !sentido.equalsIgnoreCase("NE")
                && !sentido.equalsIgnoreCase("NO")
                && !sentido.equalsIgnoreCase("SE")
                && !sentido.equalsIgnoreCase("SO")) {

            System.out.println("Elija un sentido");
            System.out.println("Opciones: N - S - E - O - NE - NO - SE - SO");
            sentido = in.nextLine();
        }
        return sentido.toUpperCase();
    }

    private int pedirEntero(String mensaje, String mensajeError) {
        int valor = 0;
        boolean valido = false;
        while (!valido) {
            try {
                System.out.println(mensaje);
                valor = in.nextInt();
                in.nextLine();
                valido = true;
            } catch (InputMismatchException e) {
                System.out.println(mensajeError);
                in.nextLine();
            }
        }
        return valor;
    }

    private String pedirForma() {
        String forma = "";
        while (!forma.equalsIgnoreCase("H") && !forma.equalsIgnoreCase("V")) {
            System.out.println("Elija forma entre H o V");
            forma = in.nextLine();
        }
        return forma.toUpperCase();
    }

    private String pedirSentidoGrupo() {
        String sentido = "";
        while (!sentido.equalsIgnoreCase("N")
                && !sentido.equalsIgnoreCase("S")
                && !sentido.equalsIgnoreCase("E")
                && !sentido.equalsIgnoreCase("O")) {

            System.out.println("Elija un sentido");
            System.out.println("Opciones: N - S - E - O");
            sentido = in.nextLine();
        }
        return sentido.toUpperCase();

    }

    private Tester elegirTester() {
        Tester seleccionado = null;
        while (seleccionado == null) {
            for (int i = 0; i < testers.size(); i++) {
                System.out.println((i + 1) + " - " + testers.get(i).getNombre());
            }
            try {
                System.out.println("Ingrese número de tester");
                int opcion = in.nextInt();
                in.nextLine();
                if (opcion < 1 || opcion > testers.size()) {
                    System.out.println("Opcion fuera de rango, vuelva a ingresar");

                } else {
                    seleccionado = testers.get(opcion - 1);
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un valor válido");
                in.nextLine();

            }

        }

        return seleccionado;
    }

    private int elegirCaso() {
        int caso = 0;
        System.out.println("Casos disponibles:");
        System.out.println("1 - Contar fichas");
        System.out.println("2 - Validar movimiento individual");
        System.out.println("3 - Validar movimiento en grupo");
        System.out.println("4 - Mostrar tablero");
        System.out.println("5 - Verificar conexión");

        while (!(caso <= 5 && caso > 0)) {
            try {
                System.out.println("Ingrese un valor entre 1 y 5");
                caso = in.nextInt();
                in.nextLine();
                if (caso < 1 || caso > 5) {
                    System.out.println("Opción fuera de rango");
                }

            } catch (InputMismatchException e) {
                System.out.println("Ingrese un número válido");
                in.nextLine();
            }
        }
        return caso;
    }

    public void consultarTesters() {
        System.out.println("=== Consulta de Testers ===");
        if (testers.isEmpty()) {
            System.out.println("No hay testers registrados");
        } else {
            Tester testerSeleccionado = elegirTester();
            System.out.println("Tester seleccionado: " + testerSeleccionado.getNombre());
            ArrayList<Testeo> testeosTester = testerSeleccionado.getTesteos();
            if (testeosTester.isEmpty()) {
                System.out.println("El tester no tiene testeos registrados");
            } else {
                Collections.sort(testeosTester);
                for (int i = 0; i < testeosTester.size(); i++) {
                    Testeo testeoTester = testeosTester.get(i);
                    System.out.println(testeoTester.getNumero() + " - Caso " + testeoTester.getCaso());

                }
                Testeo testeoSeleccionado = null;
                int numeroBuscado = 0;
                while (testeoSeleccionado == null) {
                    try {
                        System.out.println("Ingrese número de testeo:");
                        numeroBuscado = in.nextInt();
                        in.nextLine();
                        for (int i = 0; i < testeosTester.size() && testeoSeleccionado == null; i++) {
                            if (testeosTester.get(i).getNumero() == numeroBuscado) {
                                testeoSeleccionado = testeosTester.get(i);
                            }

                        }
                        if (testeoSeleccionado == null) {
                            System.out.println("No existe un test con ese número para el tester");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Debe ingresar un número válido");
                        in.nextLine();
                    }

                }
                System.out.println("=== Datos del testeo ===");
                System.out.println(testeoSeleccionado);

                System.out.println("Matriz original:");
                System.out.println(prepararMatrizConsulta(testeoSeleccionado.getMatrizOriginal()));

                if (huboModificaciones(testeoSeleccionado.getMatrizOriginal(), testeoSeleccionado.getMatrizResultante())) {
                    System.out.println("Matriz resultante:");
                    System.out.println(prepararMatrizConsulta(testeoSeleccionado.getMatrizResultante()));
                } else {
                    System.out.println("No hubo modificaciones en la matriz.");
                }

            }

        }

    }

    public void mostrarEstadisticas() {
        System.out.println("=== Mostrar Estadísticas ===");
        if (testers.isEmpty()) {
            System.out.println("No hay testers registrados");
        } else {
            String opcion = "";
            while (!opcion.equalsIgnoreCase("A") && !opcion.equalsIgnoreCase("B")) {
                System.out.println("Elija Opcion entre A y B");
                System.out.println("Opcion A - Mostrar Testers con mas testeos");
                System.out.println("Opcion B - Mostrar Testers sin testeos");
                opcion = in.nextLine();
            }
            if (opcion.equalsIgnoreCase("A")) {
                ArrayList<Tester> listaTesteosMax = obtenerTestersConMasTesteos();
                mostrarListaTesters(listaTesteosMax);
            } else if (opcion.equalsIgnoreCase("B")) {
                ArrayList<Tester> listaSinTesteos = obtenerTestersSinTesteos();
                mostrarListaTesters(listaSinTesteos);
            }

        }

    }

    public ArrayList<Tester> obtenerTestersConMasTesteos() {
        int maximo = -1;
        ArrayList<Tester> listaMaximos = new ArrayList<Tester>();
        for (int i = 0; i < testers.size(); i++) {
            Tester testerElegido = testers.get(i);
            int cantidadTesteos = testerElegido.getTesteos().size();
            if (cantidadTesteos > maximo) {
                maximo = cantidadTesteos;
                listaMaximos.clear();
                listaMaximos.add(testerElegido);

            } else if (cantidadTesteos == maximo) {
                listaMaximos.add(testerElegido);
            }

        }
        return listaMaximos;
    }

    public ArrayList<Tester> obtenerTestersSinTesteos() {
        ArrayList<Tester> listaSinTesteos = new ArrayList<Tester>();

        for (int i = 0; i < testers.size(); i++) {
            Tester testerElegido = testers.get(i);
            int cantidadTesteos = testerElegido.getTesteos().size();
            if (cantidadTesteos == 0) {

                listaSinTesteos.add(testerElegido);
            }
        }
        return listaSinTesteos;
    }

    private boolean existeTester(String nombre) {
        boolean existe = false;

        for (int i = 0; i < testers.size() && !existe; i++) {
            if (nombre.equalsIgnoreCase(testers.get(i).getNombre())) {
                existe = true;
            }
        }

        return existe;
    }

    private void mostrarListaTesters(ArrayList<Tester> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay Testers para mostrar.");
        } else {
            for (int i = 0; i < lista.size(); i++) {
                System.out.println(lista.get(i));
            }
        }

    }

    private String prepararMatrizConsulta(String[][] matriz) {
        String tableroConsulta = "";
        for (int i = 0; i < matriz.length; i++) {
            tableroConsulta += "+---+---+---+---+---+---+---+---+---+---+\n";
            for (int j = 0; j < matriz[0].length; j++) {
                tableroConsulta += "| " + matriz[i][j] + " ";
            }
            tableroConsulta += "|\n";
        }
        tableroConsulta += "+---+---+---+---+---+---+---+---+---+---+\n";

        return tableroConsulta;
    }

    private boolean huboModificaciones(String[][] original, String[][] resultado) {
        boolean modificada = false;
        for (int i = 0; i < original.length && !modificada; i++) {
            for (int j = 0; j < original[0].length && !modificada; j++) {
                if (!original[i][j].equalsIgnoreCase(resultado[i][j])) {
                    modificada = true;
                }
            }
        }
        return modificada;
    }

}
