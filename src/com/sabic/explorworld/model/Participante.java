package com.sabic.explorworld.model;

import java.util.Date;

public class Participante extends AbstractValueObject {

	private Long id = null;
	private String nombre = null;
	private String primerApellido = null;
	private String telefono = null;
	private String email = null;
	private Date fechaNacimiento = null;
	private String password = null;
	
	private Long generoId = null;
	
	
	public Participante() {
		
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



	public String getPrimerApellido() {
		return primerApellido;
	}



	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}



	public String getTelefono() {
		return telefono;
	}



	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}



	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Long getGeneroId() {
		return generoId;
	}



	public void setGeneroId(Long generoId) {
		this.generoId = generoId;
	}

	
	
}
