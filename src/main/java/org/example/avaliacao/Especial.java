package org.example.avaliacao;

public class Especial extends Conta {
    private Double limite;

    public Especial(Integer numero, String titular, Double limite) {
        super(numero, titular);
        this.limite = limite;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    @Override
    public Boolean sacar(Double valor) {
        // Permite saque até o saldo + limite
        if (valor <= this.getSaldo() + this.limite) {
            this.setSaldo(this.getSaldo() - valor);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Especial{" +
                "numero=" + getNumero() +
                ", titular='" + getTitular() + '\'' +
                ", limite=" + limite +
                ", saldo=" + getSaldo() +
                '}' + System.lineSeparator();
    }
}



