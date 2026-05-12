package com.sabic.explorworld.service;

import java.util.List;

import com.sabic.explorworld.model.TipoActividadDTO;

/**
 * API del servicio de tipo de actividad.
 */
public interface TipoActividadService {

	/**
	 * Busca un tipo de actividad por su id.
	 * @param id
	 * @return TipoActividadDTO encontrado o null si no existe.
	 */
	public TipoActividadDTO findById(Long id);
	
	/**
	 * Busca todos los tipos de actividad.
	 * @param TipoActividad
	 * @return Una lista con todos los tipos de actividad encontrados, o null si no existe ninguno.
	 */
	public List<TipoActividadDTO> findAll();

	/**
	 * Crea un nuevo tipo de actividad.
	 * @param tipoActividad
	 * @return TipoActividadDTO creado o null si falla.
	 */
	public TipoActividadDTO create(TipoActividadDTO tipoActividad);

	/**
	 * Actualiza un tipo de actividad existente.
	 * @param tipoActividad
	 * @return TipoActividadDTO actualizado o null si falla.
	 */
	public void update(TipoActividadDTO tipoActividad);

	/**
	 * Elimina un tipo de actividad.
	 * @param id
	 * @return TipoActividadDTO eliminado o null si no existe.
	 */
	public void delete(Long id);
}
