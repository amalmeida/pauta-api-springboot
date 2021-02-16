package com.springboot.rest.poc.pauta.controller;

import com.springboot.rest.poc.pauta.entity.Sessao;
import com.springboot.rest.poc.pauta.repository.PautaRepository;
import com.springboot.rest.poc.pauta.repository.SessaoVotacaoRepository;
import com.springboot.rest.poc.pauta.service.SessaoVotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("")
public class SessaoVotacaoController {

    @Autowired
    SessaoVotacaoService sessaoVotacaoService;

    @Autowired
    SessaoVotacaoRepository sessaoVotacaoRepository;

    @Autowired
    PautaRepository pautaRepository;


    @RequestMapping(method = RequestMethod.GET, path = "/Sessoes")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Sessao> getSessoes(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "10") int page_results) {
        PageRequest pageRequest = PageRequest.of(page, page_results, Sort.by("id").descending());
        return sessaoVotacaoRepository.findAll(pageRequest);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/Sessao")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Sessao> getSessao(@RequestParam("id") Integer id) {
        return sessaoVotacaoRepository.findById(id);
    }

//    @RequestMapping(method = RequestMethod.GET, path = "/Sesssoes")
//    @ResponseStatus(HttpStatus.OK)
//    public Iterable<Sessao> getVotosSessao(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "10") int page_results) {
//        PageRequest pageRequest = PageRequest.of(page, page_results, Sort.by("id").descending());
//        return sessaoVotacaoRepository.findAll(pageRequest);
//    }

    @RequestMapping(method = RequestMethod.POST, value = "/pauta/{idPauta}/votacao")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity postSessaoVotacao(@PathVariable Integer idPauta, @RequestParam(required = false, defaultValue = "1") long minutes) throws Exception {

        return sessaoVotacaoService.abrirSessao(idPauta,minutes);
    }


}
