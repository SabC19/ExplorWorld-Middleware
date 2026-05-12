package com.sabic.explorworld.model;

public class Transporte {

	private Long id = null;
	private String numeroPlaca = null;
	private String numeroPuestos = null;
	
	private Long tipoTransporteId = null;
	
	public Transporte() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroPlaca() {
		return numeroPlaca;
	}

	public void setNumeroPlaca(String numeroPlaca) {
		this.numeroPlaca = numeroPlaca;
	}

	public String getNumeroPuestos() {
		return numeroPuestos;
	}

	public void setNumeroPuestos(String numeroPuestos) {
		this.numeroPuestos = numeroPuestos;
	}

	public Long getTipoTransporteId() {
		return tipoTransporteId;
	}

	public void setTipoTransporteId(Long tipoTransporteId) {
		this.tipoTransporteId = tipoTransporteId;
	}


}
