package br.ufrn.imd.projetobancario.BancoGCM.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pessoa")
@EqualsAndHashCode(of = {"id"})
public class Pessoa {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_nascimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataNascimento = new Date();

    @Column(name = "data_criacao")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date dataCriacao = new Date();

    @Column(name = "data_ultima_edicao")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date dataUltimaEdicao = new Date();

    @Column(name = "versao")
    @Version
    private Long versao = 0L;

    @OneToMany(mappedBy = "pessoa")
    private List<Conta> contas;
}
