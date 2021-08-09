package com.vinicius.os.controllers;

import com.vinicius.os.DTO.ClienteFullDTO;
import com.vinicius.os.DTO.ClienteLiteDTO;
import com.vinicius.os.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteLiteDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(clienteService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClienteLiteDTO>> findAll(){
        return ResponseEntity.ok().body(clienteService.findAll());
    }

    @PostMapping()
    public ResponseEntity<ClienteLiteDTO> createCliente(@RequestBody @Valid ClienteFullDTO clienteFullDTO){
        ClienteLiteDTO tec = clienteService.createCliente(clienteFullDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tec.getId()).toUri();
        return ResponseEntity.created(uri).body(tec);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteLiteDTO> updateCliente(@PathVariable Integer id, @RequestBody @Valid ClienteFullDTO clienteFullDTO){
        return ResponseEntity.ok().body(clienteService.updateCliente(id, clienteFullDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id){
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

}
