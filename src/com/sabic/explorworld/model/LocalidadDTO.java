package com.sabic.explorworld.model;

public class LocalidadDTO extends AbstractValueObject {

	private Long id = null;
	private String nombre = null;
	private Long provinciaId = null;
	
	public LocalidadDTO() {
		
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

	public Long getProvinciaId() {
		return provinciaId;
	}

	public void setProvinciaId(Long provinciaId) {
		this.provinciaId = provinciaId;
	}
	
	

}
