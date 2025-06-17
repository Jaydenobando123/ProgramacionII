/**
 * @author Jayden Obando
 */
package ec.edu.uce.dominio;

/**
 * Clase de utilidad para generar un informe completo de clientes y sus cuentas bancarias.
 */
public class ReporteCliente {

    /**
     * Genera un reporte detallado de todos los clientes registrados en el banco.
     * Incluye información personal de cada cliente y un listado de sus cuentas.
     *
     * @return Una cadena de texto con el reporte completo de clientes.
     */
    public static String reporteClientes() {
        StringBuilder textoReporte = new StringBuilder("Reporte Clientes\n");
        Banco banco = Banco.getInstance(); // Obtiene la instancia única del banco

        // Itera sobre la lista de clientes del banco
        for (Cliente cliente : banco.getClientes()) {
            if (cliente != null) {
                textoReporte.append("----------------------------------------\n");
                textoReporte.append("CLIENTE: ").append(cliente).append("\n"); // Información básica del cliente

                textoReporte.append("Cuentas del Cliente:\n");
                // Muestra el detalle de las cuentas del cliente, si las tiene
                if (cliente.getNumCuentas() == 0) {
                    textoReporte.append("   - No tiene cuentas asociadas.\n");
                } else {
                    textoReporte.append(cliente.consultarCuentasDetalle());
                }
                textoReporte.append("\n"); // Separador entre clientes
            }
        }
        return textoReporte.toString();
    }
}