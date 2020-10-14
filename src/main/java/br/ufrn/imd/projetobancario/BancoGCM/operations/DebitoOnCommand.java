package br.ufrn.imd.projetobancario.BancoGCM.operations;

import br.ufrn.imd.projetobancario.BancoGCM.domain.Conta;
import lombok.Getter;

import java.math.BigDecimal;

public class DebitoOnCommand implements Command{
    private final Conta conta;

    @Getter
    private BigDecimal valor;

    public DebitoOnCommand(Conta conta, BigDecimal valor) {
        this.conta = conta;
        this.valor = valor;
    }

    @Override
    public void execute() {
        this.valor = conta.getSaldo().subtract(this.valor);
    }
}
