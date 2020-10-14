package br.ufrn.imd.projetobancario.BancoGCM.operations;

import br.ufrn.imd.projetobancario.BancoGCM.domain.Conta;

import java.math.BigDecimal;

public class CreditoOnCommand implements Command{
    private final Conta conta;
    private BigDecimal valor;

    public CreditoOnCommand(Conta conta, BigDecimal valor) {
        this.conta = conta;
        this.valor = valor;
    }

    @Override
    public void execute() {
        this.conta.setSaldo(this.conta.getSaldo().add(this.valor));
    }
}
