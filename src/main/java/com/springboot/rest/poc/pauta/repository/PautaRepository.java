package com.springboot.rest.poc.pauta.repository;

import com.springboot.rest.poc.pauta.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Integer> {

}