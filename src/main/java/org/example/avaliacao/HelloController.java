package org.example.avaliacao;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class HelloController {

    private final List<Conta> contas = new ArrayList<>();

    @FXML
    private TextField txtConta;

    @FXML
    private TextField txtTitular;

    @FXML
    private RadioButton rbCorrente;

    @FXML
    private RadioButton rbEspecial;

    @FXML
    private RadioButton rbPoupanca;

    @FXML
    private TextField txtLimite;

    @FXML
    private TextField txtVencimento;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnDepositar;

    @FXML
    private TextField txtValor;

    @FXML
    private Button btnSacar;

    @FXML
    private TextArea txtAreaDados;

    @FXML
    private TextField txtPesquisar;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Label lblSaldo;

    private Conta contaAtual;  // Usaremos esta variável para armazenar a conta atualmente selecionada para depósito, saque, etc.

    @FXML
    protected void onSelecionarTipo() {
        if (rbCorrente.isSelected()) {
            txtLimite.setDisable(true);
            txtVencimento.setDisable(true);
        } else if (rbEspecial.isSelected()) {
            txtLimite.setDisable(false);
            txtVencimento.setDisable(true);
        } else {
            txtLimite.setDisable(true);
            txtVencimento.setDisable(false);
        }
    }

    // Método para cadastrar uma nova conta
    @FXML
    protected void onCadastrarConta() {
        if (rbCorrente.isSelected()) {
            Conta conta = new Conta(Integer.parseInt(txtConta.getText()), txtTitular.getText());
            contas.add(conta);
        } else if (rbEspecial.isSelected()) {
            Especial contaEspecial = new Especial(Integer.parseInt(txtConta.getText()), txtTitular.getText(), Double.parseDouble(txtLimite.getText()));
            contas.add(contaEspecial);
        } else {
            Poupanca contaPoupanca = new Poupanca(Integer.parseInt(txtConta.getText()), txtTitular.getText(), Integer.parseInt(txtVencimento.getText()));
            contas.add(contaPoupanca);
        }

        atualizarAreaDados();
        limparCamposCadastro();
    }

    // Método para depositar dinheiro na conta selecionada
    @FXML
    protected void onDepositar() {
        if (contaAtual != null) {
            double valorDeposito = Double.parseDouble(txtValor.getText());
            contaAtual.depositar(valorDeposito);
            atualizarAreaDados(); // Atualiza a área de texto com os dados da conta
            lblSaldo.setText(String.format("Saldo: %.2f", contaAtual.getSaldo())); // Atualiza o saldo exibido no label
            limparCamposOperacao(); // Limpa os campos de operação
        } else {
            alert("Selecione uma conta primeiro!");
        }
    }

    // Método para sacar dinheiro da conta selecionada
    @FXML
    protected void onSacar() {
        if (contaAtual != null) {
            double valorSaque = Double.parseDouble(txtValor.getText());
            if (contaAtual.sacar(valorSaque)) {
                atualizarAreaDados(); // Atualiza a área de texto com os dados da conta
                lblSaldo.setText(String.format("Saldo: %.2f", contaAtual.getSaldo())); // Atualiza o saldo exibido no label
                limparCamposOperacao(); // Limpa os campos de operação
            } else {
                alert("Saldo insuficiente!");
            }
        } else {
            alert("Selecione uma conta primeiro!");
        }
    }

    // Método para pesquisar uma conta pelo número
    @FXML
    protected void onPesquisar() {
        int numeroConta = Integer.parseInt(txtPesquisar.getText());
        for (Conta conta : contas) {
            if (conta.getNumero().equals(numeroConta)) { // Comparando corretamente Integer com Integer
                contaAtual = conta;
                lblSaldo.setText(String.format("Saldo: %.2f", conta.getSaldo())); // Exibe o saldo da conta encontrada
                txtAreaDados.setText(conta.toString()); // Exibe os detalhes da conta encontrada
                return;
            }
        }
        alert("Conta não encontrada!");
    }

    // Atualiza a área de texto com as informações de todas as contas
    private void atualizarAreaDados() {
        StringBuilder dados = new StringBuilder();
        for (Conta conta : contas) {
            dados.append(conta.toString()).append("\n");
        }
        txtAreaDados.setText(dados.toString());
    }

    // Limpa os campos após o registro de uma conta
    private void limparCamposCadastro() {
        txtConta.setText("");
        txtTitular.setText("");
        txtLimite.setText("");
        txtVencimento.setText("");
        rbEspecial.setSelected(false);
        rbCorrente.setSelected(false);
        rbPoupanca.setSelected(false);
        lblSaldo.setText("Saldo: 0.00");
        txtConta.requestFocus();
    }

    // Limpa os campos após depósito/saque
    private void limparCamposOperacao() {
        txtValor.setText("");
        txtValor.requestFocus();
    }

    // Exibe um alerta na tela
    private void alert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
