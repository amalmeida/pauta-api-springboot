package com.springboot.rest.poc.pauta.service;

import com.springboot.rest.poc.pauta.entity.Associado;
import com.springboot.rest.poc.pauta.entity.Sessao;
import com.springboot.rest.poc.pauta.entity.Voto;
import com.springboot.rest.poc.pauta.enumeration.VotoEnum;
import com.springboot.rest.poc.pauta.repository.AssociadoRepository;
import com.springboot.rest.poc.pauta.repository.SessaoVotacaoRepository;
import com.springboot.rest.poc.pauta.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VotoService {


    @Autowired
    VotoRepository votoRepository;

    @Autowired
    AssociadoRepository associadoRepository;

    @Autowired
    SessaoVotacaoRepository sessaoVotacaoRepository;

    @Autowired
    AssociadoService associadoService;

    public ResponseEntity votar(Voto voto) throws Exception {

        Associado associado = associadoRepository.findById(voto.getIdAssociado()).get();
        voto.setAssociado(associado);
        verificarAssociado(voto);
        voto.setAssociado(associado);
        Optional<Sessao> sessao = sessaoVotacaoRepository.findById(voto.getIdSessao());
        validarSessao(sessao.get().getDataFim());

        if (VotoEnum.SIM.equals(voto.getVoto())) {
            sessao.get().getPauta().setQuantidadeVotosSim(sessao.get().getPauta().getQuantidadeVotosSim() + 1);
        } else {
            sessao.get().getPauta().setQuantidadeVotosNao(sessao.get().getPauta().getQuantidadeVotosNao() + 1);
        }
        voto.setSessao(sessao.get());
        votoRepository.save(voto);
        return new ResponseEntity(HttpStatus.OK, HttpStatus.valueOf("Voto Computado com Sucesso!"));
    }

    private ResponseEntity<HttpStatus> verificarAssociado(Voto voto) throws Exception {
        Optional<Voto> associadoVoto = votoRepository.findBySessaoIdAndAssociadoId(voto.getIdSessao(), voto.getIdAssociado());
        if(associadoVoto.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, HttpStatus.valueOf("O associado " + voto.getAssociado().getNome() + " já realizou seu voto!"));
        }
        associadoService.validarCPFAssociado(voto.getAssociado().getCpf());

        return null;
    }

    private ResponseEntity<HttpStatus> validarSessao(LocalDateTime dataFim) {
        if (dataFim.isBefore(LocalDateTime.now())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, HttpStatus.valueOf("A sessão já esta fechada para voração!"));
        }

        return null;
    }

}
