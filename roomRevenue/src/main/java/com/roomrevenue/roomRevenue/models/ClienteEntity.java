package com.roomrevenue.roomRevenue.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apPaterno;

    @Column(nullable = false)
    private String apMaterno;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String contraseña;

    @Column(nullable = false)
    private String celular;

    @Column(nullable = false, unique = true)
    private String usuario;

    @OneToMany(mappedBy = "cliente")
    private List<ReservaEntity> reservas;


}