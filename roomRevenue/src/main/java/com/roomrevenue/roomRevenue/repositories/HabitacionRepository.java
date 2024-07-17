package com.roomrevenue.roomRevenue.repositories;

import com.roomrevenue.roomRevenue.models.EstadoHabitacion;
import com.roomrevenue.roomRevenue.models.HabitacionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface HabitacionRepository extends CrudRepository<HabitacionEntity, Long>{
    List<HabitacionEntity> findByEstado(EstadoHabitacion estado);
    Optional<HabitacionEntity> findByNumHabitacion(Long numHabitacion);


}