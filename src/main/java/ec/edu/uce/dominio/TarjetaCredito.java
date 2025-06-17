package ec.edu.uce.dominio;

public class TarjetaCredito extends Cuenta {
    private double cupo;        // Crédito disponible (es el cupo disponible actual)
    private double cupoMaximo;  // Límite máximo de crédito (el cupo total que puede tener)

    public TarjetaCredito() {
        super(); // Llama al constructor de Cuenta para inicializar balance en 0.0
        this.cupoMaximo = 1000; // ejemplo límite máximo de crédito
        this.cupo = cupoMaximo; // inicialmente todo el cupo está disponible
    }

    public TarjetaCredito(double saldo, double cupoMaximo) {
        super(saldo); // Inicializa el saldo/balance de la superclase
        this.cupoMaximo = cupoMaximo;
        this.cupo = cupoMaximo; // Al inicio, el cupo disponible es el máximo
    }

    public double getCupo() {
        return cupo;
    }

    public void setCupo(double cupo) {
        // Asegura que el cupo disponible no sea negativo y no exceda el cupo máximo
        if (cupo < 0) {
            this.cupo = 0;
        } else if (cupo > cupoMaximo) {
            this.cupo = cupoMaximo; // El cupo disponible no puede exceder el máximo
        } else {
            this.cupo = cupo;
        }
    }

    public double getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(double cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
        // Si el cupo disponible actual es mayor que el nuevo cupo máximo, se ajusta
        if (this.cupo > cupoMaximo) {
            this.cupo = cupoMaximo;
        }
    }

    @Override
    public boolean deposito(double monto) {
        if (monto > 0) {
            // Calcula cuánto de la deuda (cupoMaximo - cupo) se tiene
            double deuda = cupoMaximo - cupo;
            if (deuda < 0) { // Si el cupo actual es mayor al cupoMaximo, significa que no hay deuda de cupo
                deuda = 0;
            }

            // El pago se usa primero para "restaurar" el cupo
            double pagoAlCupo = Math.min(monto, deuda);
            double nuevoCupo = cupo + pagoAlCupo;
            setCupo(nuevoCupo); // Usa el setter para aplicar validaciones

            // Si aún queda dinero, se añade al saldo (balance) de la cuenta
            double restante = monto - pagoAlCupo;
            if (restante > 0) {
                double nuevoBalance = getBalance() + restante;
                setBalance(nuevoBalance);
            }
            return true; // Asumiendo que el depósito fue exitoso si monto > 0
        }
        return false;
    }

    @Override
    public boolean retiro(double monto) {
        if (monto > 0) {
            // El dinero disponible para gastar es el saldo actual + el cupo disponible
            double disponibleTotal = getBalance() + cupo;

            if (monto <= disponibleTotal) {
                if (monto <= getBalance()) {
                    // Si el monto es menor o igual al saldo, se resta directamente del saldo
                    double nuevoBalance = getBalance() - monto;
                    setBalance(nuevoBalance);
                } else {
                    // Si el monto es mayor que el saldo, se agota el saldo y se usa el cupo
                    double restante = monto - getBalance();
                    setBalance(0); // El saldo se vuelve cero

                    double nuevoCupo = cupo - restante; // Se reduce el cupo disponible
                    setCupo(nuevoCupo); // Usa el setter para aplicar validaciones
                }
                return true; // Retiro exitoso
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public double calculoInteres() {
        // El crédito usado es lo que se ha gastado del cupo máximo
        double creditoUsado = cupoMaximo - cupo;
        if (creditoUsado <= 0) {
            return 0; // Si no hay crédito usado, no hay interés
        }

        double interes = creditoUsado * 0.05; // Calcula el interés (ej. 5%)

        // El interés se suma a la "deuda", lo que reduce el cupo disponible
        double nuevoCupo = cupo - interes;
        setCupo(nuevoCupo); // Usa el setter para aplicar validaciones

        return interes; // Retorna el monto del interés calculado
    }

    @Override
    public String toString() {
        // Formato para mostrar el saldo (balance de Cuenta), el cupo disponible y el cupo máximo
        return String.format("TarjetaCredito: [saldo actual : %.2f] [cupo disponible: %.2f / %.2f]", getBalance(), cupo, cupoMaximo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        // Verifica si el objeto no es nulo y si es una instancia de TarjetaCredito
        if (!(obj instanceof TarjetaCredito)) return false;

        TarjetaCredito otra = (TarjetaCredito) obj;

        // Compara el balance de la superclase y los atributos de TarjetaCredito
        if (Double.compare(this.getBalance(), otra.getBalance()) != 0) return false;
        if (Double.compare(this.cupo, otra.cupo) != 0) return false;
        if (Double.compare(this.cupoMaximo, otra.cupoMaximo) != 0) return false;
        return true;
    }

    @Override
    public int hashCode() {
        // Genera un hash basado en los atributos relevantes para equals
        return java.util.Objects.hash(getBalance(), cupo, cupoMaximo);
    }

    @Override
    public String descripcion() {
        // Descripción del tipo de cuenta
        return "Tarjeta de Crédito";
    }
}