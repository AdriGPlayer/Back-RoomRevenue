package com.roomrevenue.roomRevenue.controllers;

import com.roomrevenue.roomRevenue.models.ClienteEntity;
import com.roomrevenue.roomRevenue.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "http://localhost:3000")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/getCliente")
    public Iterable<ClienteEntity> getAllClientes() {
        return clienteRepository.findAll();
    }

    @GetMapping("/getCliente/{id}")
    public ResponseEntity<ClienteEntity> getClienteById(@PathVariable Long id) {
        Optional<ClienteEntity> cliente = clienteRepository.findById(id);
        return cliente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/saveCliente")
    public ClienteEntity createCliente(@RequestBody ClienteEntity cliente) {
        return clienteRepository.save(cliente);
    }

    @PutMapping("/updateCliente/{id}")
    public ResponseEntity<ClienteEntity> updateCliente(@PathVariable Long id, @RequestBody ClienteEntity clienteDetails) {
        Optional<ClienteEntity> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isPresent()) {
            ClienteEntity cliente = optionalCliente.get();
            cliente.setNombre(clienteDetails.getNombre());
            cliente.setApPaterno(clienteDetails.getApPaterno());

            return ResponseEntity.ok(clienteRepository.save(cliente));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
