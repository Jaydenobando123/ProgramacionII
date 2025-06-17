package ec.edu.uce.dominio;

/**
 * Clase para manejar fechas básicas (día, mes, año).
 * Incluye validaciones simples.
 */
public class Fecha {

    private int dia;
    private int mes;
    private int anio;

    /**
     * Constructor que crea una fecha usando los valores recibidos y validándolos.
     */
    public Fecha(int dia, int mes, int anio) {
        setAnio(anio);
        setMes(mes);
        setDia(dia);
    }

    public int getDia() { return dia; }
    public void setDia(int dia) {
        // Validación: día entre 1 y 31.
        this.dia = (dia >= 1 && dia <= 31) ? dia : 1;
    }

    public int getMes() { return mes; }
    public void setMes(int mes) {
        // Validación: mes entre 1 y 12.
        this.mes = (mes >= 1 && mes <= 12) ? mes : 1;
    }

    public int getAnio() { return anio; }
    public void setAnio(int anio) {
        // Validación: año entre 1900 y 2100.
        this.anio = (anio >= 1900 && anio <= 2100) ? anio : 2000;
    }

    /**
     * Convierte la fecha a un texto legible en formato día/mes/año.
     */
    @Override
    public String toString() {
        return dia + "/" + mes + "/" + anio;
    }
}