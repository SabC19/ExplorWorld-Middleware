package com.sabic.explorworld.service.impl;

import com.sabic.explorworld.dao.LugarDAO;
import com.sabic.explorworld.model.LugarDTO;
import com.sabic.explorworld.service.LugarService;

public class LugarServiceImpl implements LugarService {

    private LugarDAO lugarDAO;

    public LugarServiceImpl() {
        this.lugarDAO = new LugarDAO();
    }

    @Override
    public LugarDTO findById(Long id) {
        return lugarDAO.findById(id);
    }

    @Override
    public LugarDTO create(LugarDTO lugar) {
        return lugarDAO.create(lugar);
    }

    @Override
    public void update(LugarDTO lugar) {
    	if (lugar.getId() != null) {
            lugarDAO.update(lugar);
        }
    }

    @Override
    public void delete(Long id) {
    	if (id != null) {
            lugarDAO.delete(id);
        }
    }

}
