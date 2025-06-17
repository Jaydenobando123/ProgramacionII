package ec.edu.uce.dominio;

/**
 * Clase principal para ejecutar pruebas con las clases de prueba.
 * Sirve como un "runner" de pruebas manual.
 */
public class Test {

    /**
     * Método principal que ejecuta las pruebas definidas.
     */
    public static void main(String[] args) {
        // Resetear el contador de IDs de Cliente para pruebas consistentes.
        Cliente.contador = 0;
        System.out.println("Contador de IDs de Cliente reseteado para la prueba.");

        System.out.println("\n=============== Ejecutando pruebas de Cliente a través de TestCliente ===============");

        // Instancia de TestCliente para ejecutar sus métodos de prueba.
        TestCliente testClienteRunner = new TestCliente();

        // Ejecutar las pruebas individuales del TestCliente.
        testClienteRunner.testConstructorCompleto();
        testClienteRunner.testSet();
        testClienteRunner.testGet();
        testClienteRunner.testCuentas();

        System.out.println("\n=============== Probando clientes directamente en Test ===============");

        // Pruebas directas de clientes usando el constructor básico.
        Cliente cliente1 = new Cliente("Juan", "Zamba", Genero.MASCULINO);
        System.out.println("ID del cliente1 (Juan Zamba): " + cliente1.getId());
        System.out.println("Nombre: " + cliente1.getNombre() + ", Apellido: " + cliente1.getApellido() + ", Genero: " + cliente1.getGenero().getNombre());

        Cliente cliente2 = new Cliente("Annabella", "Perez", Genero.FEMENINO);
        System.out.println("ID del cliente2 (Annabella Perez): " + cliente2.getId());
        System.out.println("Nombre: " + cliente2.getNombre() + ", Apellido: " + cliente2.getApellido() + ", Genero: " + cliente2.getGenero().getNombre());

        Cliente clienteC = new Cliente("Carlos", "Allora", Genero.MASCULINO);
        System.out.println("ID del cliente 'clienteC' (Carlos Allora): " + clienteC.getId());
        System.out.println("Nombre: " + clienteC.getNombre() + ", Apellido: " + clienteC.getApellido() + ", Genero: " + clienteC.getGenero().getNombre());

        System.out.println("\n=============== Todas las pruebas han finalizado desde Test.java ===============");
    }
}