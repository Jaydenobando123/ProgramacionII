package ec.edu.uce.dominio;

import java.util.Arrays;

/**
 * Clase de prueba para la clase Cliente.
 * Incluye pruebas de constructores, getters, setters y gestión de cuentas.
 */
public class TestCliente {
    private Cliente cliente;

    // Constructor por defecto de la clase de prueba.
    public TestCliente() {
        // El cliente se inicializa en los métodos de prueba.
    }

    /**
     * Prueba el constructor completo de Cliente.
     */
    public void testConstructorCompleto() {
        System.out.println("\n=============== Pruebas de Constructores de Cliente ===============");
        System.out.println("--- Probando constructor completo ---");

        String[] telefonos = {"0987654321", "022345678"};
        Fecha fechaNacimiento = new Fecha(10, 5, 1995);

        cliente = new Cliente("Jayden", "Obando", "jayob@uce.edu.ec",
                "Calle La Concordia 123", telefonos, fechaNacimiento, Genero.MASCULINO);

        System.out.println("  - Cliente creado con constructor completo:");
        System.out.println("  " + cliente);

        assert cliente != null : "¡El cliente no se creó correctamente!";
        assert cliente.getNombre().equals("Jayden") : "¡El nombre no es el esperado!";
        assert cliente.getGenero() == Genero.MASCULINO : "¡El género no es el esperado!";
        System.out.println("  Prueba de constructor completo finalizada.");
    }

    /**
     * Prueba los métodos {@code set} de Cliente.
     */
    public void testSet() {
        System.out.println("\n=============== Pruebas de Setters de Cliente ===============");
        if (cliente == null) {
            System.out.println("  ¡Advertencia! El cliente no está inicializado. Llamando a testConstructorCompleto().");
            testConstructorCompleto();
        }

        System.out.println("  - Cliente antes de modificar: " + cliente.getNombre() + " " + cliente.getApellido());

        cliente.setNombre("Kiara");
        cliente.setApellido("Faliha");
        cliente.setCorreo("KFAL@correo.com");
        cliente.setFechaNacimiento(new Fecha(25, 6, 2000));

        String[] nuevosTelefonos = {"0998765432", "023456789"};
        cliente.setTelefonos(nuevosTelefonos);
        cliente.setDireccion("Avenida Siempre Viva 742");
        cliente.setGenero(Genero.FEMENINO);

        System.out.println("  - Atributos de cliente modificados.");
        System.out.println("  - Cliente después de modificar: " + cliente);

        assert cliente.getNombre().equals("Kiara") : "¡Setter de nombre falló!";
        assert cliente.getApellido().equals("Faliha") : "¡Setter de apellido falló!";
        assert cliente.getCorreo().equals("KFAL@correo.com") : "¡Setter de correo falló!";
        assert cliente.getFechaNacimiento().getAnio() == 2000 : "¡El año de fecha de nacimiento no es el esperado!";
        assert Arrays.equals(cliente.getTelefonos(), nuevosTelefonos) : "¡Los teléfonos no se actualizaron!";
        assert cliente.getDireccion().equals("Avenida Siempre Viva 742") : "¡La dirección no se actualizó!";
        assert cliente.getGenero() == Genero.FEMENINO : "¡El género no se actualizó!";
        System.out.println("  Pruebas de setters de Cliente finalizadas.");
    }

