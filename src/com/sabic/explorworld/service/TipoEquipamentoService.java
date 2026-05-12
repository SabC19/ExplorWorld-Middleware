package com.sabic.explorworld.service;

import com.sabic.explorworld.model.TipoEquipamento;

/**
 * API del servicio de tipo de equipamento.
 */
public interface TipoEquipamentoService {

	/**
	 * Busca un tipo de equipamento por su id.
	 * @param id
	 * @return TipoEquipamento encontrado o null si no existe.
	 */
	public TipoEquipamento findById(Long id);

	/**
	 * Crea un nuevo tipo de equipamento.
	 * @param tipoEquipamento
	 * @return TipoEquipamento creado o null si falla.
	 */
	public TipoEquipamento create(TipoEquipamento tipoEquipamento);

	/**
	 * Actualiza un tipo de equipamento existente.
	 * @param tipoEquipamento
	 * @return TipoEquipamento actualizado o null si falla.
	 */
	public void update(TipoEquipamento tipoEquipamento);

	/**
	 * Elimina un tipo de equipamento.
	 * @param id
	 * @return TipoEquipamento eliminado o null si no existe.
	 */
	public void delete(Long id);
}
