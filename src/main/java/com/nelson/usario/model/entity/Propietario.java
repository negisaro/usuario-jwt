package com.nelson.usario.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "propietarios")
public class Propietario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(length = 20)
    private String primerNombre;

    @Column(length = 20)
    private String segundoNombre;

    @NotEmpty
    @Column(length = 20)
    private String primerApellido;

    @Column(length = 20)
    private String segundoApellido;

    @NotEmpty
    @Email
    private String correoElectronico;

    @NotEmpty
    private String telefono;

    @NotEmpty
    private String direccion;

    public Propietario() {
    }

    public Long getId() {
        return id;
    }
}
