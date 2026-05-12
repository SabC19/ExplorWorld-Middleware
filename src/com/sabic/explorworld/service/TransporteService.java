package com.sabic.explorworld.service;

import com.sabic.explorworld.model.Transporte;

/**
 * API del servicio de transporte.
 */
public interface TransporteService {

	/**
	 * Busca un transporte por su id.
	 * @param id
	 * @return Transporte encontrado o null si no existe.
	 */
	public Transporte findById(Long id);

	/**
	 * Crea un nuevo transporte.
	 * @param transporte
	 * @return Transporte creado o null si falla.
	 */
	public Transporte create(Transporte transporte);

	/**
	 * Actualiza un transporte existente.
	 * @param transporte
	 * @return Transporte actualizado o null si falla.
	 */
	public void update(Transporte transporte);

	/**
	 * Elimina un transporte.
	 * @param id
	 * @return Transporte eliminado o null si no existe.
	 */
	public void delete(Long id);
}
