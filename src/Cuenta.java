public abstract class Cuenta {

    private String numero;
    private String titular;
    private double saldo;
    
    public Cuenta(String numero, String titular, double saldo) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldo;
    }

    public String getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    //metodo protegido
    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    //metodo abstracto (polimorfismo)
    public abstract boolean retirar(double cantidad);

    public void  depositar(double cantidad){
        if(cantidad>0){
            saldo +=cantidad;
        }
    }
}
