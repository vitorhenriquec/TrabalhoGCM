package br.ufrn.imd.projetobancario.BancoGCM.operations;

import br.ufrn.imd.projetobancario.BancoGCM.core.BancoGcmApplication;
import br.ufrn.imd.projetobancario.BancoGCM.domain.Conta;
import br.ufrn.imd.projetobancario.BancoGCM.domain.Pessoa;
import br.ufrn.imd.projetobancario.BancoGCM.exception.InvalidValueException;
import br.ufrn.imd.projetobancario.BancoGCM.exception.ResourceNotFoundException;
import br.ufrn.imd.projetobancario.BancoGCM.service.ContaService;
import br.ufrn.imd.projetobancario.BancoGCM.service.PessoaService;
import org.junit.jupiter.api.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootTest(classes = {BancoGcmApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestDebitOperation {

    @Autowired
    private ContaService contaService;

    @Autowired
    private PessoaService pessoaService;

    private Conta conta;

    private Pessoa pessoa;

    public TestDebitOperation() {
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
        pessoa.setNome("Aroldo Felix");
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
     * Teste de debitar um valor aceitável
     *
     * @throws ResourceNotFoundException - Se não achar a conta
     * @throws InvalidValueException     - Se o valor não for aceitável
     */
    @Test
    public void debitandoValorAceitavel() throws ResourceNotFoundException, InvalidValueException {
        BigDecimal valor = new BigDecimal(800);
        this.contaService.debit(this.conta.getId(), valor);
        this.conta = this.contaService.findOne(this.conta.getId());
        Assertions.assertEquals(new BigDecimal(7200).doubleValue(),this.conta.getSaldo().doubleValue());
    }

    /**
     * Teste de debitar um valor negativo
     */
    @Test
    public void debitandoValorNegativo() {
        Assertions.assertThrows(InvalidValueException.class, () -> {
            BigDecimal valor = new BigDecimal(-800);
            this.contaService.debit(this.conta.getId(), valor);
        });
    }

    /**
     * Teste de debitar um valor maior que o saldo
     */
    @Test
    public void debitandoValorMaiorQueSaldo() {
        Assertions.assertThrows(InvalidValueException.class, () -> {
            BigDecimal valor = new BigDecimal(120000);
            this.contaService.debit(this.conta.getId(), valor);
        });
    }

    /**
     * Remover a entidade que populou no setUp()
     *
     * @throws ResourceNotFoundException - Se não achar a pessoa ou conta
     */
    @AfterEach
    public void tearDown() throws ResourceNotFoundException {
        this.contaService.delete(this.conta.getId());
        this.pessoaService.delete(this.pessoa.getId());
    }
}
