package com.springboot.rest.poc.pauta.service;

import com.springboot.rest.poc.pauta.entity.Pauta;
import com.springboot.rest.poc.pauta.entity.Sessao;
import com.springboot.rest.poc.pauta.error.ErrorResponse;
import com.springboot.rest.poc.pauta.repository.PautaRepository;
import com.springboot.rest.poc.pauta.repository.SessaoVotacaoRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SessaoVotacaoService {

    @Autowired
    SessaoVotacaoRepository sessaoVotacaoRepository;

    @Autowired
    PautaRepository pautaRepository;


    public ResponseEntity abrirSessao(Integer idPauta, Long min)  {

        Sessao sessao = new Sessao();

        try {

            Optional<Pauta> pauta = pautaRepository.findById(idPauta);
            if (!pauta.isPresent())
                return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Pauta " + idPauta + " não encontrada. Favor informar uma Pauta válida!",""), HttpStatus.INTERNAL_SERVER_ERROR);

            Optional<Sessao> sessaoVotacao = sessaoVotacaoRepository.findByPautaId(idPauta);

            //Verificar se a Sessão esta aberta ou se ja expirou
            if(sessaoVotacao.isPresent()){
                if(sessaoVotacao.get().getDataFim().isBefore(LocalDateTime.now())){
                    return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Sessão "+sessaoVotacao.get().getId() + " já encerrada!",""), HttpStatus.INTERNAL_SERVER_ERROR);

                }else{
                    return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Sessão "+sessaoVotacao.get().getId() + " já esta aberta para votação!",""), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }else{
                sessao.setTempoEmMin(Optional.ofNullable(min).orElse(sessao.getTempoEmMin()));
                sessao.setPauta(pauta.get());
                sessao.setDataFim(sessao.getDataInicio().plusMinutes(sessao.getTempoEmMin()));
                sessao = sessaoVotacaoRepository.save(sessao);
                System.out.println("Horario final da Sessão "+ sessao.getId()+": "+ sessao.getDataFim());
            }

        } catch(Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new  ResponseEntity<>(sessao, HttpStatus.CREATED);
    }

    public Sessao carregarSessao(Integer id) throws NotFoundException {
        return sessaoVotacaoRepository.findById(id).orElseThrow(() -> new NotFoundException("Sessão não encontrada."));
    }

    public List<Sessao> listarSessoes() {
        return sessaoVotacaoRepository.findAll();
    }

    private LocalDateTime calcularFimVotacao(Long minutos) {
        return LocalDateTime.now().plusMinutes(minutos);
    }

}
