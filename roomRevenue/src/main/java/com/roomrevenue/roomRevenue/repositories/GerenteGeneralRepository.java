package com.roomrevenue.roomRevenue.repositories;



import com.roomrevenue.roomRevenue.models.GerenteGeneralEntity;
import org.springframework.data.repository.CrudRepository;

public interface GerenteGeneralRepository extends CrudRepository<GerenteGeneralEntity, Long> {
    GerenteGeneralEntity findByUsuario(String usuario);
}
