package com.vinicius.os.DTO;

import com.vinicius.os.domain.Tecnico;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;

public class TecnicoLiteDTO {

    private Integer id;

    private String nome;

    private String telefone;

    public TecnicoLiteDTO() {
    }

    public TecnicoLiteDTO(Tecnico tecnico){
        this.setId(tecnico.getId());
        this.setNome(tecnico.getNome());
        this.setTelefone(tecnico.getTelefone());
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