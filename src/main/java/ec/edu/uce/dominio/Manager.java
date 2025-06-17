package ec.edu.uce.dominio;

/**
 * Representa a un manager, heredando de Empleado y añadiendo el departamento.
 */
public class Manager extends Empleado {

    private String deptNombre;

    // Constructor sin argumentos.
    public Manager() {
        super("Sin Nombre", "Sin Apellido", Genero.OTRO, 0.0);
        this.deptNombre = "sin departamento";
    }

    // Constructor con datos esenciales.
    public Manager(String nombre, String apellido, Genero genero, double salario, String departamento) {
        super(nombre, apellido, genero, salario);
        setDeptNombre(departamento);
    }

    // Constructor completo que autogenera ID.
    public Manager(String nombre, String apellido, String correo, String direccion,
                   String[] telefonos, Fecha fechaNacimiento, Genero genero,
                   double salario, String puesto, Fecha fechaContratacion, String departamento) {
        super(nombre, apellido, correo, direccion, telefonos, fechaNacimiento, genero, salario, puesto, fechaContratacion);
        setDeptNombre(departamento);
    }

    /**
     * Constructor para Manager con ID proporcionado externamente.
     */
    public Manager(int id, String nombre, String apellido, String correo, String direccion,
                   String[] telefonos, Fecha fechaNacimiento, Genero genero,
                   double salario, String puesto, Fecha fechaContratacion, String departamento) {
        super(id, nombre, apellido, correo, direccion, telefonos, fechaNacimiento, genero,
                salario, puesto, fechaContratacion);
        setDeptNombre(departamento);
    }

    public String getDeptNombre() {
        return deptNombre;
    }

    public void setDeptNombre(String deptNombre) {
        if (deptNombre != null && !deptNombre.trim().isEmpty()) {
            this.deptNombre = deptNombre;
        } else {
            this.deptNombre = "sin departamento";
        }
    }

    @Override
    public double calcularBonoAnual() {
        // Bono del 30% del salario para el Manager.
        return getSalario() * 0.30;
    }

    // Retorna el bono calculado.
    public double getBono() {
        return calcularBonoAnual();
    }

    @Override
    public String toString() {
        // Llama al toString de Empleado y añade información del departamento y bono.
        return super.toString().replace("Empleado [", "Manager [") +
                ", Departamento=" + deptNombre + ", Bono Anual=" + String.format("%.2f", calcularBonoAnual());
    }
}