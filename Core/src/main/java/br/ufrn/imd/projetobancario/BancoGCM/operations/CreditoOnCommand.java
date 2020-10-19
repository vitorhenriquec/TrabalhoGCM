package br.ufrn.imd.projetobancario.BancoGCM.operations;

import br.ufrn.imd.projetobancario.BancoGCM.domain.Conta;
import br.ufrn.imd.projetobancario.BancoGCM.exception.InvalidValueException;

import java.math.BigDecimal;

public class CreditoOnCommand implements Command{
    private final Conta conta;
    private BigDecimal valor;

    public CreditoOnCommand(Conta conta, BigDecimal valor) {
        this.conta = conta;
        this.valor = valor;
    }

    @Override
    public void execute() throws  InvalidValueException{
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidValueException();
        } else if (valor.compareTo(BigDecimal.ZERO) > 0) {
            this.conta.setSaldo(this.conta.getSaldo().add(this.valor));
        }
    }
}
