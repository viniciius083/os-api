package com.vinicius.os.DTO;

import com.vinicius.os.domain.Cliente;
import com.vinicius.os.domain.Tecnico;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;

public class ClienteFullDTO {

    @NotEmpty(message = "O Campo nome é obrigatório")
    private String nome;

    @CPF
    private String cpf;

    @NotEmpty(message = "O Campo Telefone é obrigatório")
    private String telefone;

    public ClienteFullDTO() {
    }

    public Cliente ToEntity(ClienteFullDTO cliente) {
        return new Cliente(cliente);
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}