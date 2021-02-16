package com.springboot.rest.poc.pauta.controller;

import com.springboot.rest.poc.pauta.entity.Voto;
import com.springboot.rest.poc.pauta.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class VotoController {

    @Autowired
    VotoService votoService;


    @RequestMapping(method = RequestMethod.POST, value = "/votar")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity postVotar(@RequestBody Voto voto) throws Exception {
        return votoService.votar(voto);
    }
}