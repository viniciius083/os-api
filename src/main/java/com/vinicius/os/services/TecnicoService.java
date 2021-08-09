package com.vinicius.os.services;

import com.vinicius.os.DTO.TecnicoFullDTO;
import com.vinicius.os.DTO.TecnicoLiteDTO;
import com.vinicius.os.domain.Tecnico;
import com.vinicius.os.repository.TecnicoRepository;
import com.vinicius.os.services.exceptions.DataIntegratyViolationxception;
import com.vinicius.os.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TecnicoService {
    @Autowired
    private TecnicoRepository tecnicoRepository;

    public TecnicoLiteDTO findById(Integer id){
        return new TecnicoLiteDTO(tecnicoRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Tecnico "+id+" não Encontrado!")));
    }

    public List<TecnicoLiteDTO> findAll() {
        return tecnicoRepository.findAll().stream().map(TecnicoLiteDTO::new).collect(Collectors.toList());
    }

    public TecnicoLiteDTO createTecnico(TecnicoFullDTO tecnicoDTO) {
        if(existsTecnicoByCpf(tecnicoDTO.getCpf()).isPresent()){
            throw new DataIntegratyViolationxception("CPF já cadastrado!");
        }
        Tecnico tecnico = tecnicoRepository.save(new Tecnico(tecnicoDTO));
        return new TecnicoLiteDTO(tecnico);
    }

    public Optional<Tecnico> existsTecnicoByCpf(String cpf){
        return tecnicoRepository.findByCpf(cpf);
    }

    public void deleteTecnico(Integer id) {
        Tecnico tecnico = tecnicoRepository.findById(id).orElseThrow(()-> new DataIntegratyViolationxception("Tecnico "+id+" não Encontrado!"));
        if (tecnico.getServicos().size() > 0){
            throw new DataIntegratyViolationxception("Tecnico "+id+" possui ordens de servioço e não pode ser deletado!");
        }
        tecnicoRepository.deleteById(id);
    }

    public TecnicoLiteDTO updateTecnico(Integer id, TecnicoFullDTO tecnicoDTO) {
        Tecnico tecnico = tecnicoRepository.findById(id).orElseThrow(()-> new DataIntegratyViolationxception("Tecnico "+id+" não Encontrado!"));
        if(existsTecnicoByCpf(tecnicoDTO.getCpf()).isPresent() && tecnico.getId() != id){
            throw new DataIntegratyViolationxception("CPF já cadastrado!");
        }
        tecnico.update(tecnicoDTO);
        return new TecnicoLiteDTO(tecnico);
    }
}
