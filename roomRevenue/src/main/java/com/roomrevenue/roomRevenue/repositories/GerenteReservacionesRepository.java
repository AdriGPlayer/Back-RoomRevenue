package com.roomrevenue.roomRevenue.repositories;

import com.roomrevenue.roomRevenue.models.GerenteReservacionesEntity;
import org.springframework.data.repository.CrudRepository;

public interface GerenteReservacionesRepository extends CrudRepository<GerenteReservacionesEntity, Long> {
    GerenteReservacionesEntity findByUsuario(String usuario);
}
