package ec.edu.uce.Dato;

import ec.edu.uce.dominio.Empleado;

public interface EmpleadoDAO {
    public void crear(Empleado empleado);
    public void editar(Empleado empelado);
    public void eliminar(int id);
    public Empleado buscarPorId(int id);
    public Empleado[]consultarEmpleado();




}
