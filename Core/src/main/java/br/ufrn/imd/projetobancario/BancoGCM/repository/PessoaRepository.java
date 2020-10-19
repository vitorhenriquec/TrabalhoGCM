package br.ufrn.imd.projetobancario.BancoGCM.repository;

import br.ufrn.imd.projetobancario.BancoGCM.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
