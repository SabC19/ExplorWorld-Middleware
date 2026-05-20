package com.sabic.explorworld.service;

import java.util.List;

import com.sabic.explorworld.model.LugarDTO;

public interface LugarService {
	
	/**
	 * Busca un lugar por su ID.
	 * @param id
	 * @return El lugar encontrado.
	 */
    public LugarDTO findById(Long id) ;
    
    /**
	 * Busca todos los lugares.
	 * @return Lista de lugares encontrados.
	 */
    public List<LugarDTO> findAll();
    
    /**
	 * Crea un nuevo lugar.
	 * @param lugar
	 * @return El lugar creado.
	 */
    public LugarDTO create(LugarDTO lugar);

    /**
     * Actualiza un lugar existente.
     * @param lugar
     * @return El lugar actualizado.
     */
    public void update(LugarDTO lugar);

    /**
	 * Elimina un lugar por su ID.
	 * @param id
	 * @return El lugar eliminado.
	 */
    public void delete(Long id);
}
