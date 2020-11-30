package br.ufrn.imd.projetobancario.BancoGCM.operations;

import br.ufrn.imd.projetobancario.BancoGCM.domain.Conta;
import lombok.Getter;

import java.math.BigDecimal;

public class SaldoOnCommand implements Command {
    private final Conta conta;
    @Getter
    private BigDecimal saldo;

    public SaldoOnCommand(Conta conta) {
        this.conta = conta;
    }

    @Override
    public void execute() {
        this.saldo = this.conta.getSaldo();
    }
}
