package br.ufrn.imd.projetobancario.BancoGCM.mapper;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class TransferenciaContaDtoMapper {
    public Long idConta;
    public Long idContaDestino;
    public BigDecimal valor;
}
