package com.nelson.usario.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Entity
@Table(name = "ingresos")
public class Ingresos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Past
    @NotNull
    private Date fechaIngreso;

    @NotNull
    private Integer producidoDiario;

    @NotEmpty
    private String descripcion;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonIgnoreProperties({ "ingresos", "handler", "hibernateLazyInitializer" })
    @ManyToMany
    @JoinTable(name = "ingresos_vehiculos", joinColumns =
    @JoinColumn(name = "ingreso_id", referencedColumnName = "id"), inverseJoinColumns = 
    @JoinColumn(name = "vehiculo_id", referencedColumnName = "id"), uniqueConstraints =	{
    @UniqueConstraint(columnNames = { "ingreso_id"})})
    private Set<Vehiculo> vehiculos = new HashSet<>();;

    public Ingresos() {
       // this.vehiculos = new ArrayList<>();
    }

    public Ingresos(Long id, Date fechaIngreso, Integer producidoDiario,
            String descripcion, Set<Vehiculo> vehiculos) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
        this.producidoDiario = producidoDiario;
        this.descripcion = descripcion;
        this.vehiculos = vehiculos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getProducidoDiario() {
        return producidoDiario;
    }

    public void setProducidoDiario(Integer producidoDiario) {
        this.producidoDiario = producidoDiario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(Set<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

}
