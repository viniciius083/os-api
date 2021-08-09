package com.vinicius.os.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vinicius.os.DTO.ClienteFullDTO;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente extends Pessoa {

    @OneToMany(mappedBy = "cliente")
    private List<OrdemServico> servicos = new ArrayList<>();

    public Cliente(Integer id, String nome, String cpf, String telefone) {
        super(id, nome, cpf, telefone);
    }

    public Cliente() {
        super();
    }

    public Cliente(ClienteFullDTO cliente) {
        this.setCpf(cliente.getCpf());
        this.setTelefone(cliente.getTelefone());
        this.setNome(cliente.getNome());
    }

    public List<OrdemServico> getServicos() {
        return servicos;
    }

    public void setServicos(List<OrdemServico> servicos) {
        this.servicos = servicos;
    }

    public void update(ClienteFullDTO clienteDTO) {
        this.setCpf(clienteDTO.getCpf());
        this.setTelefone(clienteDTO.getTelefone());
        this.setNome(clienteDTO.getNome());
    }
}
