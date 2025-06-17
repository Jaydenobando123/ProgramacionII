package ec.edu.uce.dominio;

import java.util.Arrays;

/**
 * Clase de prueba para verificar el comportamiento de Empleado, Manager y Director.
 */
public class TestEmpleado {

    /**
     * Método principal para realizar pruebas básicas con objetos Empleado, Manager y Director.
     */
    public static void main(String[] args) {
        System.out.println("=============== Iniciando pruebas de Empleados y Managers ===============");

        Empleado.contador = 0; // Resetear contador de IDs para pruebas consistentes.
        System.out.println("\nContador de IDs de Empleado reseteado para la prueba.");

        // 1. Crear un empleado básico.
        Empleado miEmpleado = new Empleado("Pedro", "Vargas", "pedro.v@mail.com", "Calle Eloy Alfaro",
                new String[]{"17000", "0991234567"}, null, Genero.MASCULINO, 1500.0, "Asistente", new Fecha(10, 1, 2023));

        System.out.println("\nEmpleado básico: " + miEmpleado);
        System.out.println("Teléfonos: " + Arrays.toString(miEmpleado.getTelefonos()));

        assert miEmpleado.getNombre().equals("Pedro") : "¡El nombre del empleado básico no es el esperado!";
        assert miEmpleado.getTelefonos() != null && miEmpleado.getTelefonos().length > 0 && miEmpleado.getTelefonos()[0].equals("17000") : "¡El primer teléfono del empleado básico no es el esperado!";

        // 2. Crear un manager.
        Manager miManager = new Manager("Maria", "Rodriguez", "maria.r@mail.com", "Av. Quito",
                new String[]{"0987654321"}, null, Genero.FEMENINO, 2000.0, "Jefa de Operaciones", new Fecha(1, 1, 2022), "Operaciones");
        System.out.println("\nManager (antes de probar incremento): " + miManager);
        assert miManager.getSalario() == 2000.0 : "¡El manager no tiene el salario base esperado!";
        assert miManager.getDeptNombre().equals("Operaciones") : "¡El departamento del manager no es el esperado!";

        // 3. Incrementar el salario del manager.
        System.out.println("\n=============== Intentando incrementar el salario del Manager ===============");
        double incremento = 1000.00;
        miManager.setSalario(miManager.getSalario() + incremento);
        System.out.println("  Se simuló el incremento de salario.");
        System.out.println("  Manager (después de intento de incremento): " + miManager);
        assert miManager.getSalario() == (2000.0 + incremento) : "¡El salario del manager no se incrementó como esperaba!";

        // 4. Demostrar polimorfismo y downcasting.
        System.out.println("\n=============== Demostrando Polimorfismo y Downcasting ===============");
        Manager managerPoly1 = new Manager("Fito", "Pelaez", "fito.p@mail.com", "Dir 1",
                new String[]{}, null, Genero.MASCULINO, 2200.0, "Jefe de Produccion", new Fecha(1, 2, 2023), "Produccion");
        Empleado empleadoComoManager1 = managerPoly1;

        Manager managerPoly2 = new Manager("Juana", "Williams", "juana.w@mail.com", "Dir 2",
                new String[]{}, null, Genero.FEMENINO, 2000.0, "Manager", new Fecha(1, 3, 2023), "Recursos Humanos");
        Empleado empleadoComoManager2 = managerPoly2;

        if (empleadoComoManager1 instanceof Manager) {
            Manager managerCasteado = (Manager) empleadoComoManager1;
            System.out.println("Departamento del manager casteado (empleadoComoManager1): " + managerCasteado.getDeptNombre());
            assert managerCasteado.getDeptNombre().equals("Produccion") : "El departamento del manager casteado no es el esperado";
        }

        System.out.println("Acceso directo al departamento (downcasting para empleadoComoManager2): " +
                ((Manager) empleadoComoManager2).getDeptNombre());
        assert ((Manager) empleadoComoManager2).getDeptNombre().equals("Recursos Humanos") : "¡El departamento del segundo manager casteado no es el esperado!";

        // Prueba con Director.
        System.out.println("\n=============== Probando con un Director ===============");
        Director directorTest = new Director("Carlos", "Guzman", "carlos.g@mail.com", "Dir 3",
                new String[]{}, null, Genero.MASCULINO, 3000.0, "Director", new Fecha(1, 1, 2020), "General", 100000.0, 50000.0);
        Empleado empleadoComoDirector = directorTest;

        if (empleadoComoDirector instanceof Director) {
            Director directorCasteado = (Director) empleadoComoDirector;
            System.out.println("Director: " + directorCasteado.getNombre() + ", Departamento: " + directorCasteado.getDeptNombre() + ", Comisión: " + directorCasteado.getComision() + ", Presupuesto: " + directorCasteado.getPresupuesto());
            assert directorCasteado.getComision() == 50000.0 : "La comisión del director no es la esperada";
            assert directorCasteado.getPresupuesto() == 100000.0 : "El presupuesto del director no es el esperado";
        }

        // 5. Pruebas del método equals() de Empleado.
        System.out.println("\n=============== Pruebas del Método equals() de Empleado ===============");

        Empleado empleadoParaComparar1 = new Empleado("Lionel", "Scaloni", "l.m@mail.com", "Dir 4",
                new String[]{}, null, Genero.MASCULINO, 1000.0, "Empleado", new Fecha(1, 1, 2004));
        Empleado empleadoParaComparar2 = new Empleado("Lionel", "Messi", "l.m@mail.com", "Dir 4",
                new String[]{}, null, Genero.MASCULINO, 2000.0, "Manager", new Fecha(1, 1, 2000));

        System.out.println("Empleado 1: " + empleadoParaComparar1);
        System.out.println("Empleado 2: " + empleadoParaComparar2);

        if (empleadoParaComparar1.equals(empleadoParaComparar2)){
            System.out.println("Empleados son iguales (esto solo si equals no usa ID autogenerado).");
            assert false : "Empleados con IDs diferentes no deberían ser iguales si equals usa ID";
        } else {
            System.out.println("Empleados son distintos (esperado si equals usa ID).");
            assert true : "Empleados con IDs diferentes deberían ser distintos";
        }

        // 6. Caso donde los empleados son distintos.
        Empleado empleadoParaComparar3 = new Empleado("Andreina", "Galu", "a.g@mail.com", "Dir 5",
                new String[]{}, null, Genero.FEMENINO, 1000.0, "Empleada", new Fecha(1, 1, 1990));

        System.out.println("\nEmpleado 1: " + empleadoParaComparar1);
        System.out.println("Empleado 3: " + empleadoParaComparar3);

        if (!empleadoParaComparar1.equals(empleadoParaComparar3)){
            System.out.println("\nEmpleados Lionel y Andreina son distintos (como se espera).");
            assert true : "Los empleados deberían ser distintos, pero equals() dice que son iguales";
        } else {
            System.out.println("\nError en equals() Los empleados Lionel y Andreina deberían ser distintos.");
            assert false : "Error en equals() Los empleados Lionel y Andreina deberían ser distintos.";
        }

        System.out.println("\n=============== Todas las pruebas de Empleado han finalizado ===============");
    }
}