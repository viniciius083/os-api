package com.vinicius.os.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vinicius.os.domain.OrdemServico;
import com.vinicius.os.enums.Prioridade;
import com.vinicius.os.enums.Status;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OSCreateDTO {

    @NotNull
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    private String observacoes;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private Integer tecnicoId;

    @NotNull
    private Integer clienteId;



    public OSCreateDTO() {
    }


    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getTecnicoId() {
        return tecnicoId;
    }

    public void setTecnicoId(Integer tecnicoId) {
        this.tecnicoId = tecnicoId;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }
}
