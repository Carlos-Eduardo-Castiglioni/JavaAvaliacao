package org.example.avaliacao;

public class Poupanca extends Conta {
    private Integer aniversario;

    public Poupanca(Integer numero, String titular, Integer aniversario) {
        super(numero, titular);
        this.aniversario = aniversario;
    }

    public Integer getAniversario() {
        return aniversario;
    }

    public void setAniversario(Integer aniversario) {
        this.aniversario = aniversario;
    }

    @Override
    public String toString() {
        return "Poupança{" +
                "numero=" + this.getNumero() +
                ", titular='" + this.getTitular() + '\'' +
                ", aniversario=" + aniversario +
                ", saldo=" + this.getSaldo() + System.lineSeparator();
    }
}
