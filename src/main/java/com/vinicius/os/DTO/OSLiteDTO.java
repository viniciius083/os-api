package com.vinicius.os.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vinicius.os.domain.OrdemServico;
import com.vinicius.os.enums.Prioridade;
import com.vinicius.os.enums.Status;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class OSLiteDTO {

    @NotNull
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataAbertura;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataEncerramento;

    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    private String observacoes;

    @Enumerated(EnumType.STRING)
    private Status status;

    private TecnicoLiteDTO tecnico;

    private ClienteLiteDTO cliente;

    public OSLiteDTO() {
    }

    public OSLiteDTO(OrdemServico ordemServico) {
        this.setId(ordemServico.getId());
        this.setDataAbertura(ordemServico.getDataAbertura());
        this.setDataEncerramento(ordemServico.getDataEncerramento());
        this.setPrioridade(ordemServico.getPrioridade());
        this.setObservacoes(ordemServico.getObservacoes());
        this.setStatus(ordemServico.getStatus());
        this.setCliente(new ClienteLiteDTO(ordemServico.getCliente()));
        this.setTecnico(new TecnicoLiteDTO(ordemServico.getTecnico()));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(LocalDateTime dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
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

    public TecnicoLiteDTO getTecnico() {
        return tecnico;
    }

    public void setTecnico(TecnicoLiteDTO tecnico) {
        this.tecnico = tecnico;
    }

    public ClienteLiteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteLiteDTO cliente) {
        this.cliente = cliente;
    }
}
