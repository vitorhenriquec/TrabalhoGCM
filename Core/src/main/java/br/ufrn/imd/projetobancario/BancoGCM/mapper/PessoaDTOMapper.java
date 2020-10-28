package br.ufrn.imd.projetobancario.BancoGCM.mapper;

import br.ufrn.imd.projetobancario.BancoGCM.domain.Pessoa;
import br.ufrn.imd.projetobancario.BancoGCM.dto.PessoaDTO;

public class PessoaDTOMapper {
    public static PessoaDTO map(Pessoa pessoa) {
        PessoaDTO pessoaDTO = new PessoaDTO();

        pessoaDTO.setId(pessoa.getId());
        pessoaDTO.setNome(pessoa.getNome());
        pessoaDTO.setDataNascimento(pessoa.getDataNascimento());

        return pessoaDTO;
    }
}
