package com.sabic.explorworld.service;

import java.util.List;

import com.sabic.explorworld.dao.criteria.ParticipanteCriteria;
import com.sabic.explorworld.model.Participante;

public interface ParticipanteService {
	
	/**
	 * Busca un participante por su ID.
	 * @param id
	 * @return Participante
	 */
    public Participante findById(Long id);

    /**
     * Busca participantes por su correo electrónico.
     * @param email
     * @return Lista de participantes con el correo electrónico especificado
     */
    public List<Participante> findByEmail(String email);

    /**
     * Busca participantes que coincidan con los criterios especificados.
     * @param criteria
     * @return Lista de participantes que coinciden con los criterios de búsqueda
     */
    public List<Participante> findByCriteria(ParticipanteCriteria criteria);

    /**
	 * Crea un nuevo participante.
	 * @param participante
	 * @return El participante creado con su ID asignado
	 */
    public Participante create(Participante participante);

    /**
     * Actualiza la información de un participante existente.
     * @param participante
     * @return El participante actualizado
     */
    public void update(Participante participante);

    
    /**
	 * Elimina un participante por su ID.
	 * @param id
	 * @return El participante eliminado.
	 */
    public void delete(Long id);
}
