package com.sabic.explorworld.model;

public class TipoEquipamento extends AbstractValueObject {
	
	private Long id = null;
	private String model = null;

	public TipoEquipamento() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	

}
