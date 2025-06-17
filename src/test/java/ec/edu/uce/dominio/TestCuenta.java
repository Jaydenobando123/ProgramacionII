package ec.edu.uce.dominio;

/**
 * Clase de prueba para la clase Cuenta.
 * Realiza operaciones básicas como creación, depósitos y retiros.
 */
public class TestCuenta {

    /**
     * Método principal para ejecutar las pruebas de Cuenta.
     */
    public static void main(String[] args) {
        System.out.println("=============== Iniciando pruebas de Cuenta ===============");

        // Crear un cliente de prueba.
        Cliente miCliente = new Cliente("Luisa", "Espinoza", Genero.FEMENINO);
        System.out.println("\nCliente de prueba creado: " + miCliente.getNombre() + " " + miCliente.getApellido());
        System.out.println("ID del cliente: " + miCliente.getId());

        // Crear y agregar una cuenta de ahorro al cliente.
        CuentaAhorro nuevaCuentaAhorro = new CuentaAhorro(100.0);
        miCliente.agregarCuenta(nuevaCuentaAhorro);
        System.out.println("Intentando agregar Cuenta de Ahorro al cliente...");
        assert miCliente.getNumCuentas() == 1 : "¡Fallo al agregar CuentaAhorro!";

        // Obtener la cuenta para realizar operaciones.
        Cuenta miCuenta = miCliente.buscarCuenta(0);

        System.out.println("\n=============== Saldo inicial de la cuenta ===============");
        if (miCuenta != null) {
            System.out.println("El saldo inicial de la cuenta es: $" + String.format("%.2f", miCuenta.getBalance()));
            assert miCuenta.getBalance() == 100.0 : "¡El saldo inicial no es el esperado!";
        } else {
            System.out.println("¡Error! La cuenta del cliente no fue encontrada.");
            assert false : "¡La cuenta de cliente no fue encontrada!";
            return;
        }

        System.out.println("\n=============== Realizando un depósito de $150.0 ===============");
        boolean depositoExitoso = miCuenta.deposito(150.0);
        System.out.println("Depósito exitoso: " + (depositoExitoso ? "Sí" : "No"));
        System.out.println("Saldo después del depósito: $" + String.format("%.2f", miCuenta.getBalance()));
        assert depositoExitoso : "¡El depósito no se aplicó correctamente!";
        assert miCuenta.getBalance() == 250.0 : "¡El saldo después del depósito no es el esperado!";

        System.out.println("\n=============== Realizando un retiro de $100.0 ===============");
        boolean retiroExitoso = miCuenta.retiro(100.0);
        System.out.println("Retiro exitoso: " + (retiroExitoso ? "Sí" : "No"));
        System.out.println("Saldo después del retiro: $" + String.format("%.2f", miCuenta.getBalance()));
        assert retiroExitoso : "¡El retiro no se aplicó correctamente!";
        assert miCuenta.getBalance() == 150.0 : "¡El saldo después del retiro no es el esperado!";

        // Prueba específica para CuentaAhorro: cálculo de interés.
        if (miCuenta instanceof CuentaAhorro) {
            System.out.println("\n=============== Calculando interés en Cuenta de Ahorro ===============");
            double interesCalculado = ((CuentaAhorro) miCuenta).calculoInteres();
            System.out.println("Interés calculado: $" + String.format("%.2f", interesCalculado));
            System.out.println("Saldo después del cálculo de interés: $" + String.format("%.2f", miCuenta.getBalance()));
            assert interesCalculado == (150.0 * ((CuentaAhorro) miCuenta).getTasaInteres()) : "¡El interés calculado no es el esperado!";
            assert miCuenta.getBalance() == 150.0 : "¡El saldo no debería cambiar si calculoInteres solo calcula!";
        } else {
            System.out.println("\n=============== La cuenta no es de Ahorro, no se calcula interés de esta manera. ===============");
        }

        System.out.println("\n=============== Representación completa de la cuenta (toString) ===============");
        System.out.println(miCuenta);

        System.out.println("\n=============== Todas las pruebas de Cuenta han finalizado ===============");
    }
}