package br.ufrn.imd.projetobancario.BancoGCM.service;

import br.ufrn.imd.projetobancario.BancoGCM.domain.Conta;
import br.ufrn.imd.projetobancario.BancoGCM.domain.Pessoa;
import br.ufrn.imd.projetobancario.BancoGCM.exception.InvalidValueException;
import br.ufrn.imd.projetobancario.BancoGCM.exception.ResourceNotFoundException;
import br.ufrn.imd.projetobancario.BancoGCM.operations.CreditoOnCommand;
import br.ufrn.imd.projetobancario.BancoGCM.operations.SaldoOnCommand;
import br.ufrn.imd.projetobancario.BancoGCM.repository.ContaRepository;
import br.ufrn.imd.projetobancario.BancoGCM.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContaService {
    private final ContaRepository contaRepository;
    private final PessoaRepository pessoaRepository;

    public Conta findOne(Long id) throws ResourceNotFoundException {
        return this.contaRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public List<Conta> findAll() {
        return this.contaRepository.findAll();
    }

    @Transactional
    public Conta save(Conta conta) throws ResourceNotFoundException {
        Conta contaBD = new Conta();

        if (conta.getId() != null) {
            contaBD = this.findOne(conta.getId());
        } else {
            Pessoa pessoa = this.pessoaRepository.findById(conta.getPessoa().getId()).orElseThrow(ResourceNotFoundException::new);
            contaBD.setPessoa(pessoa);
        }

        BeanUtils.copyProperties(conta, contaBD, Conta.ignoreProperties);

        return this.contaRepository.save(contaBD);
    }

    @Transactional
    public void delete(Long id) throws ResourceNotFoundException {
        Conta conta = this.findOne(id);
        this.contaRepository.delete(conta);
    }

    @Transactional
    public void credit(Long id, BigDecimal value) throws ResourceNotFoundException, InvalidValueException {
        if (value.compareTo(BigDecimal.ZERO) == -1) {
            throw new InvalidValueException();
        } else if (value.compareTo(BigDecimal.ZERO) == 1) {
            Conta conta = this.findOne(id);

            CreditoOnCommand command = new CreditoOnCommand(conta, value);
            command.execute();
            conta.setSaldo(command.getValor());
            this.save(conta);
        }
    }

    public BigDecimal getSaldo(Long id) throws ResourceNotFoundException {
        Conta conta = this.findOne(id);

        SaldoOnCommand command = new SaldoOnCommand(conta);
        command.execute();

        return command.getSaldo();

    }
}
