package br.ufrn.imd.projetobancario.BancoGCM.repository;

import br.ufrn.imd.projetobancario.BancoGCM.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
