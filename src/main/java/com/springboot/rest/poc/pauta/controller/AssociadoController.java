package com.springboot.rest.poc.pauta.controller;


import java.io.IOException;
import java.util.Optional;

import com.springboot.rest.poc.pauta.entity.Associado;
import com.springboot.rest.poc.pauta.service.AssociadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.springboot.rest.poc.pauta.repository.AssociadoRepository;


@RestController
@RequestMapping("")
public class AssociadoController {

    @Autowired
    AssociadoRepository associadoRepository;

    @Autowired
    AssociadoService associadoService;

    @RequestMapping(method = RequestMethod.GET, path = "/associados")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Associado> getAssociados(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "10") int page_results) {
        PageRequest pageRequest = PageRequest.of(page, page_results, Sort.unsorted());
        return associadoRepository.findAll(pageRequest);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/associado")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Associado> getAssociadoByCpf(@RequestParam("cpf") String cpf) {
        return associadoRepository.findByCpf(cpf);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/associados/consultaCpf")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getValidateCPF(@RequestParam("cpf") String cpf) throws IOException {
        return associadoService.validarCPFAssociado(cpf);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/associados", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> postAssociado(@RequestBody Associado associado) throws Exception {
        return associadoService.cadastrarAssociado(associado);
    }


}