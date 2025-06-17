package ec.edu.uce.dominio;

/**
 * Clase abstracta Cuenta, representa la base para cualquier tipo de cuenta bancaria.
 * Define un comportamiento común para depositar y retirar, pero deja su implementación
 * específica a las clases que hereden de ella (ej. CuentaAhorro, CuentaCorriente).
 * Autor: Jayden Obando
 */
public abstract class Cuenta {

    /**
     * Balance actual de la cuenta.
     */
    protected double balance; // Protegido para que las subclases puedan acceder directamente

    /**
     * Constructor por defecto.
     * Inicializa el balance en cero.
     */
    public Cuenta() {
        this.balance = 0.0;
    }

    /**
     * Constructor que permite inicializar la cuenta con un balance específico.
     * @param balance Saldo inicial de la cuenta.
     */
    public Cuenta(double balance) {
        this.balance = balance;
    }

    /**
     * Obtiene el balance actual de la cuenta.
     * @return El balance de la cuenta.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Establece un nuevo balance para la cuenta.
     * @param balance Nuevo balance.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Método abstracto para realizar un depósito en la cuenta.
     * La implementación varía según el tipo de cuenta.
     * @param monto Monto a depositar.
     * @return true si el depósito fue exitoso, false en caso contrario.
     */
    public abstract boolean deposito(double monto);

    /**
     * Método abstracto para realizar un retiro de la cuenta.
     * La implementación varía según el tipo de cuenta.
     * @param monto Monto a retirar.
     * @return true si el retiro fue exitoso, false en caso contrario.
     */
    public abstract boolean retiro(double monto);

    /**
     * Método abstracto para calcular intereses o alguna métrica financiera de la cuenta.
     * @return El monto del cálculo (ej. interés).
     */
    public abstract double calculoInteres(); // ¡NUEVO MÉTODO ABSTRACTO!

    /**
     * Método abstracto para obtener una descripción del tipo de cuenta.
     * @return Una cadena con la descripción de la cuenta.
     */
    public abstract String descripcion();

    /**
     * Representación en cadena de la cuenta, que será sobrescrita por las subclases.
     * @return Cadena con la información de la cuenta.
     */
    @Override
    public String toString() {
        return "Cuenta [Balance actual: $" + String.format("%.2f", balance) + "]";
    }

    /**
     * Método abstracto para comparar si dos objetos Cuenta son iguales.
     * @param obj El objeto a comparar.
     * @return true si son iguales, false en caso contrario.
     */
    @Override
    public abstract boolean equals(Object obj);

    /**
     * Método abstracto para generar el valor hash de la cuenta.
     * @return El valor hash.
     */
    @Override
    public abstract int hashCode();
}