package com.sabic.explorworld.service.impl;

import com.sabic.explorworld.dao.TipoEquipamentoDAO;
import com.sabic.explorworld.model.TipoEquipamento;
import com.sabic.explorworld.service.TipoEquipamentoService;

public class TipoEquipamentoServiceImpl implements TipoEquipamentoService {

	private TipoEquipamentoDAO tipoEquipamentoDAO;

	public TipoEquipamentoServiceImpl() {
		this.tipoEquipamentoDAO = new TipoEquipamentoDAO();
	}

	@Override
	public TipoEquipamento findById(Long id) {
		return tipoEquipamentoDAO.findById(id);
	}

	@Override
	public TipoEquipamento create(TipoEquipamento tipoEquipamento) {
		return tipoEquipamentoDAO.create(tipoEquipamento);
	}

	@Override
	public void update(TipoEquipamento tipoEquipamento) {
		if (tipoEquipamento.getId() != null) {
			tipoEquipamentoDAO.update(tipoEquipamento);
		}
	}

	@Override
	public void delete(Long id) {
		if (id != null) {
			tipoEquipamentoDAO.delete(id);
		}

	}
}
