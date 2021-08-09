package com.vinicius.os.services;

import com.vinicius.os.DTO.ClienteFullDTO;
import com.vinicius.os.DTO.ClienteLiteDTO;
import com.vinicius.os.domain.Cliente;
import com.vinicius.os.repository.ClienteRepository;
import com.vinicius.os.services.exceptions.DataIntegratyViolationxception;
import com.vinicius.os.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteLiteDTO findById(Integer id) {
        return new ClienteLiteDTO(clienteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Cliente " + id + " não Encontrado!")));
    }

    public List<ClienteLiteDTO> findAll() {
        return clienteRepository.findAll().stream().map(ClienteLiteDTO::new).collect(Collectors.toList());
    }

    public ClienteLiteDTO createCliente(ClienteFullDTO clienteFullDTO) {
        if (existsClienteByCpf(clienteFullDTO.getCpf()).isPresent()) {
            throw new DataIntegratyViolationxception("CPF já cadastrado!");
        }
        Cliente cliente = clienteRepository.save(new Cliente(clienteFullDTO));
        return new ClienteLiteDTO(cliente);
    }

    public Optional<Cliente> existsClienteByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    public void deleteCliente(Integer id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new DataIntegratyViolationxception("Cliente " + id + " não Encontrado!"));
        if (cliente.getServicos().size() > 0) {
            throw new DataIntegratyViolationxception("Cliente " + id + " possui ordens de servioço e não pode ser deletado!");
        }
        clienteRepository.deleteById(id);
    }

    public ClienteLiteDTO updateCliente(Integer id, ClienteFullDTO clienteFullDTO) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new DataIntegratyViolationxception("Cliente " + id + " não Encontrado!"));
        if (existsClienteByCpf(clienteFullDTO.getCpf()).isPresent() && cliente.getId() != id) {
            throw new DataIntegratyViolationxception("CPF já cadastrado!");
        }
        cliente.update(clienteFullDTO);
        return new ClienteLiteDTO(cliente);
    }
}
