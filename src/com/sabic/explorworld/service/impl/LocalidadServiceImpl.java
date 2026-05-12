package com.sabic.explorworld.service.impl;

import java.util.List;

import com.sabic.explorworld.dao.LocalidadDAO;
import com.sabic.explorworld.model.LocalidadDTO;
import com.sabic.explorworld.service.LocalidadService;

public class LocalidadServiceImpl implements LocalidadService {

    private LocalidadDAO localidadDAO;

    public LocalidadServiceImpl() {
        this.localidadDAO = new LocalidadDAO();
    }

    @Override
    public LocalidadDTO findById(Long id) {
        return localidadDAO.findById(id);
    }

    @Override
    public List<LocalidadDTO> findByNombre(String nombre) {
        return localidadDAO.findByNombre(nombre);
    }

}
