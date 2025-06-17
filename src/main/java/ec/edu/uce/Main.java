/**
 * @author Jayden Obando
 */
package ec.edu.uce;
import ec.edu.uce.dominio.*;

/**
 * Esta es mi clase principal Main, donde pruebo las funcionalidades de mi Banco
 * y de todas las clases que he diseñado.
 */
public class Main {
    public static void main(String[] args) {
        // Obtengo la única instancia de mi banco (patrón Singleton).
        Banco banco = Banco.getInstance();

        System.out.println("=============== Estado inicial de mi Banco ===============\n" + banco);

        // --- Clientes ---
        System.out.println("\n--- Intentando agregar Clientes ---");
        // Ejemplo de cómo agregar un cliente según el género.
        String gen = "F"; // Puedes cambiar a "M" para probar el otro caso.

        switch (gen){
            case "F":{
                Banco.agregarCliente(1, "Lila", "Landazuri", Genero.FEMENINO);
                System.out.println("He añadido a Lila Landazuri (Femenino, ID: 1) a mi lista de clientes.");
                break;
            }
            case "M":{
                Banco.agregarCliente(2, "Ronaldo", "Nazario", Genero.MASCULINO);
                System.out.println("He añadido a Ronaldo Nazario (Masculino, ID: 2) a mi lista de clientes.");
                break;
            }
            default: {
                System.out.println("Género no reconocido para añadir cliente.");
                break;
            }
        }
        // Agregamos otro cliente para tener más datos.
        Banco.agregarCliente(3, "Carlos", "Silva", Genero.MASCULINO);
        System.out.println("He añadido a Carlos Silva (Masculino, ID: 3) a mi lista de clientes.");


        // --- Empleados ---
        // Agrego diferentes tipos de empleados.
        Fecha fechaContratacionEmp = new Fecha(15, 3, 2020);

        Banco.agregarEmpleado(new Empleado(101, "Juan", "Perez", "juan.p@mail.com", "Dir. Emp", new String[]{"0000000000"}, new Fecha(1,1,1990), Genero.MASCULINO, 1000.0, "Empleado", fechaContratacionEmp));

        Fecha fechaContratacionMan = new Fecha(1, 7, 2018);
        Banco.agregarEmpleado(new Manager(102, "Cafu","Fafa", "cafu@mail.com", "Dir. Man", new String[]{"0000000000"}, new Fecha(2,2,1980), Genero.MASCULINO, 2000.0, "Manager", fechaContratacionMan, "Ninguno"));

        Fecha fechaContratacionDir = new Fecha(10, 1, 2010);

        Banco.agregarEmpleado(new Director(103, "Carl","Jhonsom", "carl@mail.com", "Dir. Dir", new String[]{"0000000000"}, new Fecha(3,3,1975), Genero.MASCULINO, (int)3000.0, "Director General", fechaContratacionDir, "Ninguno", 50000.0, 1000.0));

        System.out.println("\n=============== Mi Equipo de Empleados Actualizado ===============\n" + Banco.consultarEmpleados());

        // Recorro mi equipo de empleados para calcular y mostrar el impuesto.
        System.out.println("\n=============== Cálculo de Impuestos por Empleado ===============");
        for (Empleado empleado : Banco.getStaff()) {
            if (empleado != null){
                System.out.println("\nEmpleado: " + empleado.getNombre() + " " + empleado.getApellido() +
                        " \nImpuesto a pagar: $" + String.format("%.2f", Impuesto.calcularImpuesto(empleado)));
            }
        }

        // Obtengo el primer cliente agregado (ID 1).
        Cliente clientePrincipal = Banco.buscarCliente(1);
        if (clientePrincipal != null) {
            System.out.println("\n=============== Operaciones con el Cliente: " + clientePrincipal.getNombre() + " ===============");

            clientePrincipal.agregarCuenta(new CuentaAhorro(300.50, 1)); // Balance inicial y tasa (dummy si es static final).
            clientePrincipal.agregarCuenta(new CuentaCorriente(800.00, 150.00)); // Balance inicial y límite de sobregiro.
            clientePrincipal.agregarCuenta(new TarjetaCredito(0.0, 1200.00)); // Deuda inicial y cupo total.

            System.out.println("\nCuentas de " + clientePrincipal.getNombre() + ":\n" + clientePrincipal.consultarCuentasDetalle());

            // Realizando algunas operaciones de prueba en una de las cuentas
            Cuenta cuentaAhorroDeCliente = clientePrincipal.buscarCuenta(0); // La primera cuenta agregada.
            if (cuentaAhorroDeCliente != null && cuentaAhorroDeCliente instanceof CuentaAhorro) {
                System.out.println("\n=============== Operaciones en la Cuenta de Ahorro de " + clientePrincipal.getNombre() + " ===============");
                cuentaAhorroDeCliente.deposito(50.00);
                cuentaAhorroDeCliente.retiro(20.00);

                ((CuentaAhorro) cuentaAhorroDeCliente).calculoInteres();
                System.out.println("Estado final de la Cuenta de Ahorro: " + cuentaAhorroDeCliente);
            } else {
                System.out.println("\nNo se encontró una Cuenta de Ahorro válida en el índice 0 para " + clientePrincipal.getNombre() + " o no es una instancia de CuentaAhorro.");
            }
        } else {
            System.out.println("\nNo se pudo encontrar al cliente principal (ID 1) para realizar operaciones.");
        }

        // Genero el reporte final de todos mis clientes con sus cuentas.
        System.out.println("\n=============== Reporte Final de Clientes y Sus Cuentas ===============");
        System.out.println(ReporteCliente.reporteClientes());

        // Puedes agregar más pruebas aquí si lo necesitas.
        System.out.println("\n=============== Fin de la Ejecución del Main Mi Ñaño ===============\n" + banco);
    }
}