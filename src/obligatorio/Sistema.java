package obligatorio;

import java.util.ArrayList;
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

    }
    public void registrarMatrizActual(){
        
    }
    public void registrarTesteo(){
        
    }
    public void consultarTesters(){
        
    }
    public void mostrarEstadisticas(){
        
    }


}