    /**
     * Prueba los métodos {@code get} de Cliente.
     */
    public void testGet() {
        System.out.println("\n--- Pruebas de Getters de Cliente ---");
        if (cliente == null) {
            System.out.println("  ¡Advertencia! El cliente no está inicializado. Llamando a testConstructorCompleto().");
            testConstructorCompleto();
        }

        System.out.println("  - ID de cliente: " + cliente.getId());
        System.out.println("  - Nombre de cliente: " + cliente.getNombre());
        System.out.println("  - Apellido de cliente: " + cliente.getApellido());
        System.out.println("  - Correo de cliente: " + cliente.getCorreo());
        System.out.println("  - Dirección de cliente: " + cliente.getDireccion());
        System.out.println("  - Teléfonos de cliente: " + Arrays.toString(cliente.getTelefonos()));
        System.out.println("  - Fecha de Nacimiento de cliente: " + cliente.getFechaNacimiento());
        System.out.println("  - Género de cliente: " + cliente.getGenero().getNombre());
        System.out.println("  - Número de cuentas de cliente: " + cliente.getNumCuentas());

        assert cliente.getId() > 0 : "¡Getter de ID devuelve un valor inválido!";
        assert cliente.getNombre() != null && !cliente.getNombre().isEmpty() : "¡Getter de nombre nulo o vacío!";
        assert cliente.getApellido() != null && !cliente.getApellido().isEmpty() : "¡Getter de apellido nulo o vacío!";
        assert cliente.getCorreo() != null : "¡Getter de correo nulo!";
        assert cliente.getDireccion() != null : "¡Getter de dirección nulo!";
        assert cliente.getTelefonos() != null : "¡Getter de teléfonos nulo!";
        assert cliente.getFechaNacimiento() != null : "¡Getter de fecha de nacimiento nulo!";
        assert cliente.getGenero() != null : "¡Getter de género nulo!";
        System.out.println("  Pruebas de getters de Cliente finalizadas.");
    }

    /**
     * Prueba el constructor que solo recibe nombre, apellido y género.
     */
    public void testConstructorBasico(String nombre, String apellido, Genero genero) {
        System.out.println("\n=============== Prueba del Constructor Básico de Cliente ===============");
        Cliente clienteBasico = new Cliente(nombre, apellido, genero);
        System.out.println("  - Cliente básico creado:");
        System.out.println("  " + clienteBasico);

        assert clienteBasico != null : "¡El cliente básico no se creó!";
        assert clienteBasico.getNombre().equals(nombre) : "¡El nombre del cliente básico no coincide!";
        assert clienteBasico.getApellido().equals(apellido) : "¡El apellido del cliente básico no coincide!";
        assert clienteBasico.getGenero().equals(genero) : "¡El género del cliente básico no coincide!";
        assert clienteBasico.getId() > 0 : "¡El ID del cliente básico no se generó!";
        assert clienteBasico.getCorreo() != null && clienteBasico.getCorreo().isEmpty() : "¡El correo del cliente básico no es vacío!";
        assert clienteBasico.getDireccion() != null && clienteBasico.getDireccion().isEmpty() : "¡La dirección del cliente básico no es vacía!";
        assert clienteBasico.getTelefonos() != null && clienteBasico.getTelefonos().length == 0 : "¡Los teléfonos del cliente básico no son un arreglo vacío!";
        assert clienteBasico.getFechaNacimiento() == null : "¡La fecha de nacimiento del cliente básico no es nula!";

        System.out.println("  Prueba de constructor básico finalizada.");
    }

