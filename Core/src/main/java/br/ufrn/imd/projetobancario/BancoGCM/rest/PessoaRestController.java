package br.ufrn.imd.projetobancario.BancoGCM.rest;

import br.ufrn.imd.projetobancario.BancoGCM.mapper.PessoaDTOMapper;
import br.ufrn.imd.projetobancario.BancoGCM.domain.Pessoa;
import br.ufrn.imd.projetobancario.BancoGCM.dto.PessoaDTO;
import br.ufrn.imd.projetobancario.BancoGCM.exception.ResourceNotFoundException;
import br.ufrn.imd.projetobancario.BancoGCM.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pessoa")
@RequiredArgsConstructor
public class PessoaRestController {
    private final PessoaService pessoaService;

    @GetMapping
    public List<PessoaDTO> findAll() {
        return this.pessoaService.findAll().stream().map(PessoaDTOMapper::map).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public PessoaDTO findOne(@PathVariable(name = "id") Long idPessoa) throws ResourceNotFoundException {
        return PessoaDTOMapper.map(this.pessoaService.findOne(idPessoa));
    }

    @PostMapping
    public Long save(@RequestBody Pessoa pessoa) throws ResourceNotFoundException {
        return this.pessoaService.save(pessoa).getId();
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") Long idPessoa) throws ResourceNotFoundException {
        this.pessoaService.delete(idPessoa);
    }
}
