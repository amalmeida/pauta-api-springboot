package com.springboot.rest.poc.pauta.repository;

import com.springboot.rest.poc.pauta.entity.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessaoVotacaoRepository extends JpaRepository<Sessao, Integer> {

    Optional<Sessao> findById(Integer id);

    Optional<Sessao> findByPautaId(Integer id);
}
