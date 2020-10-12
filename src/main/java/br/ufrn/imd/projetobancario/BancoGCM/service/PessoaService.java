package br.ufrn.imd.projetobancario.BancoGCM.service;

import br.ufrn.imd.projetobancario.BancoGCM.domain.Pessoa;
import br.ufrn.imd.projetobancario.BancoGCM.exception.ResourceNotFoundException;
import br.ufrn.imd.projetobancario.BancoGCM.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    public Pessoa findOne(Long id) throws ResourceNotFoundException {
        return this.pessoaRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public List<Pessoa> findAll() {
        return this.pessoaRepository.findAll();
    }

    @Transactional
    public Pessoa save(Pessoa pessoa) throws ResourceNotFoundException {
        Pessoa pessoaBD = new Pessoa();

        if (pessoa.getId() != null) {
            pessoaBD = this.findOne(pessoa.getId());
        }

        BeanUtils.copyProperties(pessoa, pessoaBD, Pessoa.ignoreProperties);

        return this.pessoaRepository.save(pessoaBD);
    }

    @Transactional
    public void delete(Long id) throws ResourceNotFoundException {
        Pessoa pessoa = this.findOne(id);
        this.pessoaRepository.delete(pessoa);
    }
}
