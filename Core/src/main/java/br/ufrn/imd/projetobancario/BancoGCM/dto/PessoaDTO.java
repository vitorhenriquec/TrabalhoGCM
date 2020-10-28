package br.ufrn.imd.projetobancario.BancoGCM.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PessoaDTO extends TipoDTO {
    private Date dataNascimento;
}
