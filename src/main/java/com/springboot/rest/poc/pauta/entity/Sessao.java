package com.springboot.rest.poc.pauta.entity;


import java.time.LocalDateTime;


import javax.persistence.*;

@Entity
public class Sessao {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Id
    private Integer id;

    @Column(updatable = false)
    private LocalDateTime dataInicio = LocalDateTime.now();

    @Column(updatable = false)
    private LocalDateTime dataFim;

    @Column(nullable = false)
    private long tempoEmMin = 1;

    @OneToOne
    private Pauta pauta;

    @Transient
    private Integer idPauta;

//    @Transient
//    private Integer tempoEmMinutos;

//    @Transient
//    private List<Voto> votos;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getTempoEmMin() {
        return tempoEmMin;
    }

    public void setTempoEmMin(long tempoEmMin) {
        this.tempoEmMin = tempoEmMin;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public Integer getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(Integer idPauta) {
        this.idPauta = idPauta;
    }

    public LocalDateTime getDataFim() { return dataFim; }

    public void setDataFim(LocalDateTime dataFim) { this.dataFim = dataFim; }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

}