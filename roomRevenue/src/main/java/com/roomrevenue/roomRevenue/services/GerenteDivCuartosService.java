package com.roomrevenue.roomRevenue.services;

import com.roomrevenue.roomRevenue.models.GerenteDivCuartosEntity;
import com.roomrevenue.roomRevenue.repositories.GerenteDivCuartosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GerenteDivCuartosService {

    @Autowired
    private GerenteDivCuartosRepository gerenteDivCuartosRepository;

    public GerenteDivCuartosEntity findByUsuario(String usuario) {
        return gerenteDivCuartosRepository.findByUsuario(usuario);
    }
}