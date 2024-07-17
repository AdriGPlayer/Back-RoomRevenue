package com.roomrevenue.roomRevenue.controllers;


import com.roomrevenue.roomRevenue.models.RecepcionistaEntity;
import com.roomrevenue.roomRevenue.repositories.RecepcionistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/recepcionista")
public class RecepcionistaController {

    @Autowired
    private RecepcionistaRepository recepcionistaRepository;

    @PostMapping("/saveRecepcionista")
    private void guardarRecepcionista(@RequestBody RecepcionistaEntity recepcionista){
        recepcionistaRepository.save(recepcionista);
    }
    @GetMapping("/getRecepcionistas")
    private Iterable<RecepcionistaEntity> listarRecepcionistas(){
        return  recepcionistaRepository.findAll();
    }

    @GetMapping("/getRecepcionistas/{id}")
    public ResponseEntity<RecepcionistaEntity> listarRecepcionistaById(@PathVariable long id){
        RecepcionistaEntity recepcionista = recepcionistaRepository.findById(id)
                 .orElseThrow(() -> new ResolutionException("El Recepcionista con id " + id + " no existe"));
        return ResponseEntity.ok(recepcionista);
    }
    @DeleteMapping("/deleteRecepcionista/{id}")
    public ResponseEntity<Map<String, Boolean>>deleteRecepcionista(@PathVariable long id){
        RecepcionistaEntity recepcionista = recepcionistaRepository.findById(id)
                .orElseThrow(() -> new ResolutionException("El Recepcionista con id " + id + " no existe"));
        recepcionistaRepository.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/updateRecepcionista/{id}")
    public ResponseEntity<RecepcionistaEntity>updateRecepcionista(@PathVariable long id,
                                                                  @RequestBody RecepcionistaEntity recepcionista){
        RecepcionistaEntity recepcionistaEntity = recepcionistaRepository.findById(id).get();
        recepcionistaEntity.setNombre(recepcionista.getNombre());
        recepcionistaEntity.setUsuario(recepcionista.getUsuario());
        recepcionistaEntity.setEmail(recepcionista.getEmail());
        recepcionistaEntity.setContraseña(recepcionista.getContraseña());
        recepcionistaRepository.save(recepcionistaEntity);
        return ResponseEntity.ok(recepcionistaEntity);
    }

}
