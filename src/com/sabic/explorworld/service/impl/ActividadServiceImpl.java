package com.sabic.explorworld.service.impl;

import java.util.List;

import com.sabic.explorworld.dao.ActividadDAO;
import com.sabic.explorworld.dao.criteria.ActividadCriteria;
import com.sabic.explorworld.model.ActividadDTO;
import com.sabic.explorworld.service.ActividadService;

public class ActividadServiceImpl implements ActividadService {

	private ActividadDAO actividadDAO;

	public ActividadServiceImpl() {
		this.actividadDAO = new ActividadDAO();
	}

	@Override
	public ActividadDTO findById(Long id) {
		return actividadDAO.findById(id);
	}

	@Override
	public List<ActividadDTO> findByCriteria(ActividadCriteria criteria) {
		return actividadDAO.findByCriteria(criteria);
	}

	@Override
	public ActividadDTO create(ActividadDTO actividad) {
		return actividadDAO.create(actividad);
	}

	@Override
	public void update(ActividadDTO actividad) {
		if (actividad.getId() != null) {
			actividadDAO.update(actividad);
		}
	}

	@Override
	public void delete(Long id) {
		if (id != null) {
			actividadDAO.delete(id);
		}

	}
}
