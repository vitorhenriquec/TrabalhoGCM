package br.ufrn.imd.projetobancario.BancoGCM.operations;

import br.ufrn.imd.projetobancario.BancoGCM.domain.Conta;

import java.math.BigDecimal;

public class DebitoOnCommand implements Command{
    private final Conta conta;
    private BigDecimal valor;

    public DebitoOnCommand(Conta conta, BigDecimal valor) {
        this.conta = conta;
        this.valor = valor;
    }

    @Override
    public void execute() {
        this.conta.setSaldo(conta.getSaldo().subtract(this.valor));
    }
}
