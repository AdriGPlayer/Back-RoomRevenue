package com.roomrevenue.roomRevenue.services;

import com.roomrevenue.roomRevenue.models.RecepcionistaEntity;
import com.roomrevenue.roomRevenue.repositories.RecepcionistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecepcionistaService {

    @Autowired
    private RecepcionistaRepository recepcionistaRepository;

    public RecepcionistaEntity findByUsuario(String usuario) {
        return recepcionistaRepository.findByUsuario(usuario);
    }
}
