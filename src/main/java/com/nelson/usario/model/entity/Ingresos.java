package com.nelson.usario.model.entity;

import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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

    @NotNull
    private Integer vrLiquidacion;

    @NotNull
    private Integer vrGasolina;

    @NotNull
    private Integer vrGastosAdicionales;

    @NotEmpty
    private String descripcion;

    @NotNull
    @JsonIgnoreProperties({ "ingresos", "handler", "hibernateLazyInitializer" })
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehiculo_id", referencedColumnName = "id")
    private Vehiculo vehiculo;

    public Ingresos() {
    }

    public Ingresos(Long id, Date fechaIngreso, Integer producidoDiario,
            Integer vrLiquidacion, Integer vrGasolina, Integer vrGastosAdicionales,
            String descripcion, Vehiculo vehiculo) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
        this.producidoDiario = producidoDiario;
        this.vrLiquidacion = vrLiquidacion;
        this.vrGasolina = vrGasolina;
        this.vrGastosAdicionales = vrGastosAdicionales;
        this.descripcion = descripcion;
        this.vehiculo = vehiculo;
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

    public Integer getVrLiquidacion() {
        return vrLiquidacion;
    }

    public void setVrLiquidacion(Integer vrLiquidacion) {
        this.vrLiquidacion = vrLiquidacion;
    }

    public Integer getVrGasolina() {
        return vrGasolina;
    }

    public void setVrGasolina(Integer vrGasolina) {
        this.vrGasolina = vrGasolina;
    }

    public Integer getVrGastosAdicionales() {
        return vrGastosAdicionales;
    }

    public void setVrGastosAdicionales(Integer vrGastosAdicionales) {
        this.vrGastosAdicionales = vrGastosAdicionales;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((fechaIngreso == null) ? 0 : fechaIngreso.hashCode());
        result = prime * result + ((producidoDiario == null) ? 0 : producidoDiario.hashCode());
        result = prime * result + ((vrLiquidacion == null) ? 0 : vrLiquidacion.hashCode());
        result = prime * result + ((vrGasolina == null) ? 0 : vrGasolina.hashCode());
        result = prime * result + ((vrGastosAdicionales == null) ? 0 : vrGastosAdicionales.hashCode());
        result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
        result = prime * result + ((vehiculo == null) ? 0 : vehiculo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ingresos other = (Ingresos) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (fechaIngreso == null) {
            if (other.fechaIngreso != null)
                return false;
        } else if (!fechaIngreso.equals(other.fechaIngreso))
            return false;
        if (producidoDiario == null) {
            if (other.producidoDiario != null)
                return false;
        } else if (!producidoDiario.equals(other.producidoDiario))
            return false;
        if (vrLiquidacion == null) {
            if (other.vrLiquidacion != null)
                return false;
        } else if (!vrLiquidacion.equals(other.vrLiquidacion))
            return false;
        if (vrGasolina == null) {
            if (other.vrGasolina != null)
                return false;
        } else if (!vrGasolina.equals(other.vrGasolina))
            return false;
        if (vrGastosAdicionales == null) {
            if (other.vrGastosAdicionales != null)
                return false;
        } else if (!vrGastosAdicionales.equals(other.vrGastosAdicionales))
            return false;
        if (descripcion == null) {
            if (other.descripcion != null)
                return false;
        } else if (!descripcion.equals(other.descripcion))
            return false;
        if (vehiculo == null) {
            if (other.vehiculo != null)
                return false;
        } else if (!vehiculo.equals(other.vehiculo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Ingresos [id=" + id + ", fechaIngreso=" + fechaIngreso + ", producidoDiario=" + producidoDiario
                + ", vrLiquidacion=" + vrLiquidacion + ", vrGasolina=" + vrGasolina + ", vrGastosAdicionales="
                + vrGastosAdicionales + ", descripcion=" + descripcion + ", vehiculo=" + vehiculo + "]";
    }

}
