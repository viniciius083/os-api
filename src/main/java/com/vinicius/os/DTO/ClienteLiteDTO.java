package com.vinicius.os.DTO;

import com.vinicius.os.domain.Cliente;

public class ClienteLiteDTO {

    private Integer id;

    private String nome;

    private String telefone;

    public ClienteLiteDTO() {
    }

    public ClienteLiteDTO(Cliente cliente){
        this.setId(cliente.getId());
        this.setNome(cliente.getNome());
        this.setTelefone(cliente.getTelefone());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}