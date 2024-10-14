package com.nelson.usario.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "vehiculos")
public class Vehiculo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String placa;

	@NotNull
	private String marca;

	@NotNull
	@Column(name = "modelo_anio")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date modeloAnio;

	@NotNull
	private String modeloCarroceria;

	@NotNull
	private String tipoCombustible;
	
	@JsonIgnoreProperties({ "vehiculos", "handler", "hibernateLazyInitializer" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "propietario_id", referencedColumnName = "id")
	private Propietario propietario;

	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@JsonIgnoreProperties({ "vehiculos", "handler", "hibernateLazyInitializer" })
	@ManyToMany(mappedBy = "vehiculos")
	private Set<Ingresos> ingresos = new HashSet<>();;

	public Vehiculo() {
		//this.ingresos = new ArrayList<>();
	}

	public Vehiculo(Long id, String placa, String marca, Date modeloAnio,
			String modeloCarroceria, String tipoCombustible, Propietario propietario,
			Set<Ingresos> ingresos) {
		this.id = id;
		this.placa = placa;
		this.marca = marca;
		this.modeloAnio = modeloAnio;
		this.modeloCarroceria = modeloCarroceria;
		this.tipoCombustible = tipoCombustible;
		this.propietario = propietario;
		this.ingresos = ingresos;
	}

	@PrePersist
	public void prePersist() {
		modeloAnio = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Date getModeloAnio() {
		return modeloAnio;
	}

	public void setModeloAnio(Date modeloAnio) {
		this.modeloAnio = modeloAnio;
	}

	public String getModeloCarroceria() {
		return modeloCarroceria;
	}

	public void setModeloCarroceria(String modeloCarroceria) {
		this.modeloCarroceria = modeloCarroceria;
	}

	public String getTipoCombustible() {
		return tipoCombustible;
	}

	public void setTipoCombustible(String tipoCombustible) {
		this.tipoCombustible = tipoCombustible;
	}

	public Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}

	public Set<Ingresos> getIngresos() {
		return ingresos;
	}

	public void setIngresos(Set<Ingresos> ingresos) {
		this.ingresos = ingresos;
	}

}
