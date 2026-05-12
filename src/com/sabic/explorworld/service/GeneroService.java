package com.sabic.explorworld.service;

import java.util.List;

import com.sabic.explorworld.model.Genero;

/**
 * API del servicio de géneros.
 */
public interface GeneroService {

	/**
	 * Busca un género por su id.
	 * @param id
	 * @return Genero encontrado o null si no existe.
	 */
	public Genero findById(Long id);

	/**
	 * Devuelve todos los géneros.
	 * @param genero
	 * @return Lista de géneros o null si no hay resultados.
	 */
	public List<Genero> findAll();
}
