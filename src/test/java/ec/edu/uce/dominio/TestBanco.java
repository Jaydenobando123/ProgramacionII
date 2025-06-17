package ec.edu.uce.dominio;

/**
 * Clase de prueba para la clase Banco.
 * Se encarga de crear el banco, añadir empleados y verificar su funcionamiento.
 */
public class TestBanco {

    /**
     * Método principal para ejecutar las pruebas del banco.
     */
    public static void main(String[] args) {
        // Obtener la única instancia del banco (Singleton).
        Banco banco = Banco.getInstance();
        System.out.println("--- Iniciando pruebas de Banco ---");
        System.out.println("Estado inicial del Banco:\n" + banco.toString());

        // Resetear el contador de IDs de empleados para pruebas consistentes.
        Empleado.contador = 0;
        System.out.println("\nContador de IDs de Empleado reseteado para la prueba.");

        System.out.println("\n--- Añadiendo empleados al Banco ---");

        // Agregar empleados de diferentes tipos y verificar su adición.
        boolean agregado1 = banco.agregarEmpleado(new Empleado("Empleado", "Generico", Genero.OTRO, 1000.0));
        System.out.println("Empleado Genérico agregado: " + (agregado1 ? "Sí" : "No"));
        assert agregado1 : "¡Fallo al agregar Empleado Generico!";

        boolean agregado2 = banco.agregarEmpleado(new Empleado("Laura", "Sanchez", Genero.FEMENINO, 1000.0));
        System.out.println("Laura Sanchez agregada: " + (agregado2 ? "Sí" : "No"));
        assert agregado2 : "¡Fallo al agregar Laura Sanchez!";

        boolean agregado3 = banco.agregarEmpleado(new Manager("Maria", "Rodriguez", Genero.FEMENINO, 2000.0, "Ventas"));
        System.out.println("Maria Rodriguez (Manager) agregada: " + (agregado3 ? "Sí" : "No"));
        assert agregado3 : "¡Fallo al agregar Maria Rodriguez (Manager)!";

        boolean agregado4 = banco.agregarEmpleado(new Director("Pedro", "Gomez", Genero.MASCULINO, 3000.0, "IT", 60000.0, 0.0));
        System.out.println("Pedro Gomez (Director) agregado: " + (agregado4 ? "Sí" : "No"));
        assert agregado4 : "¡Fallo al agregar Pedro Gomez (Director)!";

        // Mostrar la lista actual de empleados y verificar el total.
        System.out.println("\n--- Lista de empleados actual en el Banco ---");
        System.out.println(banco.consultarEmpleados());
        assert banco.getNumEmpleados() == 4 : "¡El número de empleados no es el esperado!";

        System.out.println("--- Pruebas de Banco finalizadas ---");
    }
}