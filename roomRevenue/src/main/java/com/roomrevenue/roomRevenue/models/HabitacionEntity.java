package com.roomrevenue.roomRevenue.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "habitaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabitacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long numHabitacion;

    @Enumerated(EnumType.STRING)
    private TipoHabitacion tipoHabitacion;

    @Enumerated(EnumType.STRING)
    private EstadoHabitacion estado;
}