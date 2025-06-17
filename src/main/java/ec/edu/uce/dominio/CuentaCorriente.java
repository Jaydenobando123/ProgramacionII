package ec.edu.uce.dominio;

/**
 * Clase CuentaCorriente: representa una cuenta bancaria con capacidad de sobregiro.
 * Hereda de la clase Cuenta.
 */
public class CuentaCorriente extends Cuenta {

    private double sobregiro;

    // Constructor por defecto.
    public CuentaCorriente() {
        super();
        this.sobregiro = 0.0;
    }

    // Constructor que inicializa la cuenta con un saldo específico.
    public CuentaCorriente(double balance) {
        super(balance);
        this.sobregiro = 0.0;
    }

    // Constructor que inicializa saldo y límite de sobregiro.
    public CuentaCorriente(double balance, double sobregiro) {
        super(balance);
        setSobregiro(sobregiro);
    }

    public double getSobregiro() {
        return sobregiro;
    }

    public void setSobregiro(double sobregiro) {
        if (sobregiro >= 0) {
            this.sobregiro = sobregiro;
        } else {
            System.out.println("Advertencia: El límite de sobregiro no puede ser negativo. Se estableció en $0.00.");
            this.sobregiro = 0.0;
        }
    }

    // Realiza un depósito; el monto debe ser mayor a $1.00.
    @Override
    public boolean deposito(double monto) {
        if (monto <= 1.00) {
            return false;
        }
        this.balance += monto;
        return true;
    }

    // Realiza un retiro; permite sobregiro hasta el límite establecido.
    @Override
    public boolean retiro(double monto) {
        if (monto <= 0) {
            return false;
        }
        if (this.balance + this.sobregiro >= monto) {
            this.balance -= monto;
            return true;
        } else {
            System.out.println("Error: Fondos insuficientes (incluido sobregiro). Total disponible: $" + String.format("%.2f", this.balance + this.sobregiro));
            return false;
        }
    }

    @Override
    public double calculoInteres() {
        // Calcula interés por sobregiro si el balance es negativo.
        if (balance < 0) {
            return Math.abs(balance) * 0.10; // 10% de interés sobre el monto sobregirado.
        }
        return 0;
    }

    @Override
    public String descripcion() {
        return "Cuenta Corriente";
    }

    @Override
    public String toString() {
        return "Cuenta Corriente [Saldo actual: $" + String.format("%.2f", balance) +
                ", Sobregiro permitido: $" + String.format("%.2f", sobregiro) + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        CuentaCorriente otra = (CuentaCorriente) obj;

        if (Double.compare(balance, otra.balance) != 0) return false;
        if (Double.compare(sobregiro, otra.sobregiro) != 0) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(balance, sobregiro);
    }
}