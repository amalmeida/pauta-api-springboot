package com.springboot.rest.poc.pauta.repository;

import com.springboot.rest.poc.pauta.entity.Associado;
import com.springboot.rest.poc.pauta.entity.Sessao;
import com.springboot.rest.poc.pauta.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Integer> {

    Optional<Voto> findBySessaoIdAndAssociadoId(Integer idSessao, Integer idAssociado);
}
