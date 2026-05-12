package com.sabic.explorworld.service.impl;

import com.sabic.explorworld.dao.TipoActividadDAO;
import com.sabic.explorworld.model.TipoActividadDTO;
import com.sabic.explorworld.service.TipoActividadService;

public class TipoActividadServiceImpl implements TipoActividadService {

	private TipoActividadDAO tipoActividadDAO;

	public TipoActividadServiceImpl() {
		this.tipoActividadDAO = new TipoActividadDAO();
	}

	@Override
	public TipoActividadDTO findById(Long id) {
		return tipoActividadDAO.findById(id);
	}
	
	@Override
	public java.util.List<TipoActividadDTO> findAll() {
		return tipoActividadDAO.findAll();
	}

	@Override
	public TipoActividadDTO create(TipoActividadDTO tipoActividad) {
		return tipoActividadDAO.create(tipoActividad);
	}

	@Override
	public void update(TipoActividadDTO tipoActividad) {
		if (tipoActividad.getId() != null) {
			tipoActividadDAO.update(tipoActividad);
		}
	}

	@Override
	public void delete(Long id) {
		if (id != null) {
			tipoActividadDAO.delete(id);
		}
	}
}
