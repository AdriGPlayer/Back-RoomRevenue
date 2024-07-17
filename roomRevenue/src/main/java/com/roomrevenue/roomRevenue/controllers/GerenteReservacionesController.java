package com.roomrevenue.roomRevenue.controllers;
import com.roomrevenue.roomRevenue.models.GerenteDivCuartosEntity;
import com.roomrevenue.roomRevenue.models.GerenteReservacionesEntity;
import com.roomrevenue.roomRevenue.repositories.GerenteDivCuartosRepository;
import com.roomrevenue.roomRevenue.repositories.GerenteReservacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/gerenteReservaciones")

public class GerenteReservacionesController {
    @Autowired
    private GerenteReservacionesRepository gerenteReservacionesRepository;


    @PostMapping("/saveGerenteReservaciones")
    public void guardarDivCuartos(@RequestBody GerenteReservacionesEntity gerenteReservaciones){
        gerenteReservacionesRepository.save(gerenteReservaciones);
    }

    @GetMapping("/getGerenteReservaciones")
    public Iterable<GerenteReservacionesEntity> listarDivCuartos(){
        return gerenteReservacionesRepository.findAll();
    }

    @GetMapping("/getGerenteReservaciones/{id}")
    public ResponseEntity<GerenteReservacionesEntity> gerenteDivCuartosEntityById(@PathVariable long id){
        GerenteReservacionesEntity gerenteReservaciones = gerenteReservacionesRepository.findById(id)
                .orElseThrow(() -> new ResolutionException("El gerente con id " + id + " no existe"));

        return ResponseEntity.ok(gerenteReservaciones);
    }

    @DeleteMapping("/deleteGerenteReservaciones/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteDivCuartosById(@PathVariable long id){
        GerenteReservacionesEntity gerenteReservaciones = gerenteReservacionesRepository.findById(id)
                .orElseThrow(() -> new ResolutionException("El gerente con id " + id + " no existe"));
        gerenteReservacionesRepository.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateGerenteReservaciones/{id}")
    public ResponseEntity<GerenteReservacionesEntity> updateDivCuartosById(@PathVariable long id,
                                                                        @RequestBody GerenteReservacionesEntity gerenteReservaciones){
        GerenteReservacionesEntity gerente = gerenteReservacionesRepository.findById(id).get();
        gerente.setNombre(gerenteReservaciones.getNombre());
        gerente.setEmail(gerenteReservaciones.getEmail());
        gerente.setContraseña(gerenteReservaciones.getContraseña());
        gerente.setUsuario(gerenteReservaciones.getUsuario());
        gerente.setTipoUsuario(gerenteReservaciones.getTipoUsuario());
        gerenteReservacionesRepository.save(gerente);
        return ResponseEntity.ok(gerente);
    }




}
