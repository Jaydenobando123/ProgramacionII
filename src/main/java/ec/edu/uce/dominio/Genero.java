package ec.edu.uce.dominio;

/**
 * Enum para representar los géneros disponibles: Femenino, Masculino, Otro.
 * Cada género tiene una abreviatura y un nombre completo.
 */
public enum Genero {

    FEMENINO('F', "Femenino"),
    MASCULINO('M', "Masculino"),
    OTRO('O', "Otro");

    private char abrev;
    private String nombre;

    /**
     * Constructor privado para las opciones del enum.
     */
    private Genero(char abrev, String nombre) {
        this.abrev = abrev;
        this.nombre = nombre;
    }

    /**
     * Devuelve la abreviatura del género.
     */
    public char getAbrev() {
        return abrev;
    }

    /**
     * Devuelve el nombre completo del género.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el nombre completo del género al imprimir el enum.
     */
    @Override
    public String toString() {
        return nombre;
    }
}