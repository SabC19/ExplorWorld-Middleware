package com.sabic.explorworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sabic.explorworld.model.Transporte;
import com.sabic.explorworld.utils.DAOUtils;

public class TransporteDAO {
	
	private static final String BASE_QUERY = 
			" SELECT t.id, t.plate_number, t.seat_number, "
			+ "t.transport_type_id, tt.name "
			+ "FROM transport t "
			+ "INNER JOIN transport_type tt ON"
			+ " tt.id = t.transport_type_id ";

	public TransporteDAO() {
	}

	/**
	 * Busqueda de un transporte por su id.
	 * @param id
	 * @return El transporte encontrado
	 */
	public Transporte findById(Connection c, Long id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE t.id = ? ");
			

			ps = c.prepareStatement(sql.toString());
			ps.setLong(1, id);
			rs = ps.executeQuery();

			Transporte t = null;

			if (rs.next()) {
				t = loadNext(rs);
				
			}

			return t;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		 DAOUtils.close(rs, ps, c);
		}
		return null;
	}

	/**
	 * Crea un nuevo transporte.
	 * @param transporte
	 * @return El transporte creado
	 */
	public Transporte create(Connection c, Transporte transporte) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sqlBuilder = new StringBuilder();
			sqlBuilder.append(" INSERT INTO transport (plate_number,"
					+ "seat_number, transport_type_id) ");
			sqlBuilder.append(" VALUES (?, ?, ?) ");
			
			String sql = sqlBuilder.toString();

			ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			ps.setString(i++, transporte.getNumeroPlaca());
			ps.setString(i++, transporte.getNumeroPuestos());
			ps.setLong(i++, transporte.getTipoTransporteId());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				transporte.setId(rs.getLong(1));
			}
			
			return transporte;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DAOUtils.close(rs, ps, c);
		}
		return null;
	}

	/**
	 * Actualiza un transporte ya existente.
	 * @param transporte
	 * @return El transporte actualizado
	 */
	public Transporte update(Connection c, Transporte transporte) {
		PreparedStatement ps = null;

		try {

			StringBuilder sqlBuilder = new StringBuilder();
			sqlBuilder.append(" UPDATE transport t");
			sqlBuilder.append(" SET t.plate_number = ?, "
					+ "t.seat_number = ?, t.transport_type_id = ? ");
			sqlBuilder.append(" WHERE t.id = ? ");

			ps = c.prepareStatement(sqlBuilder.toString());

			int i = 1;
			ps.setString(i++, transporte.getNumeroPlaca());
			ps.setString(i++, transporte.getNumeroPuestos());
			ps.setLong(i++, transporte.getTipoTransporteId());
			ps.setLong(i++, transporte.getId());

			int rows = ps.executeUpdate();
			if (rows > 0) {
				return transporte;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {	
			DAOUtils.close(null, ps, c);
		}
		return null;
	}
	
	/**
	 * Elimina un transporte por su id.
	 * @param id
	 * @return true si se eliminó correctamente, false en caso contrario.
	 */

	public boolean delete(Connection c, Long id) {
		PreparedStatement ps = null;

		try {

			StringBuilder sqlBuilder = new StringBuilder();
			sqlBuilder.append(" DELETE FROM transport ");
			sqlBuilder.append(" WHERE id = ? ");

			ps = c.prepareStatement(sqlBuilder.toString());
			ps.setLong(1, id);

			int rows = ps.executeUpdate();
			return rows > 0;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DAOUtils.close(null, ps, c);
		}
		return false;
	}
	
	
	private Transporte loadNext(ResultSet rs) throws Exception {
		int col = 1;
		Transporte t = new Transporte();
		t.setId(rs.getLong(col++));
		t.setNumeroPlaca(rs.getString(col++));
		t.setNumeroPuestos(rs.getString(col++));
		t.setTipoTransporteId(rs.getLong(col++));

		return t;
	}
	
}
