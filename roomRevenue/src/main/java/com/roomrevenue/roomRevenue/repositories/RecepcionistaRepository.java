package com.roomrevenue.roomRevenue.repositories;


import com.roomrevenue.roomRevenue.models.RecepcionistaEntity;
import org.springframework.data.repository.CrudRepository;

public interface RecepcionistaRepository extends CrudRepository<RecepcionistaEntity, Long> {
    RecepcionistaEntity findByUsuario(String usuario);
}
