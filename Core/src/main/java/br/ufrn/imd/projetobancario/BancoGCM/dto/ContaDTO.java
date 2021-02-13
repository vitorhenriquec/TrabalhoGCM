package br.ufrn.imd.projetobancario.BancoGCM.dto;

import br.ufrn.imd.projetobancario.BancoGCM.tipos.TipoConta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ContaDTO {
    private Long id;
    private TipoConta tipoConta;
    private BigDecimal saldo;
    private BigDecimal saldoBonus;
    private PessoaDTO pessoa;
}
