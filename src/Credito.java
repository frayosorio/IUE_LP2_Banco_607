public class Credito extends Cuenta {

    private double limiteCredito;

    public Credito(String numero, String titular, double saldo, double limiteCredito) {
        super(numero, titular, saldo);
        this.limiteCredito = limiteCredito;
    }

    @Override
    public boolean retirar(double cantidad) {
        if (cantidad <= getSaldo() + limiteCredito) {
            setSaldo(getSaldo() - cantidad);
            return true;
        }
        return false;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    
}
