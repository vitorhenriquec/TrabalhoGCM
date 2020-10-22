package br.ufrn.imd.projetobancario.BancoGCM.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class TransferenciaContaDto {
    public Long idConta;
    public Long idContaDestino;
    public BigDecimal valor;
}
