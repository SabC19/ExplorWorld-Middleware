package com.sabic.explorworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sabic.explorworld.dao.criteria.ActividadCriteria;
import com.sabic.explorworld.model.ActividadDTO;
import com.sabic.explorworld.model.GuiaDTO;
import com.sabic.explorworld.utils.DAOUtils;
import com.sabic.explorworld.utils.JDBCUtils;
import com.sabic.explorworld.utils.SQLUtils;

/**
 * DAO de Actividad.
 */
public class ActividadDAO {

	private static Logger logger = LogManager.getLogger(ActividadDAO.class.getName());

	private static final String BASE_QUERY =
			" SELECT a.id, a.name, a.description, a.start_date, a.end_date, a.capacity, a.pricemin, a.pricemax,"
					+ " a.activity_type_id, aty.name, a.start_place_id, a.end_place_id,"
					+ " pin.name, pin.address, pfin.name, pfin.address, g.id, g.name "
					+ " FROM activity a "
					+ " LEFT JOIN activity_guide ag ON a.id = ag.activity_id "
					+ " LEFT JOIN guide g ON g.id = ag.guide_id "
					+ " LEFT JOIN activity_type aty ON aty.id = a.activity_type_id "
					+ " LEFT JOIN place pin  ON a.start_place_id = pin.id "
					+ " LEFT JOIN place pfin ON a.end_place_id   = pfin.id ";

	public GuiaDAO guiaDAO = null;

	public ActividadDAO() {
		guiaDAO = new GuiaDAO();
	}

	/**
	 * Busca una actividad por su ID.
	 */
	public ActividadDTO findById(Connection c, Long id) throws Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE a.id = ? ");

			ps = c.prepareStatement(sql.toString());
			DAOUtils.setParameters(ps, id);
			rs = ps.executeQuery();

