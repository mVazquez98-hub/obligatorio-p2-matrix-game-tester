package obligatorio;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

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

    }

    public void consultarTesters() {

    }

    public void mostrarEstadisticas() {

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

}
