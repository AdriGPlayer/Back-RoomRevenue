package com.roomrevenue.roomRevenue.controllers;
import com.roomrevenue.roomRevenue.models.GerenteDivCuartosEntity;
import com.roomrevenue.roomRevenue.repositories.GerenteDivCuartosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/divCuartos")

public class DivCuartosController {

    @Autowired
    private GerenteDivCuartosRepository gerenteDivCuartosRepository;

    @PostMapping("/saveDivCuartos")
    public void guardarDivCuartos(@RequestBody  GerenteDivCuartosEntity gerenteDivCuartos){
        gerenteDivCuartosRepository.save(gerenteDivCuartos);
    }

    @GetMapping("/getDivCuartos")
    public Iterable<GerenteDivCuartosEntity> listarDivCuartos(){
        return gerenteDivCuartosRepository.findAll();
    }

    @GetMapping("/getDivCuartos/{id}")
    public ResponseEntity<GerenteDivCuartosEntity> gerenteDivCuartosEntityById(@PathVariable long id){
        GerenteDivCuartosEntity gerenteDivCuartosEntity = gerenteDivCuartosRepository.findById(id)
                .orElseThrow(() -> new ResolutionException("El gerente con id " + id + " no existe"));

        return ResponseEntity.ok(gerenteDivCuartosEntity);
    }

    @DeleteMapping("/deleteDivCuartos/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteDivCuartosById(@PathVariable long id){
        GerenteDivCuartosEntity gerenteDivCuartosEntity = gerenteDivCuartosRepository.findById(id)
                .orElseThrow(() -> new ResolutionException("El gerente con id " + id + " no existe"));
        gerenteDivCuartosRepository.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateDivCuartos/{id}")
    public ResponseEntity<GerenteDivCuartosEntity> updateDivCuartosById(@PathVariable long id,
                                                                        @RequestBody GerenteDivCuartosEntity gerenteDivCuartosEntity){
        GerenteDivCuartosEntity gerente = gerenteDivCuartosRepository.findById(id).get();
        gerente.setNombre(gerenteDivCuartosEntity.getNombre());
        gerente.setEmail(gerenteDivCuartosEntity.getEmail());
        gerente.setContraseña(gerenteDivCuartosEntity.getContraseña());
        gerente.setUsuario(gerenteDivCuartosEntity.getUsuario());
        gerente.setTipoUsuario(gerenteDivCuartosEntity.getTipoUsuario());
        gerenteDivCuartosRepository.save(gerente);
        return ResponseEntity.ok(gerente);
    }



}


