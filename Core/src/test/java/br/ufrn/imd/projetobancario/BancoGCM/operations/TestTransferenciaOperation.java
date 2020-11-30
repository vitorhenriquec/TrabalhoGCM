package br.ufrn.imd.projetobancario.BancoGCM.operations;

import br.ufrn.imd.projetobancario.BancoGCM.core.BancoGcmApplication;
import br.ufrn.imd.projetobancario.BancoGCM.domain.Conta;
import br.ufrn.imd.projetobancario.BancoGCM.domain.Pessoa;
import br.ufrn.imd.projetobancario.BancoGCM.exception.InvalidValueException;
import br.ufrn.imd.projetobancario.BancoGCM.exception.ResourceNotFoundException;
import br.ufrn.imd.projetobancario.BancoGCM.service.ContaService;
import br.ufrn.imd.projetobancario.BancoGCM.service.PessoaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootTest(classes = {BancoGcmApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestTransferenciaOperation {
    @Autowired
    private ContaService contaService;

    @Autowired
    private PessoaService pessoaService;

    private Conta conta;

    private Conta contaDestino;

    private Pessoa pessoa;

    private Pessoa pessoaDestino;

    public TestTransferenciaOperation(){

    }

    /**
     * Preparação das entidades de teste
     *
     * @throws ResourceNotFoundException - Se não achar a pessoa ou conta
     */
    @BeforeEach
    private void setUp() throws ResourceNotFoundException {
        MockitoAnnotations.initMocks(this);
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Maria Rayane Alves");
        pessoa.setDataNascimento(new Date());

        pessoa = this.pessoaService.save(pessoa);
        this.pessoa = this.pessoaService.findOne(pessoa.getId());

        Conta conta = new Conta();
        conta.setPessoa(pessoa);
        conta.setSaldo(new BigDecimal(8000));

        conta = this.contaService.save(conta);
        this.conta = this.contaService.findOne(conta.getId());

        Pessoa pessoaDestino = new Pessoa();
        pessoaDestino.setNome("Vitor Henrique");
        pessoaDestino.setDataNascimento(new Date());

        pessoaDestino = this.pessoaService.save(pessoaDestino);
        this.pessoaDestino = this.pessoaService.findOne(pessoaDestino.getId());

        Conta contaDestino = new Conta();
        contaDestino.setPessoa(pessoaDestino);
        contaDestino.setSaldo(new BigDecimal(6000));

        contaDestino = this.contaService.save(contaDestino);
        this.contaDestino = this.contaService.findOne(contaDestino.getId());
    }

    /**
     * Teste de transferir um valor aceitável
     *
     * @throws ResourceNotFoundException - Se não achar a conta
     * @throws InvalidValueException     - Se o valor não for aceitável
     */
    @Test
    public void transferindoValorAceitavel() throws ResourceNotFoundException, InvalidValueException {
        BigDecimal valor = new BigDecimal(800);
        this.contaService.transfer(this.conta.getId(), this.contaDestino.getId(), valor);

        this.conta = this.contaService.findOne(this.conta.getId());
        this.contaDestino = this.contaService.findOne( this.contaDestino.getId() );

        BigDecimal newValueConta = new BigDecimal(7200);
        BigDecimal newValueContaDestino = new BigDecimal(6800);

        Assertions.assertEquals(newValueConta.doubleValue(), this.conta.getSaldo().doubleValue());
        Assertions.assertEquals(newValueContaDestino.doubleValue(), this.contaDestino.getSaldo().doubleValue());
    }


    /**
     * Teste de transferir um valor para uma conta inexistente
     */
    @Test
    public void transferirValorParaContaInexistente() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            BigDecimal valor = new BigDecimal(800);
            this.contaService.transfer(this.conta.getId(), 77L, valor);
        });
    }

    /**
     * Teste de transferir um valor de uma conta inexistente
     */
    @Test
    public void transferirValorDeContaInexistente() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            BigDecimal valor = new BigDecimal(800);
            this.contaService.transfer(88L, this.contaDestino.getId(), valor);
        });
    }

    /**
     * Teste de transferir um valor negativo
     */
    @Test
    public void transferirValorNegativo() {
        Assertions.assertThrows(InvalidValueException.class, () -> {
            BigDecimal valor = new BigDecimal(-800);
            this.contaService.transfer(this.conta.getId(), this.contaDestino.getId(), valor);
        });
    }
}