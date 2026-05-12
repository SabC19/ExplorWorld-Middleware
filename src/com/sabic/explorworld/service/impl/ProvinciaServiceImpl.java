package com.sabic.explorworld.service.impl;

import com.sabic.explorworld.dao.ProvinciaDAO;
import com.sabic.explorworld.model.Provincia;
import com.sabic.explorworld.service.ProvinciaService;

public class ProvinciaServiceImpl implements ProvinciaService {

    private ProvinciaDAO provinciaDAO;

    public ProvinciaServiceImpl() {
        this.provinciaDAO = new ProvinciaDAO();
    }

    @Override
    public Provincia findById(Long id) {
        return provinciaDAO.findById(id);
    }

    @Override
    public Provincia findByNombre(String nombre) {
        return provinciaDAO.findByNombre(nombre);
    }
}