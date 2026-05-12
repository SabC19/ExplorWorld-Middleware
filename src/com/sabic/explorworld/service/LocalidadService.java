package com.sabic.explorworld.service;

import java.util.List;
import com.sabic.explorworld.model.LocalidadDTO;

public interface LocalidadService {

	/**
	 * Busca una localidad por su ID.
	 * @param id
	 * @return Localidad
	 */
    public LocalidadDTO findById(Long id);

    /**
     * Busca localidades por su nombre. El nombre puede ser parcial, es decir, se pueden encontrar localidades que contengan el nombre buscado.
     * @param nombre
     * @return Lista de Localidades
     */
    public List<LocalidadDTO> findByNombre(String nombre);

}
