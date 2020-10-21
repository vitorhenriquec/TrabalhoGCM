package br.ufrn.imd.projetobancario.BancoGCM.operations;

import br.ufrn.imd.projetobancario.BancoGCM.domain.Conta;
import br.ufrn.imd.projetobancario.BancoGCM.exception.InvalidValueException;

import java.math.BigDecimal;

public class TransferenciaOnCommand implements Command{
    private final Conta conta;

    private final Conta contaDestino;

    private BigDecimal valor;

    public TransferenciaOnCommand(Conta conta, Conta contaDestino, BigDecimal valor) {
        this.conta = conta;
        this.contaDestino = contaDestino;
        this.valor = valor;
    }

    public void execute() throws InvalidValueException {
        DebitoOnCommand debitoOnCommand = new DebitoOnCommand(this.conta, this.valor);
        debitoOnCommand.execute();
        CreditoOnCommand creditoOnCommand = new CreditoOnCommand(this.contaDestino, this.valor);
        creditoOnCommand.execute();
    }
}
