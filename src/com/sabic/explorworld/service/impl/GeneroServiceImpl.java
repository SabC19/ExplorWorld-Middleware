package com.sabic.explorworld.service.impl;

import java.util.List;

import com.sabic.explorworld.dao.GeneroDAO;
import com.sabic.explorworld.model.Genero;
import com.sabic.explorworld.service.GeneroService;

public class GeneroServiceImpl implements GeneroService {

	private GeneroDAO generoDAO;

	public GeneroServiceImpl() {
		this.generoDAO = new GeneroDAO();
	}

	@Override
	public Genero findById(Long id) {
		return generoDAO.findById(id);
	}

	@Override
	public List<Genero> findAll() {
		return generoDAO.findAll();
	}
}
