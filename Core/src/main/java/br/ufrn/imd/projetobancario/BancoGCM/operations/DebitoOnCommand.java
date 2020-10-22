package br.ufrn.imd.projetobancario.BancoGCM.operations;

import br.ufrn.imd.projetobancario.BancoGCM.domain.Conta;
import br.ufrn.imd.projetobancario.BancoGCM.exception.InvalidValueException;

import java.math.BigDecimal;

public class DebitoOnCommand implements Command {
    private final Conta conta;
    private BigDecimal valor;

    public DebitoOnCommand(Conta conta, BigDecimal valor) {
        this.conta = conta;
        this.valor = valor;
    }

    @Override
    public void execute() throws InvalidValueException {
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidValueException();
        } else if (valor.compareTo(BigDecimal.ZERO) > 0) {
            SaldoOnCommand saldoOnCommand = new SaldoOnCommand(conta);
            saldoOnCommand.execute();
            // Se o valor a ser debitado for maior que o saldo
            if (saldoOnCommand.getSaldo().compareTo(valor) < 0) {
                throw new InvalidValueException();
            }
            this.conta.setSaldo(conta.getSaldo().subtract(this.valor));
        }
    }
}
