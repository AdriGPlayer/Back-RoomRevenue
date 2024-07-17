package com.roomrevenue.roomRevenue.services;
import com.roomrevenue.roomRevenue.models.HabitacionEntity;
import com.roomrevenue.roomRevenue.repositories.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class HabitacionService {
    private HabitacionRepository habitacionRepository;

    public Optional<HabitacionEntity> getHabitacionByNumHabitacion(Long numHabitacion) {
        return habitacionRepository.findByNumHabitacion(numHabitacion).stream().findFirst();
    }
}
