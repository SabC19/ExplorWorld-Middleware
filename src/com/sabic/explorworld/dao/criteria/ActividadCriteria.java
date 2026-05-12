package com.sabic.explorworld.dao.criteria;

import java.util.Date;

public class ActividadCriteria {
	
	private Long id = null;
	private String nombre = null;
	private String descripcion = null;
	private Date fechaInicio = null;
	private Date fechaFin = null;
	private Integer capacidad = null;
	private Double precioMin = null;
	private Double precioMax = null;
	private Long actividadTipoId = null;
	private Long InicioLugarId = null;
	private Long FinLugarId = null;
	
	public ActividadCriteria() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public Double getPrecioMin() {
		return precioMin;
	}

	public void setPrecioMin(Double precioMin) {
		this.precioMin = precioMin;
	}

	public Double getPrecioMax() {
		return precioMax;
	}

	public void setPrecioMax(Double precioMax) {
		this.precioMax = precioMax;
	}

	public Long getActividadTipoId() {
		return actividadTipoId;
	}

	public void setActividadTipoId(Long actividadTipoId) {
		this.actividadTipoId = actividadTipoId;
	}

	public Long getInicioLugarId() {
		return InicioLugarId;
	}
	
	public void setInicioLugarId(Long inicioLugarId) {
		InicioLugarId = inicioLugarId;
	}
	
	public Long getFinLugarId() {
		return FinLugarId;
	}
	
	public void setFinLugarId(Long finLugarId) {
		FinLugarId = finLugarId;
	}
	

	
	
}
