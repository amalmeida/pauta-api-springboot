package com.springboot.rest.poc.pauta.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;


@Entity
public class Associado {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull  //(message = "Por favor informe o cpf")
    @Column(unique = true)
    private String cpf;

    @NotNull
    @Column
    private String nome;

    @Column
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Associado [idAssociado=" + id + ", cpf=" + cpf + ", nome=" + nome + "]";
    }

}
