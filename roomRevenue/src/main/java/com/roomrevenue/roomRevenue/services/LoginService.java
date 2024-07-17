package com.roomrevenue.roomRevenue.services;
import com.roomrevenue.roomRevenue.models.GerenteDivCuartosEntity;
import com.roomrevenue.roomRevenue.models.GerenteGeneralEntity;
import com.roomrevenue.roomRevenue.models.GerenteReservacionesEntity;
import com.roomrevenue.roomRevenue.models.RecepcionistaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private GerenteGeneralService gerenteGeneralService;

    @Autowired
    private GerenteDivCuartosService gerenteDivCuartosService;

    @Autowired
    private GerenteReservacionesService gerenteReservacionesService;

    @Autowired
    private RecepcionistaService recepcionistaService;

    public String getTipoUsuario(String usuario, String contraseña) {
        // Primero intentamos autenticar al usuario como Gerente General
        GerenteGeneralEntity gerenteGeneral = gerenteGeneralService.findByUsuario(usuario);
        if (gerenteGeneral != null && gerenteGeneral.getContraseña().equals(contraseña)) {
            return "GerenteGeneral";
        }

        // Si no es Gerente General, intentamos como Gerente de División de Cuartos
        GerenteDivCuartosEntity gerenteDivCuartos = gerenteDivCuartosService.findByUsuario(usuario);
        if (gerenteDivCuartos != null && gerenteDivCuartos.getContraseña().equals(contraseña)) {
            return "GerenteDivCuartos";
        }

        // Si no es Gerente de División de Cuartos, intentamos como Gerente de Reservaciones
        GerenteReservacionesEntity gerenteReservaciones = gerenteReservacionesService.findByUsuario(usuario);
        if (gerenteReservaciones != null && gerenteReservaciones.getContraseña().equals(contraseña)) {
            return "GerenteReservaciones";
        }

        // Si no es Gerente de Reservaciones, intentamos como Recepcionista
        RecepcionistaEntity recepcionista = recepcionistaService.findByUsuario(usuario);
        if (recepcionista != null && recepcionista.getContraseña().equals(contraseña)) {
            return "Recepcionista";
        }

        // Si no se encontró ningún usuario con las credenciales proporcionadas, devolvemos null
        return null;
    }
}
