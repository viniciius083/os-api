package com.vinicius.os.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vinicius.os.DTO.TecnicoFullDTO;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tecnico extends Pessoa{

    @OneToMany(mappedBy = "tecnico")
    private List<OrdemServico> servicos = new ArrayList<>();

    public Tecnico(Integer id, String nome, String cpf, String telefone) {
        super(id, nome, cpf, telefone);
    }

    public Tecnico(){
        super();
    }

    public Tecnico(TecnicoFullDTO tecnico) {
        this.setNome(tecnico.getNome());
        this.setTelefone(tecnico.getTelefone());
        this.setCpf(tecnico.getCpf());
    }

    public List<OrdemServico> getServicos() {
        return servicos;
    }

    public void setServicos(List<OrdemServico> servicos) {
        this.servicos = servicos;
    }

    public void update(TecnicoFullDTO tecnicoDTO) {
        this.setCpf(tecnicoDTO.getCpf());
        this.setTelefone(tecnicoDTO.getTelefone());
        this.setNome(tecnicoDTO.getNome());
    }
}
