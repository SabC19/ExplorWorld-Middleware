package com.sabic.explorworld.service.impl;

import com.sabic.explorworld.dao.TipoTransporteDAO;
import com.sabic.explorworld.model.TipoTransporte;
import com.sabic.explorworld.service.TipoTransporteService;

public class TipoTransporteServiceImpl implements TipoTransporteService {

	private TipoTransporteDAO tipoTransporteDAO;

	public TipoTransporteServiceImpl() {
		this.tipoTransporteDAO = new TipoTransporteDAO();
	}

	@Override
	public TipoTransporte findById(Long id) {
		return tipoTransporteDAO.findById(id);
	}

	@Override
	public TipoTransporte create(TipoTransporte tipoTransporte) {
		return tipoTransporteDAO.create(tipoTransporte);
	}

	@Override
	public void update(TipoTransporte tipoTransporte) {
		if (tipoTransporte.getId() != null) {
			tipoTransporteDAO.update(tipoTransporte);
		}
	}

	@Override
	public void delete(Long id) {
		if (id != null) {
			tipoTransporteDAO.delete(id);
		}
	}
}
