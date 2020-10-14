package br.ufrn.imd.projetobancario.BancoGCM.operations;

import br.ufrn.imd.projetobancario.BancoGCM.domain.Conta;
import lombok.Getter;

import java.math.BigDecimal;

public class CreditoOnCommand implements Command{
    private final Conta conta;

    @Getter
    private BigDecimal valor;

    public CreditoOnCommand(Conta conta, BigDecimal valor) {
        this.conta = conta;
        this.valor = valor;
    }

    @Override
    public void execute() {
        this.valor = conta.getSaldo().add(this.valor);
    }
}
