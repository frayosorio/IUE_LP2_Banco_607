public class Ahorros extends Cuenta {

    private double TASA_INTERES = 0.015;

    public Ahorros(String numero, String titular, double saldo) {
        super(numero, titular, saldo);
    }

    @Override
    public boolean retirar(double cantidad) {
        if (cantidad <= getSaldo()) {
            setSaldo(getSaldo() - cantidad);
            return true;
        }
        return false;
    }

    public void aplicarInteres() {
        setSaldo(getSaldo() * (1 + TASA_INTERES));
    }

}