    /**
     * Prueba las operaciones de gestión de cuentas del cliente (agregar, editar, eliminar, consultar).
     */
    public void testCuentas() {
        System.out.println("\n=============== Pruebas de Gestión de Cuentas de Cliente ===============");

        if (cliente == null) {
            System.out.println("  ¡Advertencia! El cliente no está inicializado. Llamando a testConstructorCompleto().");
            testConstructorCompleto();
        }

        System.out.println("\n--- 1. Agregando cuentas ---");
        int numCuentasInicial = cliente.getNumCuentas();
        cliente.agregarCuenta(new CuentaAhorro(100.0));
        cliente.agregarCuenta(new CuentaCorriente(250.0, 50.0));
        cliente.agregarCuenta(new CuentaAhorro(500.0));
        System.out.println("  - Cuentas agregadas.");
        System.out.println("  - Cuentas recién creadas:");
        System.out.println(cliente.consultarCuentasDetalle());
        assert cliente.getNumCuentas() == numCuentasInicial + 3 : "¡No se agregaron las 3 cuentas esperadas!";

        System.out.println("\n--- 2. Editando el saldo de la segunda cuenta (índice 1) ---");
        Cuenta cuentaAEditar = cliente.buscarCuenta(numCuentasInicial + 1);
        if (cuentaAEditar instanceof CuentaCorriente) {
            System.out.println("  - Antes de editar (Cuenta Corriente): " + cuentaAEditar);
            boolean depositoExitoso = cuentaAEditar.deposito(50.0);
            System.out.println("  - Depósito de $50 exitoso: " + depositoExitoso);
            System.out.println("  - Después de depositar $50: " + cuentaAEditar);
            assert depositoExitoso : "¡El depósito de $50 no funcionó!";
            assert cuentaAEditar.getBalance() == 300.0 : "¡El balance después del depósito no es el esperado!";

            boolean retiroExitoso = cuentaAEditar.retiro(100.0);
            System.out.println("  - Retiro de $100 exitoso: " + retiroExitoso);
            System.out.println("  - Después de retirar $100: " + cuentaAEditar);
            assert retiroExitoso : "¡El retiro de $100 no funcionó!";
            assert cuentaAEditar.getBalance() == 200.0 : "¡El balance después del retiro no es el esperado!";
        } else {
            System.out.println("  ¡Error en prueba! La cuenta esperada no es CuentaCorriente o es nula.");
            assert false : "¡Error en prueba! La cuenta esperada no es CuentaCorriente o es nula.";
        }

        System.out.println("\n--- 3. Eliminando la primera cuenta (índice 0) ---");
        int numCuentasAntesEliminar = cliente.getNumCuentas();
        boolean eliminada = cliente.eliminarCuenta(numCuentasInicial);
        System.out.println("  - Cuenta en índice " + numCuentasInicial + " eliminada: " + eliminada);
        System.out.println("  - Cuentas después de eliminar la primera:");
        System.out.println(cliente.consultarCuentasDetalle());
        assert eliminada : "¡La eliminación de la cuenta 0 falló!";
        assert cliente.getNumCuentas() == numCuentasAntesEliminar - 1 : "¡El número de cuentas no se actualizó!";
        assert cliente.buscarCuenta(numCuentasInicial) instanceof CuentaCorriente : "¡Error al eliminar: elementos no se movieron correctamente!";

        System.out.println("\n--- 4. Consultando cuentas restantes ---");
        System.out.println("  - Cuenta restante en índice " + numCuentasInicial + ": " + cliente.buscarCuenta(numCuentasInicial));
        System.out.println("  - Cuenta restante en índice " + (numCuentasInicial + 1) + ": " + cliente.buscarCuenta(numCuentasInicial + 1));
        assert cliente.buscarCuenta(numCuentasInicial) != null : "¡La cuenta en el índice " + numCuentasInicial + " es nula!";
        assert cliente.buscarCuenta(numCuentasInicial + 1) != null : "¡La cuenta en el índice " + (numCuentasInicial + 1) + " es nula!";

        System.out.println("  Pruebas de gestión de cuentas finalizadas.");
    }

    /**
     * Método principal para ejecutar todas las pruebas de la clase.
     */
    public static void main(String[] args) {
        TestCliente miTest = new TestCliente();

        Cliente.contador = 0; // Resetear para asegurar IDs consistentes.
        System.out.println("Contador de IDs de Cliente reseteado para la prueba de TestCliente.");

        System.out.println("\n=============== Ejecutando Pruebas de Atributos de Cliente ===============");
        miTest.testConstructorCompleto();
        miTest.testSet();
        miTest.testGet();

        System.out.println("\n=============== Ejecutando Pruebas de Gestión de Cuentas ===============");
        miTest.testCuentas();

        System.out.println("\n=============== Ejecutando Pruebas con Clientes Adicionales ===============");
        miTest.testConstructorBasico("Juan", "Zamba", Genero.MASCULINO);
        miTest.testConstructorBasico("Annabella", "Perez", Genero.FEMENINO);
        miTest.testConstructorBasico("Carlos", "Allora", Genero.MASCULINO);

        System.out.println("\n=============== Todas las Pruebas de Cliente han Finalizado ===============");
    }
}