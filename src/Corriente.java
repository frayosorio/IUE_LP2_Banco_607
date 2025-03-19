public class Corriente extends Cuenta {

    private double sobregiro;

    public Corriente(String numero, String titular, double saldo, double sobregiro) {
        super(numero, titular, saldo);
        this.sobregiro = sobregiro;
    }

    @Override
    public boolean retirar(double cantidad) {
        if (cantidad <= getSaldo() + sobregiro) {
            setSaldo(getSaldo() - cantidad);
            return true;
        }
        return false;
    }

    public double getSobregiro() {
        return sobregiro;
    }

}
