package com.sabic.explorworld.service.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sabic.explorworld.dao.ActividadDAO;
import com.sabic.explorworld.dao.criteria.ActividadCriteria;
import com.sabic.explorworld.model.ActividadDTO;
import com.sabic.explorworld.service.ActividadService;
import com.sabic.explorworld.utils.JDBCUtils;

public class ActividadServiceImpl implements ActividadService {
	
	private Logger logger = LogManager.getLogger(ActividadServiceImpl.class.getName());

	private ActividadDAO dao = new ActividadDAO();

	public ActividadServiceImpl() {
		this.dao = new ActividadDAO();
	}

	@Override
	public ActividadDTO findById(Long id) throws Exception {
		Connection c =  JDBCUtils.getConnection();
		return dao.findById(c, id);
	}

	@Override
	public List<ActividadDTO> findByCriteria(ActividadCriteria criteria)  throws Exception {
		Connection c = null;
		boolean commit = false;
		try {
			c = JDBCUtils.getConnection();
			c.setAutoCommit(false);
			List<ActividadDTO> results = dao.findByCriteria(c, criteria);
			commit = true;
			return results;
			} catch (Exception e) {
				logger.error("Buscando {}: {}", criteria, e.getMessage(), e);
				throw e;
			} finally {
				JDBCUtils.close(c, commit);
		}
	}

	@Override
	public ActividadDTO create(ActividadDTO actividad) throws Exception {
		Connection c = null;
		boolean commit = false;
		try {
			c = JDBCUtils.getConnection();
			c.setAutoCommit(false);
			ActividadDTO created = dao.create(c, actividad);
			commit = true;
			return created;
		} catch (Exception e) {
			logger.error("Creando {}: {}", actividad, e.getMessage(), e);
			throw e;
		} finally {
			JDBCUtils.close(c, commit);
		}
	}

	@Override
	public ActividadDTO update(ActividadDTO actividad) throws Exception {
			Connection c = null;
			boolean commit = false;
			
			try {
				c = JDBCUtils.getConnection();
				c.setAutoCommit(false);
				ActividadDTO updated = dao.update(c, actividad);
				commit = true;
				return updated;
			} catch (Exception e) {
				logger.error("Actualizando {}: {}", actividad, e.getMessage(), e);
				throw e;
			} finally {
				JDBCUtils.close(c, commit);
				
			}
		}

	@Override
	public boolean delete(Long id) throws Exception {
		
		Connection c = null;
		boolean commit = false;
		try {
			c = JDBCUtils.getConnection();
			c.setAutoCommit(false);
			boolean deleted = dao.delete(c, id);
			commit = true;
			return deleted;
		} catch (Exception e) {
			logger.error("Error al eliminar coche {}:  {}", id, e.getMessage(), e);
			throw e;
		} finally {
			JDBCUtils.close(c, commit);
		
		}

	}
}
