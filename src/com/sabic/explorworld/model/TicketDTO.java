package com.sabic.explorworld.model;

import java.util.Date;

public class TicketDTO extends AbstractValueObject {

	private Long id = null;
	private Date registroDia = null;
	private String numeroTicket = null;
	private Double precio = null;
	
	private Long actividadId = null;
	private Long participanteId = null;
	
	public TicketDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getRegistroDia() {
		return registroDia;
	}

	public void setRegistroDia(Date registroDia) {
		this.registroDia = registroDia;
	}

	public String getNumeroTicket() {
		return numeroTicket;
	}

	public void setNumeroTicket(String numeroTicket) {
		this.numeroTicket = numeroTicket;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Long getActividadId() {
		return actividadId;
	}

	public void setActividadId(Long actividadId) {
		this.actividadId = actividadId;
	}

	public Long getParticipanteId() {
		return participanteId;
	}

	public void setParticipanteId(Long participanteId) {
		this.participanteId = participanteId;
	}

	
}
