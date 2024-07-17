package com.roomrevenue.roomRevenue.controllers;

import com.roomrevenue.roomRevenue.models.EstatusReserva;
import com.roomrevenue.roomRevenue.models.HabitacionEntity;
import com.roomrevenue.roomRevenue.models.MetodoPago;
import com.roomrevenue.roomRevenue.models.ReservaEntity;
import com.roomrevenue.roomRevenue.repositories.HabitacionRepository;
import com.roomrevenue.roomRevenue.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/reservas")
public class ReservaController {
    @Autowired
    private ReservaRepository repository;
    @Autowired
    private HabitacionRepository habitacionRepository;
    @GetMapping("/getReserva")
    public Iterable<ReservaEntity> getReserva(){
        return repository.findAll();
    }

    @GetMapping("/getReserva/{id}")
    public ResponseEntity<ReservaEntity> getReservaById(@PathVariable Long id){
        Optional<ReservaEntity> reserva = repository.findById(id);
        return reserva.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/postReserva")
    public ReservaEntity createReserva(@RequestBody ReservaEntity reserva){
        return repository.save(reserva);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ReservaEntity> updateReserva(@PathVariable Long id, @RequestBody ReservaEntity reservaDetails) {
        Optional<ReservaEntity> optionalReserva = repository.findById(id);
        if (optionalReserva.isPresent()) {
            ReservaEntity reserva = optionalReserva.get();
            reserva.setFechaEntrada(reservaDetails.getFechaEntrada());
            reserva.setFechaSalida(reservaDetails.getFechaSalida());
            reserva.setTarifa(reservaDetails.getTarifa());
            reserva.setCliente(reservaDetails.getCliente());
            // Añade los demás campos que desees actualizar
            return ResponseEntity.ok(repository.save(reserva));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/asignarNumeroHabitacion/{id}")
    public ResponseEntity<ReservaEntity> asignarHabitacion(@PathVariable Long id, @RequestBody ReservaEntity reservaDetails){
        Optional<ReservaEntity> reserva = repository.findById(id);
        if(reserva.isPresent()){
            ReservaEntity reservaEntity = reserva.get();
            reservaEntity.setNumHabitacion(reservaDetails.getNumHabitacion());
            return ResponseEntity.ok(repository.save(reservaEntity));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/asignarCliente/{id}")
    public ResponseEntity<ReservaEntity> asignarCliente(@PathVariable Long id, @RequestBody ReservaEntity reservaDetails){
        Optional<ReservaEntity> reserva = repository.findById(id);
        if(reserva.isPresent()){
            ReservaEntity reservaEntity = reserva.get();
            reservaEntity.setCliente(reservaDetails.getCliente());
            return ResponseEntity.ok(repository.save(reservaEntity));
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/confirmar/{id}")
    public ResponseEntity<String> confirmarReserva(@PathVariable long id) {
        Optional<ReservaEntity> optionalReserva = repository.findById(id);
        if (optionalReserva.isPresent()) {
            ReservaEntity reserva = optionalReserva.get();
            reserva.setEstatus(EstatusReserva.CONFIRMADA);
            repository.save(reserva);
            return ResponseEntity.ok("Reserva confirmada con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva no encontrada");
        }
    }

    @PutMapping("/agregarMetodoPago/{id}/{metodo}")
    public ResponseEntity<String> addMethodPay(
            @PathVariable long id,
            @PathVariable MetodoPago metodo) {
        Optional<ReservaEntity> optionalReserva = repository.findById(id);
        if (optionalReserva.isPresent()) {
            ReservaEntity reserva = optionalReserva.get();
            reserva.setMetodoPago(metodo);
            try {
                repository.save(reserva);
                return ResponseEntity.ok("Método de pago confirmado");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el método de pago");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva no encontrada con ID: " + id);
        }
    }


}
