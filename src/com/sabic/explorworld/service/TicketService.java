package com.sabic.explorworld.service;

import com.sabic.explorworld.model.TicketDTO;

/**
 * API del servicio de tickets.
 */
public interface TicketService {

	/**
	 * Busca un ticket por su id.
	 * @param id
	 * @return TicketDTO encontrado o null si no existe.
	 */
	public TicketDTO findById(Long id);

	/**
	 * Crea un nuevo ticket.
	 * @param ticket
	 * @return TicketDTO creado o null si falla.
	 */
	public TicketDTO create(TicketDTO ticket);

	/**
	 * Actualiza un ticket existente.
	 * @param ticket
	 * @return TicketDTO actualizado o null si falla.
	 */
	public void update(TicketDTO ticket);

	/**
	 * Elimina un ticket.
	 * @param id
	 * @return TicketDTO eliminado o null si no existe.
	 */
	public void delete(Long id);
}
