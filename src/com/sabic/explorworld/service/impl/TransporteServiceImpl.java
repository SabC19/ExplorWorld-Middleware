package com.sabic.explorworld.service.impl;

import com.sabic.explorworld.dao.TransporteDAO;
import com.sabic.explorworld.model.Transporte;
import com.sabic.explorworld.service.TransporteService;

public class TransporteServiceImpl implements TransporteService {

	private TransporteDAO transporteDAO;

	public TransporteServiceImpl() {
		this.transporteDAO = new TransporteDAO();
	}

	@Override
	public Transporte findById(Long id) {
		return transporteDAO.findById(id);
	}

	@Override
	public Transporte create(Transporte transporte) {
		return transporteDAO.create(transporte);
	}

	@Override
	public void update(Transporte transporte) {
		if (transporte.getId() != null) {
			transporteDAO.update(transporte);
		}
	}

	@Override
	public void delete(Long id) {
		if (id != null) {
			transporteDAO.delete(id);
		}

	}
}
