package ec.edu.uce.dominio;

import java.util.Arrays;

/**
 * Clase Cliente, representa un cliente del banco.
 */
public class Cliente {

    // Contador estático para IDs únicos
    public static int contador;

    static {
        contador = 0;
    }

    private final int id; // ID único, inmutable
    private String nombre;
    private String apellido;
    private String correo;
    private String direccion;
    private String[] telefonos;
    private Fecha fechaNacimiento;
    private Genero genero;
    private Cuenta[] cuentas; // Arreglo de cuentas
    private int numCuentas; // Contador de cuentas

    /**
     * Constructor completo para crear un cliente.
     * El ID se asigna automáticamente.
     */
    public Cliente(String nombre, String apellido, String correo, String direccion,
                   String[] telefonos, Fecha fechaNacimiento, Genero genero) {
        this.id = ++contador;

        setNombre(nombre);
        setApellido(apellido);
        setCorreo(correo);
        setDireccion(direccion);
        setTelefonos(telefonos);
        setFechaNacimiento(fechaNacimiento);
        setGenero(genero);

        this.cuentas = new Cuenta[5]; // Capacidad inicial
        this.numCuentas = 0;
    }

    /**
     * Constructor básico para crear un cliente.
     * El ID se asigna automáticamente, otros datos con valores por defecto.
     */
    public Cliente(String nombre, String apellido, Genero genero) {
        this(nombre, apellido, "", "", new String[0], null, genero);
    }

    /**
     * Constructor con ID proporcionado externamente.
     * Actualiza el contador estático.
     */
    public Cliente(int id, String nombre, String apellido, Genero genero) {
        this.id = id;
        if (id >= contador) {
            contador = id + 1;
        }

        setNombre(nombre);
        setApellido(apellido);
        setCorreo("");
        setDireccion("");
        setTelefonos(new String[0]);
        setFechaNacimiento(null);
        setGenero(genero);

        this.cuentas = new Cuenta[5];
        this.numCuentas = 0;
    }

    // --- Métodos Getters y Setters ---

    public int getId() { return id; }

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
        this.telefonos = (telefonos != null) ? Arrays.copyOf(telefonos, telefonos.length) : new String[0];
    }

    public Fecha getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Fecha fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Genero getGenero() { return genero; }
    public void setGenero(Genero genero) {
        this.genero = (genero != null) ? genero : Genero.OTRO;
    }

    public Cuenta[] getCuentas() {
        return Arrays.copyOf(cuentas, numCuentas); // Copia defensiva
    }

    public int getNumCuentas() { return numCuentas; }

    /**
     * Agrega una cuenta al cliente. Redimensiona el arreglo si es necesario.
     */
    public void agregarCuenta(Cuenta cuenta) {
        if (cuenta == null) {
            return;
        }
        // Redimensionar si el arreglo está lleno
        if (numCuentas == cuentas.length) {
            Cuenta[] nuevoArreglo = new Cuenta[cuentas.length * 2];
            System.arraycopy(cuentas, 0, nuevoArreglo, 0, cuentas.length);
            this.cuentas = nuevoArreglo;
        }
        cuentas[numCuentas++] = cuenta;
    }

    /**
     * Elimina una cuenta por su índice. Los elementos posteriores se mueven.
     */
    public boolean eliminarCuenta(int indice) {
        if (indice < 0 || indice >= numCuentas) {
            return false;
        }
        // Mover elementos para llenar el espacio
        System.arraycopy(cuentas, indice + 1, cuentas, indice, numCuentas - 1 - indice);
        cuentas[--numCuentas] = null; // Anular la última referencia
        return true;
    }

    /**
     * Busca una cuenta por su índice.
     */
    public Cuenta buscarCuenta(int indice) {
        if (indice >= 0 && indice < numCuentas) {
            return cuentas[indice];
        }
        return null;
    }

    /**
     * Genera una cadena con los detalles de todas las cuentas del cliente.
     */
    public String consultarCuentasDetalle() {
        String detalleCuentas = "";
        if (numCuentas == 0) {
            detalleCuentas = "   - No hay cuentas asociadas a este cliente.\n";
        } else {
            for (int i = 0; i < numCuentas; i++) {
                // Asumiendo que Cuenta.toString() es útil
                detalleCuentas = detalleCuentas + "   Cuenta " + (i + 1) + ": " + cuentas[i].toString() + "\n";
            }
        }
        return detalleCuentas;
    }

    /**
     * Compara clientes por su ID.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cliente cliente = (Cliente) obj;
        return id == cliente.id;
    }

    /**
     * Genera el valor hash consistente con equals.
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    /**
     * Devuelve una cadena con la información básica del cliente.
     */
    @Override
    public String toString() {
        return "Cliente [ID=" + id + ", Nombre=" + nombre + ", Apellido=" + apellido +
                ", Género=" + genero.getNombre() + ", Num. Cuentas=" + numCuentas + "]";
    }
}