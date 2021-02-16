package com.springboot.rest.poc.pauta.entity;

import com.sun.istack.NotNull;
        import javax.persistence.Column;
        import javax.persistence.Entity;
        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;
        import javax.persistence.Id;
        import javax.persistence.PrePersist;
import java.util.Date;

@Entity
public class Pauta {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Id
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String tema;

    @Column
    private Integer quantidadeVotosSim;

    @Column
    private Integer quantidadeVotosNao;

    @Column
    private Date data;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Integer getQuantidadeVotosSim() {
        return quantidadeVotosSim;
    }

    public void setQuantidadeVotosSim(Integer quantidadeVotosSim) {
        this.quantidadeVotosSim = quantidadeVotosSim;
    }

    public Integer getQuantidadeVotosNao() {
        return quantidadeVotosNao;
    }

    public void setQuantidadeVotosNao(Integer quantidadeVotosNao) {
        this.quantidadeVotosNao = quantidadeVotosNao;
    }

    @PrePersist
    public void prePersist() {
        quantidadeVotosSim = 0;
        quantidadeVotosNao = 0;
    }
}
