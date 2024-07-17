package com.roomrevenue.roomRevenue.services;
import com.roomrevenue.roomRevenue.models.GerenteReservacionesEntity;
import com.roomrevenue.roomRevenue.repositories.GerenteReservacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GerenteReservacionesService {

    @Autowired
    private GerenteReservacionesRepository gerenteReservacionesRepository;

    public GerenteReservacionesEntity findByUsuario(String usuario) {
        return gerenteReservacionesRepository.findByUsuario(usuario);
    }
}
