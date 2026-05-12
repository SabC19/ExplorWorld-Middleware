package com.sabic.explorworld.service;

import com.sabic.explorworld.model.TipoTransporte;

/**
 * API del servicio de tipo de transporte.
 */
public interface TipoTransporteService {

	/**
	 * Busca un tipo de transporte por su id.
	 * @param id
	 * @return TipoTransporte encontrado o null si no existe.
	 */
	public TipoTransporte findById(Long id);

	/**
	 * Crea un nuevo tipo de transporte.
	 * @param tipoTransporte
	 * @return TipoTransporte creado o null si falla.
	 */
	public TipoTransporte create(TipoTransporte tipoTransporte);

	/**
	 * Actualiza un tipo de transporte existente.
	 * @param tipoTransporte
	 * @return TipoTransporte actualizado o null si falla.
	 */
	public void update(TipoTransporte tipoTransporte);

	/**
	 * Elimina un tipo de transporte.
	 * @param id
	 * @return TipoTransporte eliminado o null si no existe.
	 */
	public void delete(Long id);
}
