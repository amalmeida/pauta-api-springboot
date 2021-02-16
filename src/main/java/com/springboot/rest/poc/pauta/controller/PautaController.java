package com.springboot.rest.poc.pauta.controller;


import java.util.Date;

import com.springboot.rest.poc.pauta.entity.Pauta;
import com.springboot.rest.poc.pauta.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class PautaController {

    @Autowired
    PautaRepository pautaRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/pautas")
    public ResponseEntity<Object> postPauta(@RequestBody Pauta pauta) {
        pauta.setData(new Date());
        return new ResponseEntity<>(pautaRepository.save(pauta), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/pautas")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Pauta> getPautas(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "10") int page_results) {
        PageRequest pageRequest = PageRequest.of(page, page_results, Sort.by("id").descending());
        return pautaRepository.findAll(pageRequest);
    }


}