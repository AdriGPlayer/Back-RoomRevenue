package com.roomrevenue.roomRevenue.controllers;

import com.roomrevenue.roomRevenue.dto.LoginRequest;
import com.roomrevenue.roomRevenue.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/login")

public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String usuario = loginRequest.getUsuario();
        String contraseña = loginRequest.getContraseña();

        String tipoUsuario = loginService.getTipoUsuario(usuario, contraseña);

        if (tipoUsuario != null) {
            switch (tipoUsuario) {
                case "GerenteGeneral":
                    return ResponseEntity.ok("Gerente General");
                case "GerenteDivCuartos":
                    return ResponseEntity.ok("División de Cuartos");
                case "GerenteReservaciones":
                    return ResponseEntity.ok("Gerente de Reservaciones");
                case "Recepcionista":
                    return ResponseEntity.ok("Recepcionista");
                default:
                    return ResponseEntity.badRequest().body("Tipo de usuario no reconocido");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

}
