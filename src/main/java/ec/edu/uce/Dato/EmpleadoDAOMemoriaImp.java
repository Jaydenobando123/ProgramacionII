package ec.edu.uce.Dato;

import ec.edu.uce.dominio.Empleado;
import java.util.Arrays;

public class EmpleadoDAOMemoriaImp implements EmpleadoDAO {

    private static Empleado[] empleados;
    private static int cantEmpleados; // Contador de empleados activos

    static {
        empleados = new Empleado[5]; // Capacidad inicial del arreglo
        cantEmpleados = 0;
    }

    @Override
    public void crear(Empleado emp) {
        if (emp == null || buscarPorId(emp.getId()) != null) {
            return; // No crear nulos o duplicados
        }

        // Redimensionar si el arreglo está lleno
        if (cantEmpleados == empleados.length) {
            empleados = Arrays.copyOf(empleados, empleados.length * 2);
        }
        empleados[cantEmpleados] = emp;
        cantEmpleados++;
    }

    @Override
    public void editar(Empleado emp) {
        if (emp == null) {
            return;
        }
        for (int i = 0; i < cantEmpleados; i++) {
            if (empleados[i] != null && empleados[i].getId() == emp.getId()) {
                empleados[i] = emp; // Reemplazar el empleado
                return;
            }
        }
    }

    @Override
    public void eliminar(int id) {
        int indiceAEliminar = -1;
        for (int i = 0; i < cantEmpleados; i++) {
            if (empleados[i] != null && empleados[i].getId() == id) {
                indiceAEliminar = i;
                break;
            }
        }

        if (indiceAEliminar != -1) {
            // Desplazar elementos para rellenar el espacio
            System.arraycopy(empleados, indiceAEliminar + 1, empleados, indiceAEliminar, cantEmpleados - 1 - indiceAEliminar);
            empleados[--cantEmpleados] = null; // Anular la última referencia
        }
    }

    @Override
    public Empleado buscarPorId(int id) {
        for (int i = 0; i < cantEmpleados; i++) {
            if (empleados[i] != null && empleados[i].getId() == id) {
                return empleados[i];
            }
        }
        return null; // Empleado no encontrado
    }

    @Override
    public Empleado[] consultarEmpleado() {
        // Devolver una copia defensiva de los empleados activos
        return Arrays.copyOf(empleados, cantEmpleados);
    }
}