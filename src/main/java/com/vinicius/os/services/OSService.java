package com.vinicius.os.services;

import com.vinicius.os.DTO.ClienteFullDTO;
import com.vinicius.os.DTO.ClienteLiteDTO;
import com.vinicius.os.DTO.OSCreateDTO;
import com.vinicius.os.DTO.OSLiteDTO;
import com.vinicius.os.domain.Cliente;
import com.vinicius.os.domain.OrdemServico;
import com.vinicius.os.domain.Tecnico;
import com.vinicius.os.repository.ClienteRepository;
import com.vinicius.os.repository.OrdemServicoRepository;
import com.vinicius.os.repository.TecnicoRepository;
import com.vinicius.os.services.exceptions.DataIntegratyViolationxception;
import com.vinicius.os.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OSService {

    @Autowired
    OrdemServicoRepository osRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    TecnicoRepository tecnicoRepository;

    public OSLiteDTO findById(Integer id) {
        return new OSLiteDTO(osRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("OS " + id + " não Encontrado!")));
    }


    public List<OSLiteDTO> findAll() {
        return osRepository.findAll().stream().map(OSLiteDTO::new).collect(Collectors.toList());
    }


    public OSLiteDTO updateOS(Integer id, @Valid OSCreateDTO osCreateDTO) {
        OrdemServico os = osRepository.findById(id).orElseThrow(() -> new DataIntegratyViolationxception("OS " + id + " não Encontrado!"));
        Cliente cliente = clienteRepository.findById(osCreateDTO.getClienteId()).orElseThrow(() -> new ObjectNotFoundException("Cliente " + osCreateDTO.getClienteId() + " não Encontrado!"));
        Tecnico tecnico = tecnicoRepository.findById(osCreateDTO.getTecnicoId()).orElseThrow(()-> new ObjectNotFoundException("Tecnico "+osCreateDTO.getTecnicoId()+" não Encontrado!"));
        os.setCliente(cliente);
        os.setTecnico(tecnico);
        os.setStatus(osCreateDTO.getStatus());
        os.setObservacoes(osCreateDTO.getObservacoes());
        os.setPrioridade(osCreateDTO.getPrioridade());
        osRepository.save(os);
        return new OSLiteDTO(os);
    }

    public OSLiteDTO createOS(OSCreateDTO osCreateDTO) {
        Cliente cliente = clienteRepository.findById(osCreateDTO.getClienteId()).orElseThrow(() -> new ObjectNotFoundException("Cliente " + osCreateDTO.getClienteId() + " não Encontrado!"));
        Tecnico tecnico = tecnicoRepository.findById(osCreateDTO.getTecnicoId()).orElseThrow(()-> new ObjectNotFoundException("Tecnico "+osCreateDTO.getTecnicoId()+" não Encontrado!"));
        OrdemServico ordemServico = new OrdemServico(null, LocalDateTime.now(), null, osCreateDTO.getPrioridade(), osCreateDTO.getObservacoes(), osCreateDTO.getStatus(), tecnico, cliente);
        osRepository.save(ordemServico);
        return new OSLiteDTO(ordemServico);
    }
}
