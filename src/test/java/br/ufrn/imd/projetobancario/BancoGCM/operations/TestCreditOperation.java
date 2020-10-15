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
class TestCreditOperation {
    @Autowired
    private ContaService contaService;

    @Autowired
    private PessoaService pessoaService;

    private Conta conta;

    private Pessoa pessoa;

    public TestCreditOperation(){

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
    }

    /**
     * Teste de creditar um valor em uma conta válida
     * @throws ResourceNotFoundException - Se não achar a pessoa ou conta
     * @throws InvalidValueException - Se o valor não for aceitável
     */
    @Test
    public void creditandoValorContaValida() throws ResourceNotFoundException, InvalidValueException {
        BigDecimal valor = new BigDecimal(1000);
        this.contaService.credit(this.conta.getId(), valor);
        this.conta = this.contaService.findOne(this.conta.getId());
        Assertions.assertEquals(new BigDecimal(9000).doubleValue(), this.conta.getSaldo().doubleValue());
    }

    /**
     * Teste para creditar um valor em uma conta inválida
     */
    @Test
    public void creditantoValorContaInvalida() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            BigDecimal valor = new BigDecimal(1000);
            this.contaService.credit( 55L, valor);
        });
    }

    /**
     * Teste de debitar um valor negativo
     */
    @Test
    public void creditandoValorNegativo() {
        Assertions.assertThrows(InvalidValueException.class, () -> {
            BigDecimal valor = new BigDecimal(-800);
            this.contaService.credit(this.conta.getId(), valor);
        });
    }
}