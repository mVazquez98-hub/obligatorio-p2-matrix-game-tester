package obligatorio;

import java.util.ArrayList;

public class Tester {

    private String nombre;
    private int edad;
    private int aniosExperiencia;
    private ArrayList<Testeo> testeos;

    public Tester(String unNombre, int unaEdad, int experiencia) {
        this.nombre = unNombre;
        this.edad = unaEdad;
        this.aniosExperiencia = experiencia;
        this.testeos = new ArrayList<Testeo>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setNombre(String unNombre) {
        nombre = unNombre;
    }

    public void setEdad(int unaEdad) {
        edad = unaEdad;
    }

    public void setAniosExperiencia(int experiencia) {
        aniosExperiencia = experiencia;
    }

    public ArrayList<Testeo> getTesteos() {
        return testeos;
    }

    public void agregarTesteo(Testeo unTesteo) {
        testeos.add(unTesteo);
    }

}
