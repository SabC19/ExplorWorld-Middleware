package com.sabic.explorworld.service;

import com.sabic.explorworld.model.Provincia;

public interface ProvinciaService {

	/**
	 * Busca una provincia por su id
	 * @param id
	 * @return Provincia
	 */
    public Provincia findById(Long id);

    /**
	 * Busca una provincia por su nombre
	 * @param nombre
	 * @return Provincia
	 */
     public Provincia findByNombre(String nombre);
}
