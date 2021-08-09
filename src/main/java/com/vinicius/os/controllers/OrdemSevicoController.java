package com.vinicius.os.controllers;

import com.vinicius.os.DTO.ClienteFullDTO;
import com.vinicius.os.DTO.ClienteLiteDTO;
import com.vinicius.os.DTO.OSCreateDTO;
import com.vinicius.os.DTO.OSLiteDTO;
import com.vinicius.os.services.OSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/os")
public class OrdemSevicoController {

    @Autowired
    OSService osService;

    @GetMapping("/{id}")
    public ResponseEntity<OSLiteDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(osService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<OSLiteDTO>> findAll(){
        return ResponseEntity.ok().body(osService.findAll());
    }

    @PostMapping
    public ResponseEntity<OSLiteDTO> createOS(@RequestBody @Valid OSCreateDTO osCreateDTO){
        OSLiteDTO os = osService.createOS(osCreateDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(os.getId()).toUri();
        return ResponseEntity.created(uri).body(os);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OSLiteDTO> updateCliente(@PathVariable Integer id, @RequestBody @Valid OSCreateDTO osCreateDTO){
        return ResponseEntity.ok().body(osService.updateOS(id, osCreateDTO));
    }

}
