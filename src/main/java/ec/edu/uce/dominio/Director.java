package ec.edu.uce.dominio;

import java.util.Arrays; // Aunque no se usa directamente en Director, se mantiene por herencia o si se usara en el futuro

import ec.edu.uce.dominio.Fecha;
import ec.edu.uce.dominio.Genero;

/**
 * Clase Director que hereda de Manager y añade atributos para presupuesto y comisión.
 */
public class Director extends Manager {

    private double presupuesto;
    private double comision;

    // Constructor sin argumentos
    public Director() {
        super();
        this.presupuesto = 0.0;
        this.comision = 0.0;
    }

    // Constructor con datos esenciales
    public Director(String nombre, String apellido, Genero genero, double salario, String departamento, double presupuesto, double comision) {
        super(nombre, apellido, genero, salario, departamento);
        setPresupuesto(presupuesto);
        setComision(comision);
    }

    // Constructor completo SIN ID inicial
    public Director(String nombre, String apellido, String correo, String direccion,
                    String[] telefonos, Fecha fechaNacimiento, Genero genero,
                    double salario, String puesto, Fecha fechaContratacion, String departamento, double presupuesto, double comision) {
        super(nombre, apellido, correo, direccion, telefonos, fechaNacimiento, genero, salario, puesto, fechaContratacion, departamento);
        setPresupuesto(presupuesto);
        setComision(comision);
    }

    /**
     * Constructor completo para Director, que recibe el ID explícito.
     */
    public Director(int id, String nombre, String apellido, String correo, String direccion,
                    String[] telefonos, Fecha fechaNacimiento, Genero genero,
                    double salario, String puesto, Fecha fechaContratacion, String departamento, double presupuesto, double comision) {
        super(id, nombre, apellido, correo, direccion, telefonos, fechaNacimiento, genero,
                salario, puesto, fechaContratacion, departamento);
        setPresupuesto(presupuesto);
        setComision(comision);
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        if (presupuesto >= 0) {
            this.presupuesto = presupuesto;
        } else {
            System.out.println("Advertencia (Director Presupuesto): El presupuesto no puede ser negativo. Se estableció en 0.0.");
            this.presupuesto = 0.0;
        }
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        if (comision >= 0) {
            this.comision = comision;
        } else {
            System.out.println("Advertencia (Director Comisión): La comisión no puede ser negativa. Se estableció en 0.0.");
            this.comision = 0.0;
        }
    }

    /**
     * Aprueba un gasto si el presupuesto es suficiente.
     */
    public boolean aprobarGasto(double gasto) {
        if (gasto <= 0) {
            return false;
        }
        if (gasto <= presupuesto) {
            presupuesto -= gasto;
            return true;
        } else {
            System.out.println("Gasto rechazado: $" + String.format("%.2f", gasto) +
                    ". Presupuesto disponible: $" + String.format("%.2f", presupuesto));
            return false;
        }
    }

    /**
     * Calcula el bono anual del Director (bono de Manager + porcentaje de comisión).
     */
    @Override
    public double calcularBonoAnual() {
        return super.calcularBonoAnual() + (this.comision * 0.15);
    }

    /**
     * Devuelve una cadena con la información del Director.
     */
    @Override
    public String toString() {
        // Llama al toString de Manager y añade la información específica de Director
        return super.toString().replace("Manager [", "Director [") +
                ", Presupuesto = $" + String.format("%.2f", presupuesto) +
                ", Comisión = $" + String.format("%.2f", comision) + "]";
    }
}