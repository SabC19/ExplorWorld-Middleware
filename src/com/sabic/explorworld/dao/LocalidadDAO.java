package com.sabic.explorworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sabic.explorworld.model.LocalidadDTO;
import com.sabic.explorworld.utils.DAOUtils;

public class LocalidadDAO {

	private static final String BASE_QUERY =
			" SELECT l.id, l.name, l.province_id "
					+ " FROM locality l";

	public LocalidadDAO() {
	}

	/**
	 * Búsqueda de una localidad por su Id.
	 */
	public LocalidadDTO findById(Connection c, Long id) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE id = ? ");

			ps = c.prepareStatement(sql.toString());
			ps.setLong(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				return loadNext(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DAOUtils.close(rs, ps, c);
		}
		return null;
	}

	/**
	 * Búsqueda de localidades por nombre (coincidencia parcial).
	 * @param nombre El nombre o parte del nombre de la localidad a buscar.
	 * @return Una lista de localidades que coinciden con el criterio de búsqueda, o null si no se encuentran.
	 */
	public List<LocalidadDTO> findByNombre(Connection c, String nombre) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE UPPER(name) LIKE UPPER(?) ");

			ps = c.prepareStatement(sql.toString());
			ps.setString(1, "%" + nombre + "%");

			rs = ps.executeQuery();

			List<LocalidadDTO> localidades = new ArrayList<>();

			while (rs.next()) {
				localidades.add(loadNext(rs));
			}

			return localidades;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DAOUtils.close(rs, ps, c);
		}
		return null;
	}

	private LocalidadDTO loadNext(ResultSet rs) throws Exception {
		int i = 1;
		LocalidadDTO l = new LocalidadDTO();
		l.setId(rs.getLong(i++));
		l.setNombre(rs.getString(i++));
		l.setProvinciaId(rs.getLong(i++));
		return l;
	}
}
