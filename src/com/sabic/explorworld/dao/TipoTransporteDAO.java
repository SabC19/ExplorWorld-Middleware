package com.sabic.explorworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sabic.explorworld.model.TipoTransporte;
import com.sabic.explorworld.utils.DAOUtils;

public class TipoTransporteDAO {

	private static final String BASE_QUERY =
			" SELECT tt.id, tt.name "
		  + " FROM transport_type tt ";

	public TipoTransporteDAO() {
	}

	/**
	 * Búsqueda de un tipo de transporte por su id.
	 * @param id
	 * @return El tipo de transporte encontrado
	 */
	public TipoTransporte findById(Connection c, Long id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE tt.id = ? ");

			ps = c.prepareStatement(sql.toString());
			ps.setLong(1, id);
			rs = ps.executeQuery();

			TipoTransporte tt = null;

			if (rs.next()) {
				tt = loadNext(rs);
			}

			return tt;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DAOUtils.close(rs, ps, c);
		}
		return null;
	}

	/**
	 * Crea un nuevo tipo de transporte.
	 * @param tipoTransporte
	 * @return El tipo de transporte creado
	 */
	public TipoTransporte create(Connection c, TipoTransporte tipoTransporte) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append(" INSERT INTO transport_type (name) ");
			sql.append(" VALUES (?) ");

			ps = c.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			ps.setString(i++, tipoTransporte.getNombre());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				tipoTransporte.setId(rs.getLong(1));
			}

			return tipoTransporte;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DAOUtils.close(rs, ps, c);
		}
		return null;
	}

	/**
	 * Actualiza un tipo de transporte.
	 * @param tipoTransporte
	 * @return El tipo de transporte actualizado
	 */
	public TipoTransporte update(Connection c, TipoTransporte tipoTransporte) {
		PreparedStatement ps = null;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append(" UPDATE transport_type ");
			sql.append(" SET name = ? ");
			sql.append(" WHERE id = ? ");

			ps = c.prepareStatement(sql.toString());

			int i = 1;
			ps.setString(i++, tipoTransporte.getNombre());
			ps.setLong(i++, tipoTransporte.getId());

			int rows = ps.executeUpdate();
			if (rows > 0) {
				return tipoTransporte;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DAOUtils.close(null, ps, c);
		}
		return null;
	}

	/**
	 * Elimina un tipo de transporte por su id.
	 * @param id
	 * @return true si se eliminó correctamente, false en caso contrario
	 */
	public boolean delete(Connection c, Long id) {
		PreparedStatement ps = null;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append(" DELETE FROM transport_type ");
			sql.append(" WHERE id = ? ");

			ps = c.prepareStatement(sql.toString());
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

	private TipoTransporte loadNext(ResultSet rs) throws Exception {
		int col = 1;
		TipoTransporte tt = new TipoTransporte();
		tt.setId(rs.getLong(col++));
		tt.setNombre(rs.getString(col++));
		return tt;
	}
}
