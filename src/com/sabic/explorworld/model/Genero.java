package com.sabic.explorworld.model;

public class Genero extends AbstractValueObject{
	
	private Long id = null;
	private String nombre = null;
	
	public Genero() {	
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


}
