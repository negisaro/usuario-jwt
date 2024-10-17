package com.nelson.usario.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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

	@NotNull
	@JsonIgnoreProperties({ "vehiculos", "handler", "hibernateLazyInitializer" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "propietario_id", referencedColumnName = "id")
	private Propietario propietario;

	@NotNull
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@JsonIgnoreProperties({ "vehiculos", "handler", "hibernateLazyInitializer" })
	@OneToMany(mappedBy = "vehiculo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Ingresos> ingresos;

	public Vehiculo() {
		this.ingresos = new ArrayList<>();
	}

	public Vehiculo(Long id, String placa, String marca, Date modeloAnio,
			String modeloCarroceria, String tipoCombustible, Propietario propietario,
			List<Ingresos> ingresos) {
		this.id = id;
		this.placa = placa;
		this.marca = marca;
		this.modeloAnio = modeloAnio;
		this.modeloCarroceria = modeloCarroceria;
		this.tipoCombustible = tipoCombustible;
		this.propietario = propietario;
		this.ingresos = ingresos;
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

	public List<Ingresos> getIngresos() {
		return ingresos;
	}

	public void setIngresos(List<Ingresos> ingresos) {
		this.ingresos = ingresos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((modeloAnio == null) ? 0 : modeloAnio.hashCode());
		result = prime * result + ((modeloCarroceria == null) ? 0 : modeloCarroceria.hashCode());
		result = prime * result + ((tipoCombustible == null) ? 0 : tipoCombustible.hashCode());
		result = prime * result + ((propietario == null) ? 0 : propietario.hashCode());
		result = prime * result + ((ingresos == null) ? 0 : ingresos.hashCode());
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
		Vehiculo other = (Vehiculo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (modeloAnio == null) {
			if (other.modeloAnio != null)
				return false;
		} else if (!modeloAnio.equals(other.modeloAnio))
			return false;
		if (modeloCarroceria == null) {
			if (other.modeloCarroceria != null)
				return false;
		} else if (!modeloCarroceria.equals(other.modeloCarroceria))
			return false;
		if (tipoCombustible == null) {
			if (other.tipoCombustible != null)
				return false;
		} else if (!tipoCombustible.equals(other.tipoCombustible))
			return false;
		if (propietario == null) {
			if (other.propietario != null)
				return false;
		} else if (!propietario.equals(other.propietario))
			return false;
		if (ingresos == null) {
			if (other.ingresos != null)
				return false;
		} else if (!ingresos.equals(other.ingresos))
			return false;
		return true;
	}

}
