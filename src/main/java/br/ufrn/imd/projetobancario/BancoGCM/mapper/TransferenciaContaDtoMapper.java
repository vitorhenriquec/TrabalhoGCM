package br.ufrn.imd.projetobancario.BancoGCM.mapper;

import lombok.Getter;

import java.math.BigDecimal;

public class TransferenciaContaDtoMapper {
    @Getter
    public Long idConta;
    @Getter
    public Long idContaDestino;
    @Getter
    public BigDecimal valor;
}
