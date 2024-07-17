package com.roomrevenue.roomRevenue.controllers;

import com.roomrevenue.roomRevenue.models.EstadoHabitacion;
import com.roomrevenue.roomRevenue.models.HabitacionEntity;
import com.roomrevenue.roomRevenue.repositories.HabitacionRepository;
import com.roomrevenue.roomRevenue.services.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/habitaciones")
public class HabitacionController {

    @Autowired
    private HabitacionRepository habitacionRepository;
    @Autowired
    private HabitacionService habitacionService;
    @GetMapping("/getHabitacion")
    public Iterable<HabitacionEntity> getAllHabitaciones() {
        return habitacionRepository.findAll();
    }

    @GetMapping("/getHabitacion/{id}")
    public ResponseEntity<HabitacionEntity> getHabitacionById(@PathVariable Long id) {
        Optional<HabitacionEntity> habitacion = habitacionRepository.findById(id);
        return habitacion.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/getHabitacion/reserva/{numHabitacion}")
    public ResponseEntity<HabitacionEntity> getHabitacionByNumHabitacion(@PathVariable Long numHabitacion) {
        Optional<HabitacionEntity> habitacion = habitacionRepository.findByNumHabitacion(numHabitacion);
        return  habitacion.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/getHabitacionesVaciasLimpias")
    public Iterable<HabitacionEntity> getHabitacionesVaciasLimpias(){
        return habitacionRepository.findByEstado(EstadoHabitacion.VACIA_LIMPIA);
    }

    @PostMapping("/postHabitacion")
    public HabitacionEntity createHabitacion(@RequestBody HabitacionEntity habitacion) {
        return habitacionRepository.save(habitacion);
    }

    @PutMapping("/updateHabitacion/{id}")
    public ResponseEntity<HabitacionEntity> updateHabitacion(@PathVariable Long id, @RequestBody HabitacionEntity habitacionDetails) {
        Optional<HabitacionEntity> optionalHabitacion = habitacionRepository.findById(id);
        if (optionalHabitacion.isPresent()) {
            HabitacionEntity habitacion = optionalHabitacion.get();

            habitacion.setEstado(habitacionDetails.getEstado());

            return ResponseEntity.ok(habitacionRepository.save(habitacion));
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping("/deleteHabitacion/{id}")
    public ResponseEntity<Void> deleteHabitacion(@PathVariable Long id) {
        habitacionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}