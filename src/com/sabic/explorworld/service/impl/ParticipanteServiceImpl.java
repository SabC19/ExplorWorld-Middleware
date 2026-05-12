package com.sabic.explorworld.service.impl;

import java.util.List;

import com.sabic.explorworld.dao.ParticipanteDAO;
import com.sabic.explorworld.dao.criteria.ParticipanteCriteria;
import com.sabic.explorworld.model.Participante;
import com.sabic.explorworld.service.ParticipanteService;

public class ParticipanteServiceImpl implements ParticipanteService {

    private ParticipanteDAO participanteDAO;

    public ParticipanteServiceImpl() {
        this.participanteDAO = new ParticipanteDAO();
    }

    @Override
    public Participante findById(Long id) {
        return participanteDAO.findById(id);
    }

    @Override
    public List<Participante> findByEmail(String email) {
        return participanteDAO.findByEmail(email);
    }

    @Override
    public List<Participante> findByCriteria(ParticipanteCriteria criteria) {
        if (criteria == null) {
        	criteria = new ParticipanteCriteria();
        }
        return participanteDAO.findByCriteria(criteria);
    }

    @Override
    public Participante create(Participante participante) {
        return participanteDAO.create(participante);
    }

    @Override
    public void update(Participante participante) {
        if (participante != null) {
			participanteDAO.update(participante);
		}
    }

    @Override
    public void delete(Long id) {
    	if (id != null) {
    		participanteDAO.delete(id);
    	}
    }
}
