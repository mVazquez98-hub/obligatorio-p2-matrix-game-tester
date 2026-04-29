package obligatorio;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));
        System.out.println("Trabajo desarrollado por: Martín Vázquez - 180210");
        System.out.println("MatrixGameTester - Inicio");

        Sistema sistema = new Sistema();
        sistema.iniciar();
    }

}
