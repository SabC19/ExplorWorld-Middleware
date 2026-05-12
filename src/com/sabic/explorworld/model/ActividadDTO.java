package com.sabic.explorworld.model;

import java.util.Date;
import java.util.List;

public class ActividadDTO extends AbstractValueObject {

	private Long id = null;
	private String nombre = null;
	private String descripcion = null;
	private Date fechaInicio = null;
	private Date fechaFin = null;
	private Integer capacidad = null;
	private Double precioMin = null;
	private Double precioMax = null;
	
	private Long actividadTipoId = null;
	
	private String actividadTipoNombre = null;
	
	private Long inicioLugarId = null;
	private String iniciolugarNombre = null;
	private String inicioLugarDireccion = null;
	
	private Long finLugarId = null;
	private String finLugarNombre = null;
	private String finLugarDireccion = null;
	
	private Long guiaId = null;
	private String guiaNombre = null;
	
	private List<GuiaDTO> guias = null;
	
	public ActividadDTO() {
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

	public String getActividadTipoNombre() {
		return actividadTipoNombre;
	}

	public void setActividadTipoNombre(String actividadTipoNombre) {
		this.actividadTipoNombre = actividadTipoNombre;
	}

	public Long getInicioLugarId() {
		return inicioLugarId;
	}

	public void setInicioLugarId(Long inicioLugarId) {
		this.inicioLugarId = inicioLugarId;
	}

	public String getIniciolugarNombre() {
		return iniciolugarNombre;
	}

	public void setIniciolugarNombre(String iniciolugarNombre) {
		this.iniciolugarNombre = iniciolugarNombre;
	}

	public String getInicioLugarDireccion() {
		return inicioLugarDireccion;
	}

	public void setInicioLugarDireccion(String inicioLugarDireccion) {
		this.inicioLugarDireccion = inicioLugarDireccion;
	}

	public Long getFinLugarId() {
		return finLugarId;
	}

	public void setFinLugarId(Long finLugarId) {
		this.finLugarId = finLugarId;
	}

	public String getFinLugarNombre() {
		return finLugarNombre;
	}

	public void setFinLugarNombre(String finLugarNombre) {
		this.finLugarNombre = finLugarNombre;
	}

	public String getFinLugarDireccion() {
		return finLugarDireccion;
	}

	public void setFinLugarDireccion(String finLugarDireccion) {
		this.finLugarDireccion = finLugarDireccion;
	}

	public Long getGuiaId() {
		return guiaId;
	}

	public void setGuiaId(Long guiaId) {
		this.guiaId = guiaId;
	}

	public String getGuiaNombre() {
		return guiaNombre;
	}

	public void setGuiaNombre(String guiaNombre) {
		this.guiaNombre = guiaNombre;
	}

	public List<GuiaDTO> getGuias() {
		return guias;
	}
	
	public void setGuias(List<GuiaDTO> guias) {
		this.guias = guias;
	}
	
	

}
