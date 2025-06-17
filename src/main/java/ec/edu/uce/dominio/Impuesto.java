/**
 * @author Jayden Obando
 */
package ec.edu.uce.dominio;

/**
 * Esta es mi clase de utilidad para calcular el impuesto sobre el salario de mis empleados.
 * La tasa de impuesto varía según el tipo de empleado (Director, Manager o Empleado regular).
 */
public class Impuesto {

    /**
     * Calculo el monto del impuesto para un empleado específico.
     * La lógica que utilizo para el cálculo del impuesto difiere si el empleado es un Director,
     * un Manager o un Empleado general.
     *
     * @param empleado El objeto Empleado sobre el cual deseo calcular el impuesto.
     * @return El monto total del impuesto que debe pagar el empleado.
     */
    public static double calcularImpuesto(Empleado empleado) {
        double impuesto = 0.00;

        if (empleado == null) {
            return 0.0;
        }

        if (empleado instanceof Director) {
            Director director = (Director) empleado;
            // Ahora getComision() EXISTE en Director.java
            impuesto = (director.getSalario() + director.getComision()) * 0.15;
        } else if (empleado instanceof Manager) {
            Manager manager = (Manager) empleado;
            // Ahora getBono() EXISTE en Manager.java
            impuesto = (manager.getSalario() + manager.getBono()) * 0.10;
        } else {
            impuesto = empleado.getSalario() * 0.05;
        }
        return impuesto;
    }
}