package com.springboot.rest.poc.pauta.repository;


import com.springboot.rest.poc.pauta.entity.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Integer> {

    Optional<Associado> findByCpf(String cpf);
}