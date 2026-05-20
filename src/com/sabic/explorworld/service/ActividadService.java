package com.sabic.explorworld.service;

import java.util.List;

import com.sabic.explorworld.dao.criteria.ActividadCriteria;
import com.sabic.explorworld.model.ActividadDTO;

/**
 * API del servicio de actividad.
 */
public interface ActividadService {

	/**
	 * Busca una actividad por su id.
	 * @param id
	 * @return Actividad encontrada o null si no existe.
	 */
	public ActividadDTO findById(Long id) throws Exception;

	/**
	 * Búsqueda estructurada de actividades.
	 * @param criteria Encapsula los criterios de búsqueda.
	 * @return Lista de actividades encontradas.
	 */
	public List<ActividadDTO> findByCriteria(ActividadCriteria criteria) throws Exception;

	/**
	 * Crea una nueva actividad.
	 * @param actividad Datos de la actividad a insertar.
	 * @return Actividad creada con su id generado,
	 * o null si la creación falla.
	 */
	public ActividadDTO create(ActividadDTO actividad) throws Exception;

	/**
	 * Actualiza todos los datos de una actividad,
	 * en base a su id.
	 * @param actividad Datos a actualizar.
	 * @return Actividad actualizada o null si falla.
	 */
	public ActividadDTO update(ActividadDTO actividad) throws Exception;

	/**
	 * Elimina una actividad.
	 * @param id
	 * @return Actividad eliminada o null si no existe.
	 */
	public boolean delete(Long id) throws Exception;

}
