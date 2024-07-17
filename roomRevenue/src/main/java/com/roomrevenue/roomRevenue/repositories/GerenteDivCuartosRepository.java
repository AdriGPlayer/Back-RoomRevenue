package com.roomrevenue.roomRevenue.repositories;


import com.roomrevenue.roomRevenue.models.GerenteDivCuartosEntity;
import org.springframework.data.repository.CrudRepository;

public interface GerenteDivCuartosRepository extends CrudRepository<GerenteDivCuartosEntity, Long> {
    GerenteDivCuartosEntity findByUsuario(String usuario);

}
