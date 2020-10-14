package br.ufrn.imd.projetobancario.BancoGCM.rest;

import br.ufrn.imd.projetobancario.BancoGCM.domain.Conta;
import br.ufrn.imd.projetobancario.BancoGCM.dto.ContaDTO;
import br.ufrn.imd.projetobancario.BancoGCM.exception.InvalidValueException;
import br.ufrn.imd.projetobancario.BancoGCM.exception.ResourceNotFoundException;
import br.ufrn.imd.projetobancario.BancoGCM.mapper.ContaDTOMapper;
import br.ufrn.imd.projetobancario.BancoGCM.mapper.TransferenciaContaDtoMapper;
import br.ufrn.imd.projetobancario.BancoGCM.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/conta")
@RequiredArgsConstructor
public class ContaRestController {
    private final ContaService contaService;

    @GetMapping
    public List<ContaDTO> findAll() {
        return this.contaService.findAll().stream().map(ContaDTOMapper::map).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public ContaDTO findOne(@PathVariable(name = "id") Long idConta) throws ResourceNotFoundException {
        return ContaDTOMapper.map(this.contaService.findOne(idConta));
    }

    @GetMapping(path = "/{id}/recuperar-saldo")
    public BigDecimal getSaldo(@PathVariable(name = "id") Long idConta) throws ResourceNotFoundException {
        return this.contaService.getSaldo(idConta);
    }

    @PostMapping
    public Long save(@RequestBody Conta conta) throws ResourceNotFoundException {
        return this.contaService.save(conta).getId();
    }

    @PutMapping(path = "/{id}/debitar")
    public void debit(@PathVariable(name = "id") Long idConta, @RequestParam(name = "valor") BigDecimal value) throws ResourceNotFoundException, InvalidValueException {
        this.contaService.debit(idConta, value);
    }

    @PutMapping(path = "/{id}/creditar")
    public void credit(@PathVariable(name = "id") Long idConta, @RequestParam(name = "valor") BigDecimal value) throws ResourceNotFoundException, InvalidValueException {
        this.contaService.credit(idConta, value);
    }

    @PostMapping(path = "/transferir")
    public void transfer(@RequestBody TransferenciaContaDtoMapper transferenciaConta) throws ResourceNotFoundException, InvalidValueException {
        this.contaService.transfer(transferenciaConta.getIdConta(), transferenciaConta.getIdContaDestino(), transferenciaConta.getValor());
    }


    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") Long idConta) throws ResourceNotFoundException {
        this.contaService.delete(idConta);
    }
}