			ActividadDTO actividad = null;
			if (rs.next()) {
				actividad = loadNext(rs);
				List<GuiaDTO> guias = guiaDAO.findByActividad(c, actividad.getId());
				actividad.setGuias(guias);
			}
			return actividad;

		} catch (Exception e) {
			logger.error("Buscando actividad {}: {}", id, e);
			throw e;
		}
	}

	/**
	 * Búsqueda dinámica por criterios.
	 */
	public List<ActividadDTO> findByCriteria(Connection c, ActividadCriteria criteria) throws Exception{
		
		logger.info("Criteria: {}", criteria);

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);

			List<String> condiciones = new ArrayList<String>();
			List<Object> parameters = new ArrayList<Object>();

			SQLUtils.addClause(criteria.getId(), condiciones, "a.id = ?", parameters, criteria.getId());
			if (criteria.getId() == null) {
				SQLUtils.addClause(criteria.getNombre(), condiciones,
						"UPPER(a.name) LIKE UPPER(?)", parameters,
						criteria.getNombre() == null ? null : criteria.getNombre() + "%");

				SQLUtils.addClause(criteria.getDescripcion(), condiciones,
						"UPPER(a.description) LIKE UPPER(?)", parameters,
						criteria.getDescripcion() == null ? null : "%" + criteria.getDescripcion() + "%");

				SQLUtils.addClause(criteria.getFechaInicio(), condiciones,
						"a.start_date >= ?", parameters,
						criteria.getFechaInicio() == null ? null
								: new java.sql.Date(criteria.getFechaInicio().getTime()));

				SQLUtils.addClause(criteria.getFechaFin(), condiciones,
						"a.end_date <= ?", parameters,
						criteria.getFechaFin() == null ? null
								: new java.sql.Date(criteria.getFechaFin().getTime()));

				SQLUtils.addClause(criteria.getCapacidad(), condiciones,
						"a.capacity = ?", parameters, criteria.getCapacidad());

				SQLUtils.addClause(criteria.getPrecioMin(), condiciones,
						"a.pricemin >= ?", parameters, criteria.getPrecioMin());

				SQLUtils.addClause(criteria.getPrecioMax(), condiciones,
						"a.pricemax <= ?", parameters, criteria.getPrecioMax());

				SQLUtils.addClause(criteria.getActividadTipoId(), condiciones,
						"a.activity_type_id = ?", parameters, criteria.getActividadTipoId());

				SQLUtils.addClause(criteria.getInicioLugarId(), condiciones,
						"a.start_place_id = ?", parameters, criteria.getInicioLugarId());

				SQLUtils.addClause(criteria.getFinLugarId(), condiciones,
						"a.end_place_id = ?", parameters, criteria.getFinLugarId());
			}

			if (!condiciones.isEmpty()) {
				sql.append(" WHERE ");
				sql.append(String.join(" AND ", condiciones));
			}
			
			sql.append(" ORDER BY a.name ");
            

			ps = c.prepareStatement(sql.toString());
			DAOUtils.setParameters(ps, parameters);
			rs = ps.executeQuery();

			List<ActividadDTO> actividades = new ArrayList<ActividadDTO>();
			while (rs.next()) {
				actividades.add(loadNext(rs));
			}
			return actividades;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JDBCUtils.close(rs, ps);
		}
	}

	/**
	 * Crea una nueva actividad.
	 */
	public ActividadDTO create(Connection c, ActividadDTO actividad) throws Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append(" INSERT INTO activity ");
			sql.append(" (name, description, start_date, end_date, capacity, "
					+ "pricemin, pricemax, activity_type_id, start_place_id, end_place_id) ");
			sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");

			ps = c.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			ps.setString(i++, actividad.getNombre());
			ps.setString(i++, actividad.getDescripcion());
			ps.setDate(i++, new java.sql.Date(actividad.getFechaInicio().getTime()));
			ps.setDate(i++, new java.sql.Date(actividad.getFechaFin().getTime()));
			ps.setInt(i++, actividad.getCapacidad());
			ps.setDouble(i++, actividad.getPrecioMin());
			ps.setDouble(i++, actividad.getPrecioMax());
			ps.setLong(i++, actividad.getActividadTipoId());
			ps.setLong(i++, actividad.getInicioLugarId());
			ps.setLong(i++, actividad.getFinLugarId());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				actividad.setId(rs.getLong(1));
			}
			return actividad;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Actualiza una actividad existente.
	 */
	public ActividadDTO update(Connection c, ActividadDTO actividad) throws Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append(" UPDATE activity ");
			sql.append(" SET name = ?, description = ?, start_date = ?, end_date = ?, ");
			sql.append(" capacity = ?, pricemin = ?, pricemax = ?, activity_type_id = ?, ");
			sql.append(" start_place_id = ?, end_place_id = ? ");
			sql.append(" WHERE id = ? ");

			ps = c.prepareStatement(sql.toString());

			int i = 1;
			ps.setString(i++, actividad.getNombre());
			ps.setString(i++, actividad.getDescripcion());
			ps.setDate(i++, new java.sql.Date(actividad.getFechaInicio().getTime()));
			ps.setDate(i++, new java.sql.Date(actividad.getFechaFin().getTime()));
			ps.setInt(i++, actividad.getCapacidad());
			ps.setDouble(i++, actividad.getPrecioMin());
			ps.setDouble(i++, actividad.getPrecioMax());
			ps.setLong(i++, actividad.getActividadTipoId());
			ps.setLong(i++, actividad.getInicioLugarId());
			ps.setLong(i++, actividad.getFinLugarId());
			ps.setLong(i++, actividad.getId());

			int rows = ps.executeUpdate();
			if (rows > 0) {
				return actividad;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return null;
	}

	/**
	 * Elimina una actividad por su ID.
	 */
	public boolean delete(Connection c, Long id) throws Exception{

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {

			StringBuilder sql = new StringBuilder();
			sql.append(" DELETE FROM activity ");
			sql.append(" WHERE id = ? ");

			ps = c.prepareStatement(sql.toString());
			ps.setLong(1, id);

			int rows = ps.executeUpdate();
			return rows > 0;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JDBCUtils.close(rs, ps);
		}
 
	}

	/**
	 * Mapea una fila del ResultSet a un ActividadDTO.
	 */
	private ActividadDTO loadNext(ResultSet rs) throws Exception {
		int col = 1;

		ActividadDTO a = new ActividadDTO();
		a.setId(rs.getLong(col++));                         
		a.setNombre(rs.getString(col++));                   
		a.setDescripcion(rs.getString(col++));              
		a.setFechaInicio(rs.getTimestamp(col++));           
		a.setFechaFin(rs.getTimestamp(col++));              
		a.setCapacidad(rs.getInt(col++));                   
		a.setPrecioMin(rs.getDouble(col++));                
		a.setPrecioMax(rs.getDouble(col++));                
		a.setActividadTipoId(rs.getLong(col++));            
		a.setActividadTipoNombre(rs.getString(col++));      
		a.setInicioLugarId(rs.getLong(col++));              
		a.setFinLugarId(rs.getLong(col++));                 
		a.setIniciolugarNombre(rs.getString(col++));        
		a.setInicioLugarDireccion(rs.getString(col++));     
		a.setFinLugarNombre(rs.getString(col++));           
		a.setFinLugarDireccion(rs.getString(col++));        
		a.setGuiaId(rs.getLong(col++));                     
		a.setGuiaNombre(rs.getString(col++));               

		return a;
	}
}
