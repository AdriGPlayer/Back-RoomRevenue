package com.roomrevenue.roomRevenue.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "GerenteDivCuartos")
@Data
public class GerenteDivCuartosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "usuario", nullable = false, unique = true, length = 50)
    private String usuario;

    @Column(name = "contraseña", nullable = false, length = 100)
    private String contraseña;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario;


}
