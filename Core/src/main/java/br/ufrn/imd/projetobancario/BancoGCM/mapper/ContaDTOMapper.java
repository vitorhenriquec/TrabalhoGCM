package br.ufrn.imd.projetobancario.BancoGCM.mapper;

import br.ufrn.imd.projetobancario.BancoGCM.domain.Conta;
import br.ufrn.imd.projetobancario.BancoGCM.dto.ContaDTO;

public class ContaDTOMapper {
    public static ContaDTO map(Conta conta) {
        ContaDTO contaDTO = new ContaDTO();

        contaDTO.setId(conta.getId());
        contaDTO.setTipoConta(conta.getTipoConta());
        contaDTO.setSaldo(conta.getSaldo());
        contaDTO.setSaldoBonus(conta.getSaldoBonus());
        contaDTO.setPessoa(PessoaDTOMapper.map(conta.getPessoa()));

        return contaDTO;
    }
}
