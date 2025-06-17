package ec.edu.uce.dominio;

import java.util.Arrays;

/**
 * Esta clase representa a un empleado dentro del banco.
 * Guarda información personal y laboral, como nombre, salario, género, etc.
 * Autor: Jayden Obando
 */
public class Empleado {

    /**
     * Salario básico referencial para todos los empleados del banco.
     * Es una constante accesible directamente sin necesidad de crear un objeto.
     */
    public static final double SALARIO_BASICO = 470.00;

    /**
     * Este contador sirve para generar IDs únicos a cada empleado.
     * Cada vez que se crea uno nuevo con el constructor que autogenera ID, se incrementa automáticamente.
     * Si se usa el constructor que recibe ID, el contador se ajusta para evitar lo que son conflictos.
     */
    public static int contador;

    // Este bloque de código se ejecuta solo una vez cuando se carga la clase por primera vez.
    // Aquí se inicializa el contador en 0.
    static {
        contador = 0;
    }

    // Atributos que describen al empleado
    private final int id; // El ID se genera automáticamente o se asigna, y es inmutable
    private String nombre;
    private String apellido;
    private String correo;
    private String direccion;
    private String[] telefonos;
    private Fecha fechaNacimiento;
    private Genero genero;
    private double salario;
    private String puesto;
    private Fecha fechaContratacion;

    /**
     * Constructor completo que recibe todos los datos del empleado.
     * El ID se asigna automáticamente al crear el objeto usando este constructor.
     */
    public Empleado(String nombre, String apellido, String correo, String direccion,
                    String[] telefonos, Fecha fechaNacimiento, Genero genero,
                    double salario, String puesto, Fecha fechaContratacion) {
        this.id = ++contador; // Asigna un ID único autogenerado al nuevo empleado

        // Asignaciones con validaciones básicas usando los setters
        setNombre(nombre);
        setApellido(apellido);
        setCorreo(correo);
        setDireccion(direccion);
        setTelefonos(telefonos);
        setFechaNacimiento(fechaNacimiento);
        setGenero(genero);
        setSalario(salario);
        setPuesto(puesto);
        setFechaContratacion(fechaContratacion);
    }

    /**
     * Constructor más sencillo, que solo requiere datos esenciales.
     * Los demás campos se completan con valores por defecto.
     * El ID se asigna automáticamente.
     */
    public Empleado(String nombre, String apellido, Genero genero, double salario) {
        // Llama al constructor completo con valores por defecto para los campos no proporcionados.
        this(nombre, apellido, "", "", new String[0], null, genero, salario, "", null);
        // El ID ya se asigna en el constructor completo llamado arriba.
    }



    public Empleado(int id, String nombre, String apellido, String correo, String direccion,
                    String[] telefonos, Fecha fechaNacimiento, Genero genero,
                    double salario, String puesto, Fecha fechaContratacion) {
        this.id = id; // Asigna el ID que se recibe como parámetro

        // Ajusta el contador estático para asegurar unicidad en IDs futuros autogenerados.
        if (id >= contador) {
            contador = id + 1;
        }

        // Asignaciones con validaciones básicas usando los setters
        setNombre(nombre);
        setApellido(apellido);
        setCorreo(correo);
        setDireccion(direccion);
        setTelefonos(telefonos);
        setFechaNacimiento(fechaNacimiento);
        setGenero(genero);
        setSalario(salario);
        setPuesto(puesto);
        setFechaContratacion(fechaContratacion);
    }

    // Métodos para acceder (get) y modificar (set) cada atributo.
    // No se permite modificar el ID ya que es asignado automáticamente o en el constructor.

    public int getId() { return id; }
    // No hay setter para el ID porque es final.

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        this.nombre = (nombre != null && !nombre.trim().isEmpty()) ? nombre : "Desconocido";
    }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) {
        this.apellido = (apellido != null && !apellido.trim().isEmpty()) ? apellido : "Desconocido";
    }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) {
        this.correo = (correo != null) ? correo : "";
    }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) {
        this.direccion = (direccion != null) ? direccion : "";
    }

    public String[] getTelefonos() { return telefonos; }
    public void setTelefonos(String[] telefonos) {
        // Se realiza una copia defensiva del arreglo para evitar modificaciones externas
        this.telefonos = (telefonos != null) ? Arrays.copyOf(telefonos, telefonos.length) : new String[0];
    }

    public Fecha getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Fecha fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Genero getGenero() { return genero; }
    public void setGenero(Genero genero) {
        // Si el género proporcionado es nulo, se establece a Genero.OTRO
        this.genero = (genero != null) ? genero : Genero.OTRO;
    }

    public double getSalario() { return salario; }
    public void setSalario(double salario) {
        // Validamos que el salario no sea negativo
        if (salario >= 0) {
            this.salario = salario;
        } else {
            System.out.println("Advertencia (Empleado Salario): El salario no puede ser negativo. Se estableció en 0.0.");
            this.salario = 0.0;
        }
    }

    public String getPuesto() { return puesto; }
    public void setPuesto(String puesto) {
        this.puesto = (puesto != null && !puesto.trim().isEmpty()) ? puesto : "Sin Puesto";
    }

    public Fecha getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(Fecha fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    /**
     * Calcula el bono anual del empleado.
     * Por defecto, equivale al 20% del salario.
     */
    public double calcularBonoAnual() {
        return salario * 0.20;
    }

    /**
     * Devuelve la información principal del empleado en forma de texto.
     * Incluye ID, nombre, salario, puesto, género y teléfonos.
     */
    @Override
    public String toString() {
        String telString = (telefonos != null && telefonos.length > 0)
                ? String.join(", ", telefonos)
                : "No tiene";

        return "Empleado [ID=" + id + ", Nombre=" + nombre + ", Apellido=" + apellido +
                ", Género=" + genero + ", Salario=" + String.format("%.2f", salario) +
                ", Puesto=" + puesto + ", Teléfonos=" + telString + "]";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return id == empleado.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}