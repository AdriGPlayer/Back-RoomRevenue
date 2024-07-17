package com.roomrevenue.roomRevenue.services;

import com.roomrevenue.roomRevenue.models.GerenteGeneralEntity;
import com.roomrevenue.roomRevenue.repositories.GerenteGeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GerenteGeneralService {

    @Autowired
    private GerenteGeneralRepository gerenteGeneralRepository ;

    public GerenteGeneralEntity findByUsuario(String usuario) {
        return gerenteGeneralRepository.findByUsuario(usuario);
    }
}