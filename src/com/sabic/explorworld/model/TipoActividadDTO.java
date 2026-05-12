package com.sabic.explorworld.model;

public class TipoActividadDTO extends AbstractValueObject {
	
	private Long id = null;
	private String nombre = null;
	
	private Long tipoEquipamentoId = null;
	
	public TipoActividadDTO() {
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
	
	public Long getTipoEquipamentoId() {
		return tipoEquipamentoId;
	}
	
	public void setTipoEquipamentoId(Long tipoEquipamentoId) {
		this.tipoEquipamentoId = tipoEquipamentoId;
	}

	
}
