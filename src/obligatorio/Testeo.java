package obligatorio;

public class Testeo {

    private int numero;
    private int caso;
    private Tester tester;
    private String parametros;
    private String comentario;
    private String resultado;
    private String[][] matrizOriginal;
    private String[][] matrizResultante;

    public Testeo(int numero, int caso, Tester tester, String parametros, String comentario, String resultado, String[][] matrizOriginal, String[][] matrizResultado) {
        this.numero = numero;
        this.caso = caso;
        this.tester = tester;
        this.parametros = parametros;
        this.comentario = comentario;
        this.resultado = resultado;
        this.matrizOriginal = matrizOriginal;
        this.matrizResultante = matrizResultado;
    }

    public int getNumero() {
        return numero;
    }

    public int getCaso() {
        return caso;
    }

    public Tester getTester() {
        return tester;
    }

    public String getParametros() {
        return parametros;
    }

    public String getComentario() {
        return comentario;
    }

    public String getResultado() {
        return resultado;
    }

    public String[][] getMatrizOriginal() {
        return matrizOriginal;
    }

    public String[][] getMatrizResultante() {
        return matrizResultante;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}
