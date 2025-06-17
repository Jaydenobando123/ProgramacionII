package ec.edu.uce.dominio;

/**
 * Clase CuentaAhorro que hereda de Cuenta.
 * Implementa métodos de depósito, retiro y cálculo de intereses.
 */
public class CuentaAhorro extends Cuenta {

    // Tasa de interés fija para todas las cuentas de ahorro.
    private static final double TASA_INTERES = 0.05;

    // Constructor por defecto.
    public CuentaAhorro() {
        super();
    }

    // Constructor que inicializa la cuenta con un balance específico.
    public CuentaAhorro(double balanceInicial) {
        super(balanceInicial);
    }

    // Constructor para cumplir el requisito de 3 constructores.
    public CuentaAhorro(double balanceInicial, int dummyTasa) {
        super(balanceInicial);
    }

    // Obtiene la tasa de interés fija.
    public double getTasaInteres() {
        return TASA_INTERES;
    }

    // Implementación del método abstracto deposito.
    @Override
    public boolean deposito(double monto) {
        if (monto > 0) {
            this.balance += monto;
            return true;
        } else {
            System.out.println("Error: El monto a depositar debe ser positivo.");
            return false;
        }
    }

    // Implementación del método abstracto retiro.
    @Override
    public boolean retiro(double monto) {
        if (monto > 0 && this.balance >= monto) {
            this.balance -= monto;
            return true;
        } else {
            System.out.println("Error: Monto de retiro inválido o fondos insuficientes.");
            return false;
        }
    }

    // Calcula y aplica los intereses al balance.
    @Override
    public double calculoInteres() {
        double interesCalculado = this.balance * TASA_INTERES;
        this.balance += interesCalculado;
        return interesCalculado;
    }

    // Descripción breve de la cuenta.
    @Override
    public String descripcion() {
        return "Cuenta de Ahorro";
    }

    @Override
    public String toString() {
        return descripcion() + " [Balance=" + String.format("%.2f", this.balance) +
                ", Tasa Interés Fija=" + String.format("%.2f", (TASA_INTERES * 100)) + "%]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        CuentaAhorro that = (CuentaAhorro) obj;

        if (Double.compare(this.balance, that.balance) != 0) return false;
        if (Double.compare(TASA_INTERES, CuentaAhorro.TASA_INTERES) != 0) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(balance, TASA_INTERES);
    }
}